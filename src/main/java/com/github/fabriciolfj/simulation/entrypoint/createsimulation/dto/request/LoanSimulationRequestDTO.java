package com.github.fabriciolfj.simulation.entrypoint.createsimulation.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanSimulationRequestDTO {

    @NotNull(message = "{loan.amount.required}")
    @DecimalMin(value = "100.00", message = "{loan.amount.minimum}")
    @DecimalMax(value = "500000.00", message = "{loan.amount.maximum}")
    private BigDecimal requestedAmount;

    @NotNull(message = "{loan.installments.required}")
    @Min(value = 1, message = "{loan.installments.minimum}")
    @Max(value = 72, message = "{loan.installments.maximum}")
    private Integer numberOfInstallments;

    @NotBlank(message = "{customer.cpf.required}")
    @Pattern(regexp = "\\d{9}", message = "{customer.cpf.format}")
    private String customerCpf;

    @NotBlank(message = "{customer.name.required}")
    @Size(min = 2, max = 100, message = "{customer.name.size}")
    private String customerName;

    @Email(message = "{customer.email.format}")
    private String customerEmail;

    private String customerPhone;

    @DecimalMin(value = "0.00", message = "{customer.income.positive}")
    private BigDecimal monthlyIncome;
}