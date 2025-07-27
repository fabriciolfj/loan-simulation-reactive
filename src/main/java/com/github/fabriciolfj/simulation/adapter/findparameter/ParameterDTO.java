package com.github.fabriciolfj.simulation.adapter.findparameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParameterDTO {
    @JsonProperty("amount_high")
    private BigDecimal amountHigh;
    @JsonProperty("amount_customer_high")
    private BigDecimal amountCustomerHigh;
    @JsonProperty("score_negative")
    private Integer scoreNegative;
    @JsonProperty("installments_high")
    private Integer installmentsHigh;
    @JsonProperty("committment")
    private BigDecimal commitment;
    @JsonProperty("medium_score")
    private Integer mediumScore;
    @JsonProperty("rate_default")
    private BigDecimal rateDefault;
    @JsonProperty("additional_amount")
    private BigDecimal additionalAmount;
    @JsonProperty("metrics")
    private List<MetricDTO> metrics;
}
