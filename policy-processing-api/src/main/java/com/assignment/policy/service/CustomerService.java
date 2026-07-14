package com.assignment.policy.service;

import com.assignment.policy.dto.request.CustomerRequest;
import com.assignment.policy.dto.response.CustomerResponse;
import com.assignment.policy.entity.Customer;
import com.assignment.policy.exception.BusinessValidationException;
import com.assignment.policy.exception.ResourceNotFoundException;
import com.assignment.policy.repository.CustomerRepository;
import com.assignment.policy.util.AppConstants;
import com.assignment.policy.util.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * Creates a new customer.
     */
    public CustomerResponse createCustomer(CustomerRequest request) {

        validateCustomerAge(request.dateOfBirth());

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .dateOfBirth(request.dateOfBirth())
                .panNumber(request.panNumber())
                .email(request.email())
                .mobileNumber(request.mobileNumber())
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        log.info("Customer created successfully with ID {}", savedCustomer.getId());

        return CustomerMapper.toResponse(savedCustomer);
    }

    /**
     * Returns a customer response by ID.
     */
    public CustomerResponse getCustomer(Long id) {

        Customer customer = customerRepository.findById(id);

        if (customer == null) {
            throw new ResourceNotFoundException(
                    "Customer not found with ID: " + id
            );
        }

        return CustomerMapper.toResponse(customer);
    }

    /**
     * Returns the Customer entity.
     * Used internally by ProposalService.
     */
    public Customer getCustomerEntity(Long id) {

        Customer customer = customerRepository.findById(id);

        if (customer == null) {
            throw new ResourceNotFoundException(
                    "Customer not found with ID: " + id
            );
        }

        return customer;
    }

    /**
     * Returns all customers.
     */
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    /**
     * Updates an existing customer.
     */
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {

        Customer customer = customerRepository.findById(id);

        if (customer == null) {
            throw new ResourceNotFoundException(
                    "Customer not found with ID: " + id
            );
        }

        validateCustomerAge(request.dateOfBirth());

        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setDateOfBirth(request.dateOfBirth());
        customer.setPanNumber(request.panNumber());
        customer.setEmail(request.email());
        customer.setMobileNumber(request.mobileNumber());

        Customer updatedCustomer = customerRepository.save(customer);

        log.info("Customer updated successfully with ID {}", updatedCustomer.getId());

        return CustomerMapper.toResponse(updatedCustomer);
    }

    /**
     * Validates customer age.
     */
    private void validateCustomerAge(LocalDate dateOfBirth) {

        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        if (age < AppConstants.MIN_AGE || age > AppConstants.MAX_AGE) {
            throw new BusinessValidationException(
                    "Customer age must be between 18 and 65 years."
            );
        }
    }
}