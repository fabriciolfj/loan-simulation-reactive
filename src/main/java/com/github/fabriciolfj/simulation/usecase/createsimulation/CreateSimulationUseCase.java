package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationStatus;
import com.github.fabriciolfj.simulation.exceptions.clazz.BusinessException;
import com.github.fabriciolfj.simulation.usecase.findavaluatecustomer.FindAvaluateCustomerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.github.fabriciolfj.simulation.usecase.createsimulation.ProposalToSimulationMapper.toSimulation;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateSimulationUseCase {

    private final FindAvaluateCustomerGateway findAvaluateCustomerGateway;
    private final DefineStatusSimulationUseCase defineStatusSimulationUseCase;
    private final CalculateProposalUseCase calculateProposalUseCase;
    private final SaveSimulationGateway saveSimulationGateway;
    private final CreateInstallmentsUseCase createInstallmentsUseCase;

    public Mono<Simulation> execute(final Proposal proposal) {
        return findAvaluateCustomerGateway.execute(proposal.getCpfCustomer())
                .zipWhen(customer -> defineStatusSimulationUseCase.execute(customer, proposal))
                .flatMap(tuple -> calcAndMapperSimulation(tuple.getT1(), tuple.getT2(), proposal))
                .doOnNext(s -> log.info("simulation mapped {}", s.getCode()))
                .flatMap(createInstallmentsUseCase::execute)
                .flatMap(saveSimulationGateway::process);
    }

    private Mono<Simulation> calcAndMapperSimulation(final AvaluateCustomer avaluateCustomer, final SimulationStatus simulationStatus, final Proposal proposal) {
        return calculateProposalUseCase.execute(proposal, avaluateCustomer)
                .map(calc -> toSimulation(calc, simulationStatus, avaluateCustomer.getCustomer()));
    }
}
