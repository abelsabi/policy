package com.assignment.policy.util;

import com.assignment.policy.dto.response.ProposalResponse;
import com.assignment.policy.entity.Proposal;

public final class ProposalMapper {

    private ProposalMapper() {
    }

    public static ProposalResponse toResponse(Proposal proposal) {

        return new ProposalResponse(
                proposal.getId(),
                proposal.getCustomerId(),
                proposal.getPolicyTerm(),
                proposal.getAnnualPremium(),
                proposal.getSumAssured(),
                proposal.getNomineeName(),
                proposal.getPaymentFrequency(),
                proposal.getStatus(),
                proposal.getPolicyNumber()
        );
    }
}