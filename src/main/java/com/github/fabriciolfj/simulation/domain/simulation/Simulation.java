package com.github.fabriciolfj.simulation.domain.simulation;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


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

    public BigDecimal getInstallmentAmount() {
        return this.details.getInstallmentAmount();
    }

    private SimulationStatus getStatus() {
        return this.details.getStatus();
    }

    public BigDecimal getRequestedAmount() {
        return this.details.getRequestedAmount();
    }
}
