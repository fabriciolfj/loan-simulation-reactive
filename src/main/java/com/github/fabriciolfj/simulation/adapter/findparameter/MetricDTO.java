package com.github.fabriciolfj.simulation.adapter.findparameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricDTO {

    private int score;
    private BigDecimal rate;
}
