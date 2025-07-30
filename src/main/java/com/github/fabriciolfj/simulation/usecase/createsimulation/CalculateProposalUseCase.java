package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;
import com.github.fabriciolfj.simulation.domain.proposal.CalculationProposal;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.exceptions.clazz.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculateProposalUseCase {

    private final GetRateByScoreUseCase rateByScoreUseCase;
    private final AdditionalRateUseCase additionalRateUseCase;
    private final AdditionalRateInstallmentsUseCase additionalRateInstallmentsUseCase;
    private final CalculeInstallmentAmountUseCase calculeInstallmentAmountUseCase;

    public Mono<Proposal> execute(final Proposal proposal, final AvaluateCustomer avaluateCustomer) {
        return rateByScoreUseCase.execute(avaluateCustomer.getParameter(), avaluateCustomer.getCustomer())
                .flatMap(value -> additionalRateUseCase.execute(avaluateCustomer.getParameter(), proposal, value))
                .flatMap(value -> additionalRateInstallmentsUseCase.execute(avaluateCustomer.getParameter(), proposal, value))
                .zipWhen(value -> calculeInstallmentAmountUseCase.execute(proposal, value))
                .map(p ->
                    CalculationProposal
                            .builder()
                            .installmentAmount(p.getT2())
                            .interestRate(p.getT1())
                            .build())
                .doOnNext(calc -> log.info("calculate {}, to proposal {}", calc, proposal.getCode()))
                .doOnError(c -> log.error("fail process calc simulation {}, details {}", proposal.getCode(), c.getMessage()))
                .onErrorMap(c -> new BusinessException())
                .map(proposal::addCalculation);
    }



}
