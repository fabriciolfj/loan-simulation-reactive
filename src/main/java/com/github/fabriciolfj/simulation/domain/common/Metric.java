package com.github.fabriciolfj.simulation.domain.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Metric implements Comparable<Metric> {

    private int score;
    private BigDecimal rate;

    @Override
    public int compareTo(@NotNull Metric other) {
        return Integer.compare(other.score, score);
    }
}
