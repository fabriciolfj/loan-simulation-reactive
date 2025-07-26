package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.github.fabriciolfj.simulation.domain.common.ConstAmount.PERCENTAGE;

@Service
public class CalculeInstallmentAmountUseCase {

    public Mono<BigDecimal> execute(final Proposal proposal, final BigDecimal rate) {
        return Mono.just(proposal)
                .map(p -> {
                    final BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(PERCENTAGE), 6, RoundingMode.HALF_UP);
                    final BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate);
                    final BigDecimal power = onePlusRate.pow(p.getInstallments());

                    final BigDecimal numerator = monthlyRate.multiply(power);
                    final BigDecimal denominator = power.subtract(BigDecimal.ONE);

                    if (denominator.compareTo(BigDecimal.ZERO) == 0) {
                        return p.getRequestAmount().divide(new BigDecimal(p.getInstallments()), 2, RoundingMode.HALF_UP);
                    }

                    final BigDecimal factor = numerator.divide(denominator, 6, RoundingMode.HALF_UP);
                    return p.getRequestAmount().multiply(factor).setScale(2, RoundingMode.HALF_UP);
                });
    }
}
