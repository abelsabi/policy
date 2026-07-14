package com.assignment.policy.dto.response;

import com.assignment.policy.enums.PaymentFrequency;
import com.assignment.policy.enums.ProposalStatus;

import java.math.BigDecimal;

public record ProposalResponse(

        Long id,

        Long customerId,

        Integer policyTerm,

        BigDecimal annualPremium,

        BigDecimal sumAssured,

        String nomineeName,

        PaymentFrequency paymentFrequency,

        ProposalStatus status,

        String policyNumber

) {
}