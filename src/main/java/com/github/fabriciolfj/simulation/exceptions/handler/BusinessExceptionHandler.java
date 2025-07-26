package com.github.fabriciolfj.simulation.exceptions.handler;

import com.github.fabriciolfj.simulation.exceptions.clazz.BusinessException;
import com.github.fabriciolfj.simulation.exceptions.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BusinessExceptionHandler implements FindServerResponseException {

    @Override
    public Mono<ServerResponse> process(Throwable ex) {
        return ServerResponse
                .badRequest()
                .body(Mono
                        .just(ErrorDTO.builder()
                                .code(HttpStatus.UNAUTHORIZED.value())
                                .message(ex.getMessage())
                                .build()),
                        ErrorDTO.class);
    }

    @Override
    public boolean isMatch(Throwable ex) {
        return ex instanceof BusinessException;
    }
}
