package com.github.fabriciolfj.simulation.adapter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("simulations")
public class SimulationData {

    @Id
    private Long id;

    @Column("code")
    private String code;

    @Column("customer_id")
    private Long customerId;

    @Column("requested_amount")
    private BigDecimal requestedAmount;

    @Column("number_of_installments")
    private Integer numberOfInstallments;

    @Column("monthly_interest_rate")
    private BigDecimal monthlyInterestRate;

    @Column("installment_amount")
    private BigDecimal installmentAmount;

    @Column("total_amount_to_pay")
    private BigDecimal totalAmountToPay;

    @Column("status")
    private String status;

    @Column("simulation_date")
    private LocalDateTime simulationDate;

    @Column("approved_date")
    private LocalDateTime approvedDate;

    @Column("approved_by")
    private String approvedBy;

    @Column("active")
    private Boolean active;
}
