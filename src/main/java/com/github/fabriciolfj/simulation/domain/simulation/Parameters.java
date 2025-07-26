package com.github.fabriciolfj.simulation.domain.simulation;

import com.github.fabriciolfj.simulation.domain.common.ConstRates;
import com.github.fabriciolfj.simulation.domain.common.ConstScores;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Getter
@AllArgsConstructor
public class Parameters implements Comparator<Integer> {

    private int score;
    private BigDecimal rate;

    public static Parameters getDefault() {
        return new Parameters(ConstScores.LOW, ConstRates.HIGH);
    }

    public static List<Parameters> getParameters() {
        return List.of(
                new Parameters(ConstScores.HIGH, ConstRates.LOW),
                new Parameters(ConstScores.MEDIUM, ConstRates.MEDIUM),
                new Parameters(ConstScores.LOW, ConstRates.HIGH)
        );
    }

    @Override
    public int compare(Integer one, Integer two) {
        return two.compareTo(one);
    }
}
