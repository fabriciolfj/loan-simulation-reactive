package com.github.fabriciolfj.simulation.adapter.savesimulation;

import com.github.fabriciolfj.simulation.adapter.repositories.InstallmentRepository;
import com.github.fabriciolfj.simulation.adapter.repositories.SimulationRepository;
import com.github.fabriciolfj.simulation.domain.simulation.Simulation;
import com.github.fabriciolfj.simulation.exceptions.clazz.SaveSimulationException;
import com.github.fabriciolfj.simulation.usecase.createsimulation.SaveSimulationGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import static com.github.fabriciolfj.simulation.adapter.savesimulation.InstallmentDataMapper.toData;
import static com.github.fabriciolfj.simulation.adapter.savesimulation.SimulationDataMapper.toData;

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
                .map(s -> toData(s.getDetails(), s.getCode()))
                .flatMap(simulationRepository::save)
                .map(c -> toData(simulation.getInstallments(), c.getId()))
                .map(installmentRepository::saveAll)
                .doOnError(err -> log.error("fail save simulation {}, details {}", simulation.getCode(), err.getMessage()))
                .onErrorResume(err -> Mono.error(new SaveSimulationException()))
                .then(Mono.just(simulation));
    }
}
