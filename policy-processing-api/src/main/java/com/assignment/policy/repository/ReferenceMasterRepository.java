package com.assignment.policy.repository;

import com.assignment.policy.enums.PaymentFrequency;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReferenceMasterRepository {

    private List<PaymentFrequency> paymentFrequencies;

    @PostConstruct
    public void init() {

        paymentFrequencies = List.of(
                PaymentFrequency.MONTHLY,
                PaymentFrequency.QUARTERLY,
                PaymentFrequency.HALF_YEARLY,
                PaymentFrequency.YEARLY
        );

    }

    public List<PaymentFrequency> getPaymentFrequencies() {
        return paymentFrequencies;
    }

}