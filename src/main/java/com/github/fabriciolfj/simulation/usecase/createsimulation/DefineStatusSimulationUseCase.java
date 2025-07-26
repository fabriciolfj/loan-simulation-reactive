package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DefineStatusSimulationUseCase {

    public Mono<SimulationStatus> execute(final Customer customer, final Proposal proposal) {
        return Mono.just(customer)
                .filter(c -> c.isCustomerValid(proposal.getRequestAmount(), proposal.getInstallments()))
                .map(c -> SimulationStatus.APPROVED)
                .doOnNext(s -> log.info("status simulation {}-{}", proposal.getCode(), s.name()))
                .switchIfEmpty(
                  Mono.just(SimulationStatus.REJECTED)
                ).doOnNext(s -> log.info("simulation reject {}-{}", proposal.getCode(), s.name()));
    }


}
