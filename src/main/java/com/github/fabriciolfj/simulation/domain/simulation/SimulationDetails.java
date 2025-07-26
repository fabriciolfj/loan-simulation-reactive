package com.github.fabriciolfj.simulation.domain.simulation;

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
public class SimulationDetails {
    private BigDecimal requestedAmount;
    private Integer numberOfInstallments;
    private BigDecimal monthlyInterestRate;
    private BigDecimal installmentAmount;
    private BigDecimal totalAmountToPay;
    private SimulationStatus status;
    private LocalDateTime simulationDate;
    private LocalDateTime approvedDate;
    private Boolean active;
    private Long identifierCustomer;
}
