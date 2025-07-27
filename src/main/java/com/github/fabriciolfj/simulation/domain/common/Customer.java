package com.github.fabriciolfj.simulation.domain.common;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String cpf;
    private Long identifier;
    private String name;
    private BigDecimal monthlyIncome;
    private Integer creditScore;
    private Boolean active;

    public BigDecimal getMonthlyIncomeValid(final BigDecimal value, final int numberOfInstallments) {
        if (Objects.isNull(monthlyIncome)) {
            return BigDecimal.ZERO;
        }

        return value
                .divide(new BigDecimal(numberOfInstallments), 2, RoundingMode.HALF_UP)
                .divide(monthlyIncome, 4, RoundingMode.HALF_UP);
    }
}