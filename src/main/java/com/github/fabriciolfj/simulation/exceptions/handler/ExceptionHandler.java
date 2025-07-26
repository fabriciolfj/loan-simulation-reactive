package com.github.fabriciolfj.simulation.exceptions.handler;


import com.github.fabriciolfj.simulation.exceptions.dto.ErrorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.DEFAULT_ERROR;

@Component
@RequiredArgsConstructor
public class ExceptionHandler {

    private final List<FindServerResponseException> findServerResponseExceptions;

    public Mono<ServerResponse> handler(final Throwable error) {
        return Flux.fromIterable(findServerResponseExceptions)
                .filter(ex -> ex.isMatch(error))
                .flatMap(handler -> handler.process(error))
                .next()
                .switchIfEmpty(ServerResponse
                        .status(HttpStatus.SERVICE_UNAVAILABLE)
                        .bodyValue(ErrorDTO.builder()
                                .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                                .message(DEFAULT_ERROR.toMessage())
                                .build()));


    }
}
