package com.github.fabriciolfj.simulation.usecase.findavaluatecustomer;

import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;
import reactor.core.publisher.Mono;

public interface FindAvaluateCustomerGateway {

    Mono<AvaluateCustomer> execute(final String cpf);
}
