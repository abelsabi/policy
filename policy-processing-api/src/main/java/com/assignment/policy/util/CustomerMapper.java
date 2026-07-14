package com.assignment.policy.util;

import com.assignment.policy.dto.response.CustomerResponse;
import com.assignment.policy.entity.Customer;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    public static CustomerResponse toResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getDateOfBirth(),
                customer.getPanNumber(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
    }
}