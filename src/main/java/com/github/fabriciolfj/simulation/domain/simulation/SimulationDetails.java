package com.github.fabriciolfj.simulation.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationDetails {
    private String code;
    private BigDecimal requestedAmount;
    private Integer numberOfInstallments;
    private BigDecimal monthlyInterestRate;
    private BigDecimal installmentAmount;
    private BigDecimal totalAmountToPay;
    private SimulationStatus status;
    private LocalDateTime simulationDate;
    private LocalDateTime approvedDate;
    private String approvedBy;
    private Boolean active;
}
