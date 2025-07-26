package com.github.fabriciolfj.simulation.domain.proposal;

import com.github.fabriciolfj.simulation.exceptions.clazz.CalculationProposalNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proposal {

    private ProposalDetails details;
    private String cpfCustomer;
    private CalculationProposal calculationProposal;

    public String getCode() {
        return details.getCode();
    }

    public BigDecimal getRequestAmount() {
        return this.details.getRequestedAmount();
    }

    public Integer getInstallments() {
        return this.details.getNumberOfInstallments();
    }

    public LocalDateTime getSimulationDate() {
        return this.details.getSimulationDate();
    }

    public Proposal addCalculation(final CalculationProposal calculationProposal) {
        this.calculationProposal = calculationProposal;
        return this;
    }

    public CalculationProposal getCalculationProposal() {
        if (calculationProposal == null) {
            throw new CalculationProposalNotFoundException();
        }

        return this.calculationProposal;
    }

    public BigDecimal getTotalAmount() {
        return calculationProposal.getInstallmentAmount()
                .multiply(BigDecimal.valueOf(details.getNumberOfInstallments()));
    }
}
