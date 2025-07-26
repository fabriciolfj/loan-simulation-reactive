package com.github.fabriciolfj.simulation.entrypoint.createsimulation.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LoanSimulationCompleteResponseDTO {
    private Long id;
    private String customer;
    private BigDecimal requestedAmount;
    private Integer numberOfInstallments;
    private BigDecimal monthlyInterestRate;
    private BigDecimal installmentAmount;
    private BigDecimal totalAmountToPay;
    private String status;
    private String notes;
    private LocalDateTime simulationDate;
    private LocalDateTime approvedDate;
    private String approvedBy;
    private List<InstallmentDTO> installments;
}
