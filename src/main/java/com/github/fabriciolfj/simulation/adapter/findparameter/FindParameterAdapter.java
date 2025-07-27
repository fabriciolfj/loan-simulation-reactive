package com.github.fabriciolfj.simulation.adapter.findparameter;

import com.github.fabriciolfj.simulation.domain.common.Parameter;
import com.github.fabriciolfj.simulation.exceptions.clazz.FailRequestParameterException;
import com.github.fabriciolfj.simulation.exceptions.clazz.InfrastructureException;
import com.github.fabriciolfj.simulation.exceptions.clazz.ParameterUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindParameterAdapter {

    @Value("${url.parameter}")
    private String url;

    private final WebClient webClient;

    public Mono<Parameter> execute() {
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(err -> {
                            log.error("fail get parameter, details {}", err);
                            return Mono.error(new FailRequestParameterException());
                        }))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(String.class)
                    .flatMap(err -> {
                        log.error("unavailable api parameter, details {}", err);
                        return Mono.error(new ParameterUnavailableException());
                    }))
                .bodyToMono(ParameterDTO.class)
                .map(ParameterDTOMapper::toEntity)
                .onErrorResume(Exception.class, ex -> {
                    log.error("erro not mapped {}", ex.getMessage());
                    return Mono.error(new InfrastructureException());
                });
    }
}
