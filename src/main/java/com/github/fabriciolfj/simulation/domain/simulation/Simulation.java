package com.github.fabriciolfj.simulation.domain.simulation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simulation {

    private String code;
    private SimulationDetails details;
    private List<Installment> installments;
}
