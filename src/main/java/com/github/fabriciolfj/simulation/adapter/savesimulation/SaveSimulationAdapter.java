package com.github.fabriciolfj.simulation.adapter.savesimulation;

import com.github.fabriciolfj.simulation.adapter.repositories.InstallmentRepository;
import com.github.fabriciolfj.simulation.adapter.repositories.SimulationRepository;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import com.github.fabriciolfj.simulation.usecase.createsimulation.SaveSimulationGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class SaveSimulationAdapter implements SaveSimulationGateway {

    private final SimulationRepository simulationRepository;
    private final InstallmentRepository installmentRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Mono<Simulation> process(Simulation simulation) {
        return Mono.just(simulation)
                .map(s -> SimulationDataMapper.toData(s.getDetails(), s.getCode()))
                .flatMap(simulationRepository::save)
                .map(c -> InstallmentDataMapper.toData(simulation.getInstallments(), c.getId()))
                .map(installmentRepository::saveAll)
                .then(Mono.just(simulation));
    }
}
