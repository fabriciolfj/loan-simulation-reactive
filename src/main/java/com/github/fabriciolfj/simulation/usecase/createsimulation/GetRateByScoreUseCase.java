package com.github.fabriciolfj.simulation.usecase.createsimulation;

import com.github.fabriciolfj.simulation.domain.common.Customer;
import com.github.fabriciolfj.simulation.domain.common.Metric;
import com.github.fabriciolfj.simulation.domain.common.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Service
public class GetRateByScoreUseCase {

    public Mono<BigDecimal> execute(final Parameter parameter, final Customer customer) {
        return Flux.fromIterable(parameter.getMetrics())
                .sort()
                .filter(p -> p.getScore() >= customer.getCreditScore())
                .map(Metric::getRate)
                .doOnNext(value -> log.info("rate find {}", value))
                .next()
                .switchIfEmpty(Mono.just(parameter.getRateDefault()))
                .doOnNext(value -> log.info("rate default {}", value));
    }
}
