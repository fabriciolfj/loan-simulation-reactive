package com.github.fabriciolfj.simulation.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parameter {

    private BigDecimal amountHigh;
    private BigDecimal amountCustomerHigh;
    private BigDecimal committment;
    private BigDecimal rateDefault;
    private BigDecimal additionalAmount;

    private int installmentsHigh;
    private int scoreNegative;
    private int mediumScore;
    private List<Metric> metrics;
}
