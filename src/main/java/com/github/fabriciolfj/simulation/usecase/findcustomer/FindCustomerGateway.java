package com.github.fabriciolfj.simulation.usecase.findcustomer;

import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;
import reactor.core.publisher.Mono;

public interface FindCustomerGateway {

    Mono<AvaluateCustomer> execute(final String cpf);
}
