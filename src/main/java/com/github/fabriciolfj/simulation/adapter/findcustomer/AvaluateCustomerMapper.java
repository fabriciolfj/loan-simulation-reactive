package com.github.fabriciolfj.simulation.adapter.findcustomer;

import com.github.fabriciolfj.simulation.adapter.data.CustomerData;
import com.github.fabriciolfj.simulation.domain.common.Customer;
import com.github.fabriciolfj.simulation.domain.common.Parameter;
import com.github.fabriciolfj.simulation.domain.evaluatecustomer.AvaluateCustomer;

public class AvaluateCustomerMapper {

    private AvaluateCustomerMapper() { }

    public static AvaluateCustomer toEntity(final CustomerData customerData, final Parameter parameter) {
        return AvaluateCustomer.builder()
                .parameter(parameter)
                .customer(Customer
                        .builder()
                        .cpf(customerData.getCpf())
                        .name(customerData.getName())
                        .creditScore(customerData.getCreditScore())
                        .active(customerData.getActive())
                        .identifier(customerData.getId())
                        .monthlyIncome(customerData.getMonthlyIncome())
                        .build())
                .build();
    }
}
