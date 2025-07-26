package com.github.fabriciolfj.simulation.entrypoint.createsimulation.mapper;

import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.domain.proposal.ProposalDetails;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationStatus;
import com.github.fabriciolfj.simulation.entrypoint.createsimulation.dto.request.LoanSimulationRequestDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProposalMapper {

    private ProposalMapper() { }

    public static Proposal toEntity(final LoanSimulationRequestDTO dto) {
        return Proposal.builder()
                .details(toDetails(dto))
                .cpfCustomer(dto.getCustomerCpf())
                .build();
    }

    private static ProposalDetails toDetails(final LoanSimulationRequestDTO dto) {
        return ProposalDetails.builder()
                .code(UUID.randomUUID().toString())
                .status(SimulationStatus.PENDING)
                .requestedAmount(dto.getRequestedAmount())
                .simulationDate(LocalDateTime.now())
                .numberOfInstallments(dto.getNumberOfInstallments())
                .build();
    }
}
