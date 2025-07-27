package com.github.fabriciolfj.simulation.adapter.findcustomer;

import com.github.fabriciolfj.simulation.adapter.findparameter.FindParameterAdapter;
import com.github.fabriciolfj.simulation.adapter.repositories.CustomerRepository;
import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;
import com.github.fabriciolfj.simulation.exceptions.clazz.CustomerException;
import com.github.fabriciolfj.simulation.usecase.findavaluatecustomer.FindAvaluateCustomerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.github.fabriciolfj.simulation.adapter.findcustomer.AvaluateCustomerMapper.toEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAvaluateCustomerAdapter implements FindAvaluateCustomerGateway {

    private final CustomerRepository repository;
    private final FindParameterAdapter findParameterAdapter;

    @Override
    public Mono<AvaluateCustomer> execute(String cpf) {
        return repository.findByCpfAndActive(cpf)
                .doOnError(err -> log.error("fail get customer {}, details {}", cpf, err.getMessage()))
                .onErrorResume(err -> Mono.error(new CustomerException()))
                .doOnNext(c -> log.info("customer found {}", c.getId()))
                .zipWhen(customer -> findParameterAdapter.execute())
                .map(tuple -> toEntity(tuple.getT1(), tuple.getT2()));
    }
}
