package com.github.fabriciolfj.simulation.usecase.findcustomer;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import reactor.core.publisher.Mono;

public interface FindCustomerGateway {

    Mono<Customer> execute(final String cpf);
}
