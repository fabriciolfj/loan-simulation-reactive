package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import com.github.fabriciolfj.simulation.domain.proposal.CalculationProposal;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CalculateProposalUseCase {

    public Mono<Proposal> execute(final Proposal proposal, final Customer customer) {
        return Mono.just(proposal)
                .map(p -> {
                    var rate = p.calculateInterestRate(customer.getCreditScore());
                    var installmentAmount = p.calculateInstallmentAmount(rate);

                    return CalculationProposal
                            .builder()
                            .installmentAmount(installmentAmount)
                            .interestRate(rate)
                            .build();
                })
                .doOnNext(calc -> log.info("calculate {}, to proposal {}", calc, proposal.getCode()))
                .map(proposal::addCalculation);
    }



}
