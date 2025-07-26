package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveSimulationGateway {

    Mono<Simulation> process(final Simulation simulation);
}
