package com.github.fabriciolfj.simulation.domain.proposal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalculationProposal {

    private BigDecimal interestRate;
    private BigDecimal installmentAmount;
}
