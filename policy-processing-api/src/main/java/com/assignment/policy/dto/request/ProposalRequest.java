package com.assignment.policy.dto.request;

import com.assignment.policy.enums.PaymentFrequency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProposalRequest(

        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotNull(message = "Policy term is required")
        Integer policyTerm,

        @NotNull(message = "Annual premium is required")
        @DecimalMin(value = "5000.00", inclusive = true,
                message = "Annual premium must be at least ₹5,000")
        BigDecimal annualPremium,

        @NotNull(message = "Sum assured is required")
        BigDecimal sumAssured,

        @NotBlank(message = "Nominee name is required")
        String nomineeName,

        @NotNull(message = "Payment frequency is required")
        PaymentFrequency paymentFrequency

) {
}