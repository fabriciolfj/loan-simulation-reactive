package com.github.fabriciolfj.simulation.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Installment {
    private Integer installmentNumber;
    private BigDecimal installmentAmount;
    private BigDecimal interestAmount;
    private BigDecimal principalAmount;
    private BigDecimal remainingBalance;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal paidAmount;
}