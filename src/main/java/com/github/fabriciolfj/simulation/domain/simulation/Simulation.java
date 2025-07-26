package com.github.fabriciolfj.simulation.domain.simulation;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simulation {

    private SimulationDetails details;
    private List<Installment> installments;
    private Customer customer;


}
