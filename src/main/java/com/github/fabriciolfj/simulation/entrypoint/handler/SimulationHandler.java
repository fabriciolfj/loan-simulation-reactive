package com.github.fabriciolfj.simulation.entrypoint.handler;

import com.github.fabriciolfj.simulation.entrypoint.dto.request.LoanSimulationRequestDTO;
import com.github.fabriciolfj.simulation.exceptions.handler.ExceptionHandler;
import com.github.fabriciolfj.simulation.usecase.createsimulation.CreateSimulationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SimulationHandler {

    private final ValidationHandler validationHandler;
    private final ExceptionHandler exceptionHandler;
    private final CreateSimulationUseCase createSimulationUseCase;

    public Mono<ServerResponse> createSimulation(ServerRequest request) {
        return request.bodyToMono(LoanSimulationRequestDTO.class)
                .flatMap(validationHandler::validate)
                .flatMap(createSimulationUseCase::execute)
                .flatMap(simulation -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(simulation))
                .onErrorResume(exceptionHandler::handler);
    }
}
