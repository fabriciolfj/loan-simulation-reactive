package com.github.fabriciolfj.simulation.domain.evaluatecustomer;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import com.github.fabriciolfj.simulation.domain.common.Parameter;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class AvaluateCustomer {

    private Customer customer;
    private Parameter parameter;

    public boolean isCustomerValid(final BigDecimal amount, final int numberOfInstallments) {
        var validations = List.of(isMonthlyIncomeValid(amount, numberOfInstallments), isScoreValid(), isScoreToAmountValid(amount));

        return validations.stream().allMatch(p -> p);
    }

    private boolean isMonthlyIncomeValid(final BigDecimal value, final int numberOfInstallments) {
        var incomeCommitment = customer.getMonthlyIncomeValid(value, numberOfInstallments);

        return incomeCommitment.compareTo(parameter.getCommittment()) < 0;
    }

    private boolean isScoreValid() {
        return customer.getCreditScore() > parameter.getScoreNegative();
    }

    private boolean isScoreToAmountValid(final BigDecimal value) {
        return value.compareTo(parameter.getAmountCustomerHigh()) > 0 && isScoreMinorMedium();
    }

    private boolean isScoreMinorMedium() {
        return this.customer.getCreditScore() >= parameter.getMediumScore();
    }
}
