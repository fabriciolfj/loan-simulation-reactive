package com.github.fabriciolfj.simulation.domain.proposal;

import com.github.fabriciolfj.simulation.domain.common.ConstRates;
import com.github.fabriciolfj.simulation.domain.simulation.Parameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.github.fabriciolfj.simulation.domain.common.ConstAmount.*;
import static com.github.fabriciolfj.simulation.domain.common.ConstRates.ADDITIONAL;
import static com.github.fabriciolfj.simulation.domain.common.ConstRates.ADDITIONAL_AMOUNT;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proposal {

    private ProposalDetails details;

    public String getCode() {
        return details.getCode();
    }

    public BigDecimal getRequestAmount() {
        return this.details.getRequestedAmount();
    }

    public Integer getInstallments() {
        return this.details.getNumberOfInstallments();
    }

    public BigDecimal calculateInterestRate(final int score) {
        final BigDecimal baseRate = additionalRate(startCalculateRate(score));

        return baseRate.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateInstallmentAmount(final BigDecimal rate) {
        final BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(PERCENTAGE), 6, RoundingMode.HALF_UP);
        final BigDecimal onePlusRate = BigDecimal.ONE.add(monthlyRate);
        final BigDecimal power = onePlusRate.pow(getInstallments());

        final BigDecimal numerator = monthlyRate.multiply(power);
        final BigDecimal denominator = power.subtract(BigDecimal.ONE);

        if (denominator.compareTo(BigDecimal.ZERO) == 0) {
            return getRequestAmount().divide(new BigDecimal(getInstallments()), 2, RoundingMode.HALF_UP);
        }

        final BigDecimal factor = numerator.divide(denominator, 6, RoundingMode.HALF_UP);
        return getRequestAmount().multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal startCalculateRate(final int score) {
        var parameter = Parameters.getParameters()
                .stream()
                .sorted()
                .filter(p -> score >= p.getScore())
                .findFirst()
                .orElse(Parameters.getDefault());

        return ConstRates.DEFAULT.subtract(parameter.getRate());
    }

    private BigDecimal additionalRate(BigDecimal baseRate) {
        if (getRequestAmount().compareTo(AMOUNT_HIGH) > 0) {
            baseRate = baseRate.add(ADDITIONAL_AMOUNT);
        }

        return additionalRateInstallments(baseRate);
    }

    private BigDecimal additionalRateInstallments(BigDecimal baseRate) {
        if (getInstallments() > INSTALLMENTS_HIGH) {
            baseRate = baseRate.add(ADDITIONAL);
        }

        return baseRate;
    }
}
