package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import com.github.fabriciolfj.simulation.domain.proposal.Proposal;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import com.github.fabriciolfj.simulation.domain.simulation.SimulationStatus;
import com.github.fabriciolfj.simulation.usecase.findcustomer.FindCustomerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.github.fabriciolfj.simulation.usecase.createsimulation.ProposalToSimulationMapper.toSimulation;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateSimulationUseCase {

    private final FindCustomerGateway findCustomerGateway;
    private final SaveSimulationGateway saveSimulationGateway;
    private final DefineStatusSimulationUseCase defineStatusSimulationUseCase;
    private final CalculateProposalUseCase calculateProposalUseCase;

    public Mono<Simulation> execute(final Proposal proposal) {
        return findCustomerGateway.execute(proposal.getCpfCustomer())
                .zipWhen(customer -> defineStatusSimulationUseCase.execute(customer, proposal))
                .flatMap(tuple -> mapperSimulation(tuple.getT1(), tuple.getT2(), proposal))
                .doOnNext(s -> log.info("simulation mapped {}", s.getCode()))
                .flatMap(saveSimulationGateway::process);

    }

    private Mono<Simulation> mapperSimulation(final Customer customer, final SimulationStatus simulationStatus, final Proposal proposal) {
        return calculateProposalUseCase.execute(proposal, customer)
                .map(calc -> ProposalToSimulationMapper.toSimulation(calc, simulationStatus, customer));
    }
}
