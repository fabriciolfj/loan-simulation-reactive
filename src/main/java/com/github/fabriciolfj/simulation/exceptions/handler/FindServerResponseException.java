package com.github.fabriciolfj.simulation.exceptions.handler;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface FindServerResponseException {

    Mono<ServerResponse> process(final Throwable ex);

    boolean isMatch(final Throwable ex);
}
