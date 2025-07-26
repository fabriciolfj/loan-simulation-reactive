package com.github.fabriciolfj.simulation.entrypoint.routes;

import com.github.fabriciolfj.simulation.entrypoint.handler.SimulationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Component
public class RouterSimulation {

    private final static String URL_BASE = "/api/v1/simulation";

    @Bean
    public RouterFunction<ServerResponse> simulationRoutes(final SimulationHandler handler) {
        return RouterFunctions
                .route(POST(URL_BASE).and(accept(MediaType.APPLICATION_JSON)), handler::createSimulation)
                .andRoute(GET(URL_BASE).and(accept(MediaType.APPLICATION_JSON)), handler::createSimulation);
    }
}
