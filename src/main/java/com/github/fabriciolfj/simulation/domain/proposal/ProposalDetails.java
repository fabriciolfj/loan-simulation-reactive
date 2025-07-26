package com.github.fabriciolfj.simulation.domain.proposal;

import com.github.fabriciolfj.simulation.domain.simulation.SimulationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDetails {

    private String code;
    private BigDecimal requestedAmount;
    private Integer numberOfInstallments;
    private SimulationStatus status;
    private LocalDateTime simulationDate;
}
