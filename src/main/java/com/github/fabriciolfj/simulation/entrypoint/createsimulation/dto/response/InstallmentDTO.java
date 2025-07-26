package com.github.fabriciolfj.simulation.entrypoint.createsimulation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstallmentDTO {
    private Long id;
    private Integer installmentNumber;
    private BigDecimal installmentAmount;
    private BigDecimal interestAmount;
    private BigDecimal principalAmount;
    private BigDecimal remainingBalance;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal paidAmount;
}