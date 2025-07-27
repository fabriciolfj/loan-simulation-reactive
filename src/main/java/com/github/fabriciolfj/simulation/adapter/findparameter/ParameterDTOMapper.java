package com.github.fabriciolfj.simulation.adapter.findparameter;

import com.github.fabriciolfj.simulation.domain.common.Metric;
import com.github.fabriciolfj.simulation.domain.common.Parameter;

public class ParameterDTOMapper {

    private ParameterDTOMapper() { }

    public static Parameter toEntity(final ParameterDTO dto) {
        return Parameter.builder()
                .additionalAmount(dto.getAdditionalAmount())
                .amountCustomerHigh(dto.getAmountCustomerHigh())
                .amountHigh(dto.getAmountHigh())
                .committment(dto.getCommitment())
                .rateDefault(dto.getRateDefault())
                .mediumScore(dto.getMediumScore())
                .scoreNegative(dto.getScoreNegative())
                .metrics(dto.getMetrics().stream().map(metric -> Metric.builder()
                        .rate(metric.getRate())
                        .score(metric.getScore())
                        .build()).toList())
                .build();
    }
}
