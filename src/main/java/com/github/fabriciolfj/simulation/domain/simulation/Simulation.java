package com.github.fabriciolfj.simulation.domain.simulation;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.github.fabriciolfj.simulation.domain.common.ConstAmount.PERCENTAGE;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Simulation {

    private String code;
    private SimulationDetails details;
    private List<Installment> installments;
    
    public Simulation updateInstallments(final List<Installment> installments) {
        this.installments = installments;
        return this;
    }

    public boolean isApproved() {
        return getStatus().equals(SimulationStatus.APPROVED);
    }
    
    public LocalDate calculateDueDate(int installmentNumber) {
        return LocalDate.now().plusMonths(installmentNumber);
    }

    public BigDecimal getMonthlyInterestRate() {
        return this.details.getMonthlyInterestRate();
    }

    public int getNumberOfInstallments() {
        return this.details.getNumberOfInstallments();
    }

    public BigDecimal calcSubtractInstallmentAmount(final BigDecimal interestAmount) {
        return getInstallmentAmount().subtract(interestAmount);
    }

    public BigDecimal calculateInterestAmount(final BigDecimal value) {
        return value
                .multiply(getMonthlyInterestRate())
                .divide(BigDecimal.valueOf(PERCENTAGE), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getInstallmentAmount() {
        return this.details.getInstallmentAmount();
    }

    public LocalDate getSimulationDate() {
        return this.details.getSimulationDate().toLocalDate();
    }

    public BigDecimal getRequestedAmount() {
        return this.details.getRequestedAmount();
    }

    private SimulationStatus getStatus() {
        return this.details.getStatus();
    }
}
