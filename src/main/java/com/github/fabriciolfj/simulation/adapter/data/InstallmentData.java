package com.github.fabriciolfj.simulation.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("installments")
public class InstallmentData {

    @Id
    private Long id;

    @Column("simulation_id")
    private Long simulationId;

    @Column("installment_number")
    private Integer installmentNumber;

    @Column("installment_amount")
    private BigDecimal installmentAmount;

    @Column("interest_amount")
    private BigDecimal interestAmount;

    @Column("principal_amount")
    private BigDecimal principalAmount;

    @Column("remaining_balance")
    private BigDecimal remainingBalance;

    @Column("due_date")
    private LocalDate dueDate;

    @Column("payment_date")
    private LocalDate paymentDate;

    @Column("paid_amount")
    private BigDecimal paidAmount;
}