package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.common.Parameter;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class AdditionalRateInstallmentsUseCase {

    public Mono<BigDecimal> execute(final Parameter parameter, final Proposal proposal, final BigDecimal rate) {
        return Mono.just(parameter)
                .filter(p -> proposal.getInstallments() > p.getInstallmentsHigh())
                .map(p -> rate.add(p.getAdditionalAmount()))
                .switchIfEmpty(Mono.just(rate));
    }
}
