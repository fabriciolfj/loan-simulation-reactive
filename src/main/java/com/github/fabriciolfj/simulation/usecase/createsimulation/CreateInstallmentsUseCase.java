package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.simulation.Installment;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Service
public class CreateInstallmentsUseCase {

    private static final long QUANTITY_DAY_OF_MONTHS = 30L;

    public Mono<Simulation> execute(final Simulation simulation) {
        return Mono.just(simulation)
                .filter(Simulation::isApproved)
                .map(this::generateInstallments)
                .map(simulation::updateInstallments)
                .switchIfEmpty(Mono.just(simulation));
    }

    private List<Installment> generateInstallments(final Simulation simulation) {
        final AtomicReference<BigDecimal> remainingBalance = new AtomicReference<>(simulation.getRequestedAmount());

        return IntStream.rangeClosed(1, simulation.getNumberOfInstallments())
                .mapToObj(installmentNumber -> createInstallment(
                        installmentNumber,
                        remainingBalance,
                        simulation,
                        installmentNumber == simulation.getNumberOfInstallments())
                ).toList();
    }

    private Installment createInstallment(final int installmentNumber,
                                          final AtomicReference<BigDecimal> remainingBalance,
                                          final Simulation simulation,
                                          final boolean isLastInstallment) {

        BigDecimal interestAmount = simulation.calculateInterestAmount(remainingBalance.get());
        BigDecimal principalAmount = simulation.calcSubtractInstallmentAmount(interestAmount);

        if (isLastInstallment && isRemainingGranterZero(remainingBalance)) {
            principalAmount = remainingBalance.get();
            interestAmount = simulation.calcSubtractInstallmentAmount(principalAmount);
        }

        var payDate = simulation.getSimulationDate();
        return Installment.builder()
                .installmentNumber(installmentNumber)
                .installmentAmount(simulation.getInstallmentAmount())
                .interestAmount(interestAmount)
                .principalAmount(principalAmount)
                .paymentDate(payDate.plusDays(installmentNumber * QUANTITY_DAY_OF_MONTHS))
                .remainingBalance(updateNewBalance(remainingBalance, principalAmount))
                .paidAmount(BigDecimal.ZERO)
                .dueDate(simulation.calculateDueDate(installmentNumber))
                .build();
    }

    private boolean isRemainingGranterZero(AtomicReference<BigDecimal> remainingBalance) {
        return remainingBalance.get().compareTo(BigDecimal.ZERO) != 0;
    }

    private BigDecimal updateNewBalance(final AtomicReference<BigDecimal> remainingBalance, final BigDecimal principalAmount) {
        final BigDecimal newBalance = remainingBalance.get().subtract(principalAmount);

        remainingBalance.set(newBalance);
        return newBalance;
    }
}
