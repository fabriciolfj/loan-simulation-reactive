package com.github.fabriciolfj.simulation.adapter.repositories;

import com.github.fabriciolfj.simulation.adapter.data.SimulationData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface SimulationRepository extends R2dbcRepository<SimulationData, Long> {

    Flux<SimulationData> findByCustomerId(Long customerId);

    @Query("SELECT ls.* FROM loan_simulations ls " +
            "INNER JOIN customers c ON ls.customer_id = c.id " +
            "WHERE c.cpf = :cpf AND ls.active = true " +
            "ORDER BY ls.simulation_date DESC")
    Flux<SimulationData> findByCustomerSsn(String cpf);

    @Query("SELECT * FROM loan_simulations WHERE status = :status AND active = true")
    Flux<SimulationData> findByStatus(String status);

    @Query("SELECT * FROM loan_simulations WHERE customer_id = :customerId " +
            "AND status IN ('APPROVED', 'CONTRACTED') AND active = true")
    Flux<SimulationData> findApprovedSimulationsByCustomerId(Long customerId);
}
