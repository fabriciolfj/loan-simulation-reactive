package com.github.fabriciolfj.simulation.adapter.repositories;

import com.github.fabriciolfj.simulation.adapter.data.CustomerData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends R2dbcRepository<CustomerData, Long> {

    @Query("SELECT * FROM customers WHERE cpf = :cpf AND active = true")
    Mono<CustomerData> findByCpfAndActive(String cpf);
}
