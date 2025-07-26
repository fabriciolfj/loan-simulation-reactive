package com.github.fabriciolfj.simulation.adapter.findcustomer;

import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;
import com.github.fabriciolfj.simulation.usecase.findcustomer.FindCustomerGateway;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindCustomerAdapter implements FindCustomerGateway {

    @Override
    public Mono<AvaluateCustomer> execute(String cpf) {
        return null;
    }
}
