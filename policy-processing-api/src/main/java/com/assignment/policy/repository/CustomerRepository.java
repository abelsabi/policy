package com.assignment.policy.repository;

import com.assignment.policy.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CustomerRepository {

    private final ConcurrentHashMap<Long, Customer> customers = new ConcurrentHashMap<>();

    private final AtomicLong idGenerator = new AtomicLong(1);

    public Customer save(Customer customer) {

        if (customer.getId() == null) {
            customer.setId(idGenerator.getAndIncrement());
        }

        customers.put(customer.getId(), customer);

        return customer;
    }

    public Customer findById(Long id) {
        return customers.get(id);
    }

    public Collection<Customer> findAll() {
        return customers.values();
    }

    public boolean existsById(Long id) {
        return customers.containsKey(id);
    }

}