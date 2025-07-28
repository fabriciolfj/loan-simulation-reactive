package com.github.fabriciolfj.simulation.domain.proposal;

import com.github.fabriciolfj.simulation.exceptions.clazz.CalculationProposalNotFoundException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Proposal {

    @ToString.Include
    private ProposalDetails details;
    @ToString.Include
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
