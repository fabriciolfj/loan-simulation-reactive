package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateSimulationUseCase {

    public Mono<Simulation> execute(final Proposal proposal) {
        return Mono.empty();
    }
}
