package com.assignment.policy.service;

import com.assignment.policy.dto.request.ProposalRequest;
import com.assignment.policy.dto.response.ProposalResponse;
import com.assignment.policy.entity.Customer;
import com.assignment.policy.entity.Proposal;
import com.assignment.policy.enums.ProposalStatus;
import com.assignment.policy.exception.BusinessValidationException;
import com.assignment.policy.exception.ResourceNotFoundException;
import com.assignment.policy.repository.ProposalRepository;
import com.assignment.policy.util.AppConstants;
import com.assignment.policy.util.ProposalMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final CustomerService customerService;
    private final ReferenceMasterService referenceMasterService;
    private final AuditService auditService;

    /**
     * Creates a proposal in DRAFT state.
     */
    public ProposalResponse createProposal(ProposalRequest request) {

        Customer customer =
                customerService.getCustomerEntity(request.customerId());

        validateProposal(request, customer);

        Proposal proposal = Proposal.builder()
                .customerId(customer.getId())
                .policyTerm(request.policyTerm())
                .annualPremium(request.annualPremium())
                .sumAssured(request.sumAssured())
                .nomineeName(request.nomineeName())
                .paymentFrequency(request.paymentFrequency())
                .status(ProposalStatus.DRAFT)
                .build();

        Proposal savedProposal = proposalRepository.save(proposal);

        log.info("Proposal {} created successfully",
                savedProposal.getId());

        return ProposalMapper.toResponse(savedProposal);
    }

    /**
     * Returns a proposal by ID.
     */
    public ProposalResponse getProposalById(Long proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null) {
            throw new ResourceNotFoundException(
                    "Proposal not found with ID: " + proposalId
            );
        }

        return ProposalMapper.toResponse(proposal);
    }

    /**
     * Internal helper used by submitProposal().
     */
    private Proposal getProposalEntity(Long proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId);

        if (proposal == null) {
            throw new ResourceNotFoundException(
                    "Proposal not found with ID: " + proposalId
            );
        }

        return proposal;
    }

    /**
     * Runs all business validations.
     */
    private void validateProposal(
            ProposalRequest request,
            Customer customer
    ) {

        validatePolicyTerm(request.policyTerm());

        validateSumAssured(request.sumAssured());

        validatePanRequirement(
                customer,
                request.annualPremium()
        );

        validateNominee(
                customer,
                request.nomineeName()
        );

        referenceMasterService.validatePaymentFrequency(
                request.paymentFrequency()
        );
    }

    private void validatePolicyTerm(Integer policyTerm) {

        if (!AppConstants.VALID_POLICY_TERMS.contains(policyTerm)) {

            throw new BusinessValidationException(
                    "Policy term must be 10, 15, 20, 25 or 30 years."
            );

        }

    }

    private void validateSumAssured(BigDecimal sumAssured) {

        if (sumAssured.compareTo(AppConstants.MIN_SUM_ASSURED) < 0
                || sumAssured.compareTo(AppConstants.MAX_SUM_ASSURED) > 0) {

            throw new BusinessValidationException(
                    "Sum assured must be between ₹1,00,000 and ₹5,00,00,000."
            );

        }

    }

    private void validatePanRequirement(
            Customer customer,
            BigDecimal annualPremium
    ) {

        if (annualPremium.compareTo(AppConstants.PAN_THRESHOLD) > 0) {

            String pan = customer.getPanNumber();

            if (pan == null || pan.isBlank()) {

                throw new BusinessValidationException(
                        "PAN number is mandatory when annual premium exceeds ₹50,000."
                );

            }

        }

    }

    private void validateNominee(
            Customer customer,
            String nomineeName
    ) {

        String customerName =
                (customer.getFirstName() + " " + customer.getLastName()).trim();

        if (customerName.equalsIgnoreCase(nomineeName.trim())) {

            throw new BusinessValidationException(
                    "Nominee cannot be the same as the customer."
            );

        }

    }

    /**
     * Submits a proposal after all validations have passed.
     */
    public ProposalResponse submitProposal(Long proposalId) {

        Proposal proposal = getProposalEntity(proposalId);

        if (proposal.getStatus() == ProposalStatus.SUBMITTED) {
            throw new BusinessValidationException(
                    "Proposal has already been submitted."
            );
        }

        proposal.setStatus(ProposalStatus.SUBMITTED);
        proposal.setPolicyNumber(generatePolicyNumber(proposal.getId()));

        Proposal updatedProposal = proposalRepository.save(proposal);

        auditService.createAudit(
                proposal.getId(),
                "PROPOSAL_SUBMITTED"
        );

        log.info(
                "Proposal {} submitted successfully with policy number {}",
                updatedProposal.getId(),
                updatedProposal.getPolicyNumber()
        );

        return ProposalMapper.toResponse(updatedProposal);
    }

    /**
     * Generates a unique policy number.
     *
     * Example:
     * POL2026000001
     */
    private String generatePolicyNumber(Long proposalId) {

        return String.format(
                "POL%d%06d",
                java.time.LocalDate.now().getYear(),
                proposalId
        );

    }

}