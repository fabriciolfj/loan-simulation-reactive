package com.github.fabriciolfj.simulation.exceptions.handler;

import com.github.fabriciolfj.simulation.exceptions.clazz.ValidationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class ValidationExceptionHandler implements FindServerResponseException {

    @Override
    public Mono<ServerResponse> process(Throwable ex) {
        return ServerResponse.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of(
                        "error", "Erro de validação",
                        "message", ex.getMessage(),
                        "timestamp", java.time.Instant.now().toString()
                ));
    }

    @Override
    public boolean isMatch(Throwable ex) {
        return ex instanceof ValidationException;
    }
}
