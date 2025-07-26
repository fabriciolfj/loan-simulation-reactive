package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.common.Parameter;
import reactor.core.publisher.Mono;

public interface FindParameterGateway {

    Mono<Parameter> execute();
}
