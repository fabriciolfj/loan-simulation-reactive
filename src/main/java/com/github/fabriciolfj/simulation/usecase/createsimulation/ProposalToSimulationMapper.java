package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationDetails;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationStatus;

import java.time.LocalDateTime;
import java.util.Collections;

public class ProposalToSimulationMapper {

    private ProposalToSimulationMapper() { }

    public static Simulation toSimulation(final Proposal proposal, final SimulationStatus status, final Customer customer) {
        return Simulation
                .builder()
                .code(proposal.getCode())
                .installments(Collections.emptyList())
                .details(toDetails(proposal, status, customer))
                .build();
    }

    private static SimulationDetails toDetails(final Proposal proposal, final SimulationStatus status, final Customer customer) {
        var calc = proposal.getCalculationProposal();
        return SimulationDetails
                .builder()
                .active(true)
                .simulationDate(proposal.getSimulationDate())
                .approvedDate(LocalDateTime.now())
                .installmentAmount(calc.getInstallmentAmount())
                .totalAmountToPay(proposal.getTotalAmount())
                .numberOfInstallments(proposal.getInstallments())
                .requestedAmount(proposal.getRequestAmount())
                .monthlyInterestRate(calc.getInterestRate())
                .status(status)
                .identifierCustomer(customer.getIdentifier())
                .build();
    }
}
