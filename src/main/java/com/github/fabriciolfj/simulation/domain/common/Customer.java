package com.github.fabriciolfj.simulation.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.github.fabriciolfj.simulation.domain.common.ConstAmount.AMOUNT_CUSTOMER_HIGH;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private static final BigDecimal VALUE_MAX_COMMITMENT = BigDecimal.valueOf(0.4);

    private String cpf;
    private Long identifier;
    private String name;
    private BigDecimal monthlyIncome;
    private Integer creditScore;
    private Boolean active;

    public boolean isCustomerValid(final BigDecimal amount, final int numberOfInstallments) {
        var validations = List.of(isMonthlyIncomeValid(amount, numberOfInstallments), isScoreValid(), isScoreToAmountValid(amount));

        return validations.stream().allMatch(p -> p.booleanValue());
    }

    private boolean isMonthlyIncomeValid(final BigDecimal value, final int numberOfInstallments) {
        if (Objects.isNull(monthlyIncome)) {
            return false;
        }

        final BigDecimal incomeCommitment = value
                .divide(new BigDecimal(numberOfInstallments), 2, RoundingMode.HALF_UP)
                .divide(monthlyIncome, 4, RoundingMode.HALF_UP);

        return incomeCommitment.compareTo(VALUE_MAX_COMMITMENT) < 0;
    }


    private boolean isScoreValid() {
        return this.creditScore > ConstScores.NEGATIVE;
    }

    private boolean isScoreToAmountValid(final BigDecimal value) {
        return value.compareTo(AMOUNT_CUSTOMER_HIGH) > 0 && isScoreMinorMedium();
    }

    private boolean isScoreMinorMedium() {
        return this.creditScore >= ConstScores.MEDIUM;
    }
}