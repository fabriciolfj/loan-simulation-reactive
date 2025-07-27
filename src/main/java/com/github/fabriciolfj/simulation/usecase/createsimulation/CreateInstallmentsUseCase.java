package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.simulation.Installment;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static com.github.fabriciolfj.simulation.domain.common.ConstAmount.PERCENTAGE;

@Service
public class CreateInstallmentsUseCase {

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
                        installmentNumber == simulation.getNumberOfInstallments()
                ))
                .toList();
    }

    private BigDecimal calculateInterestAmount(final BigDecimal value, final Simulation simulation) {
        return value
                .multiply(simulation.getMonthlyInterestRate())
                .divide(BigDecimal.valueOf(PERCENTAGE), 2, RoundingMode.HALF_UP);
    }

    private Installment createInstallment(final int installmentNumber,
                                          final AtomicReference<BigDecimal> remainingBalance,
                                          final Simulation simulation,
                                          final boolean isLastInstallment) {

        BigDecimal interestAmount = calculateInterestAmount(remainingBalance.get(), simulation);

        BigDecimal principalAmount = simulation.getInstallmentAmount().subtract(interestAmount);

        if (isLastInstallment && remainingBalance.get().compareTo(BigDecimal.ZERO) != 0) {
            principalAmount = remainingBalance.get();
            interestAmount = simulation.getInstallmentAmount().subtract(principalAmount);
        }

        final BigDecimal newBalance = remainingBalance.get().subtract(principalAmount);
        remainingBalance.set(newBalance);

        return Installment.builder()
                .installmentNumber(installmentNumber)
                .installmentAmount(simulation.getInstallmentAmount())
                .interestAmount(interestAmount)
                .principalAmount(principalAmount)
                .remainingBalance(newBalance)
                .dueDate(simulation.calculateDueDate(installmentNumber))
                .build();
    }
}
