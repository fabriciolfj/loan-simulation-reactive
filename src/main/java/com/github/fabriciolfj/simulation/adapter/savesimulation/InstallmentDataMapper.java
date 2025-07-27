package com.github.fabriciolfj.simulation.adapter.savesimulation;

import com.github.fabriciolfj.simulation.adapter.data.InstallmentData;
import com.github.fabriciolfj.simulation.domain.simulation.Installment;

import java.util.List;

public class InstallmentDataMapper {

    private InstallmentDataMapper() { }

    public static List<InstallmentData> toData(final List<Installment> installments, final Long simulationIdentifier) {
        return installments
                .stream()
                .map(i -> InstallmentData
                        .builder()
                        .dueDate(i.getDueDate())
                        .installmentNumber(i.getInstallmentNumber())
                        .installmentAmount(i.getInstallmentAmount())
                        .interestAmount(i.getInterestAmount())
                        .paidAmount(i.getPaidAmount())
                        .paymentDate(i.getPaymentDate())
                        .principalAmount(i.getPrincipalAmount())
                        .remainingBalance(i.getRemainingBalance())
                        .simulationId(simulationIdentifier)
                        .build())
                .toList();
    }
}
