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
@Table("customers")
public class CustomerData {
    @Id
    private Long id;

    @Column("cpf")
    private String cpf;

    @Column("name")
    private String name;

    @Column("monthly_income")
    private BigDecimal monthlyIncome;

    @Column("credit_score")
    private Integer creditScore;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("active")
    private Boolean active;
}