package com.assignment.policy.service;

import com.assignment.policy.enums.PaymentFrequency;
import com.assignment.policy.exception.BusinessValidationException;
import com.assignment.policy.repository.ReferenceMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReferenceMasterService {

    private final ReferenceMasterRepository referenceMasterRepository;

    public List<PaymentFrequency> getPaymentFrequencies() {
        return referenceMasterRepository.getPaymentFrequencies();
    }

    public void validatePaymentFrequency(PaymentFrequency paymentFrequency) {

        boolean exists = referenceMasterRepository
                .getPaymentFrequencies()
                .contains(paymentFrequency);

        if (!exists) {
            throw new BusinessValidationException(
                    "Invalid payment frequency."
            );
        }

    }

}