package com.github.fabriciolfj.simulation.adapter.repositories;

import com.github.fabriciolfj.simulation.adapter.data.InstallmentData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface InstallmentRepository extends R2dbcRepository<InstallmentData, Long> {

    Flux<InstallmentData> findBySimulationIdOrderByInstallmentNumber(Long loanSimulationId);

    @Query("SELECT * FROM installments WHERE loan_simulation_id = :loanSimulationId " +
            "AND payment_status = 'PENDING' ORDER BY installment_number")
    Flux<InstallmentData> findPendingInstallments(Long loanSimulationId);

    @Query("SELECT * FROM installments WHERE due_date <= :date " +
            "AND payment_status = 'PENDING'")
    Flux<InstallmentData> findOverdueInstallments(LocalDate date);

    @Query("DELETE FROM installments WHERE loan_simulation_id = :loanSimulationId")
    Mono<Void> deleteByLoanSimulationId(Long loanSimulationId);
}
