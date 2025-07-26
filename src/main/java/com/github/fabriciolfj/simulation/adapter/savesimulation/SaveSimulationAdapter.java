package com.github.fabriciolfj.simulation.adapter.savesimulation;

import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import com.github.fabriciolfj.simulation.usecase.createsimulation.SaveSimulationGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class SaveSimulationAdapter implements SaveSimulationGateway {

    @Override
    public Mono<Simulation> process(Simulation simulation) {
        return Mono.just(simulation);
    }
}
