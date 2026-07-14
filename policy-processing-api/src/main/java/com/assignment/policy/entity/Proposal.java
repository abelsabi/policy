package com.assignment.policy.entity;

import com.assignment.policy.enums.PaymentFrequency;
import com.assignment.policy.enums.ProposalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proposal {

    private Long id;

    private Long customerId;

    private Integer policyTerm;

    private BigDecimal annualPremium;

    private BigDecimal sumAssured;

    private String nomineeName;

    private PaymentFrequency paymentFrequency;

    private ProposalStatus status;

    private String policyNumber;
}