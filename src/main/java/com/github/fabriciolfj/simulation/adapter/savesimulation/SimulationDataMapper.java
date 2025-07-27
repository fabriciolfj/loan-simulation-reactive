package com.github.fabriciolfj.simulation.adapter.savesimulation;

import com.github.fabriciolfj.simulation.adapter.data.SimulationData;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationDetails;

public class SimulationDataMapper {

    private SimulationDataMapper() { }

    private static final String USER = "system";

    public static SimulationData toData(final SimulationDetails details, final String code) {
        return SimulationData
                .builder()
                .code(code)
                .approvedDate(details.getApprovedDate())
                .customerId(details.getIdentifierCustomer())
                .approvedBy(USER)
                .simulationDate(details.getSimulationDate())
                .requestedAmount(details.getRequestedAmount())
                .status(details.getStatus().name())
                .totalAmountToPay(details.getTotalAmountToPay())
                .installmentAmount(details.getInstallmentAmount())
                .active(details.getActive())
                .numberOfInstallments(details.getNumberOfInstallments())
                .monthlyInterestRate(details.getMonthlyInterestRate())
                .build();
    }
}
