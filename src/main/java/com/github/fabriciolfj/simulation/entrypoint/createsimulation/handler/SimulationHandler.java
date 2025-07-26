package com.github.fabriciolfj.simulation.entrypoint.createsimulation.handler;

import com.github.fabriciolfj.simulation.entrypoint.common.ValidationHandler;
import com.github.fabriciolfj.simulation.entrypoint.createsimulation.dto.request.LoanSimulationRequestDTO;
import com.github.fabriciolfj.simulation.entrypoint.createsimulation.mapper.ProposalMapper;
import com.github.fabriciolfj.simulation.exceptions.handler.ExceptionHandler;
import com.github.fabriciolfj.simulation.usecase.createsimulation.CreateSimulationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimulationHandler {

    private final ValidationHandler validationHandler;
    private final ExceptionHandler exceptionHandler;
    private final CreateSimulationUseCase createSimulationUseCase;

    public Mono<ServerResponse> createSimulation(ServerRequest request) {
        return request.bodyToMono(LoanSimulationRequestDTO.class)
                .flatMap(validationHandler::validate)
                .map(ProposalMapper::toEntity)
                .doOnNext(p -> log.info("proposal created to {}", p))
                .flatMap(createSimulationUseCase::execute)
                .doOnNext(s -> log.info("simulation created to {}", s))
                .flatMap(simulation -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(simulation))
                .onErrorResume(exceptionHandler::handler);
    }
}
