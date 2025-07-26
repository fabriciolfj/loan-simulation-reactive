package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationStatus;
import com.github.fabriciolfj.simulation.exceptions.clazz.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DefineStatusSimulationUseCase {

    public Mono<SimulationStatus> execute(final AvaluateCustomer customer, final Proposal proposal) {
        return Mono.just(customer)
                .filter(c -> c.isCustomerValid(proposal.getRequestAmount(), proposal.getInstallments()))
                .map(c -> SimulationStatus.APPROVED)
                .doOnNext(s -> log.info("status simulation {}-{}", proposal.getCode(), s.name()))
                .doOnError(c -> log.error("fail process status simulation {}, details {}", proposal.getCode(), c.getMessage()))
                .onErrorMap(c -> new BusinessException())
                .switchIfEmpty(
                  Mono.just(SimulationStatus.REJECTED)
                ).doOnNext(s -> log.info("simulation reject {}-{}", proposal.getCode(), s.name()));
    }


}
