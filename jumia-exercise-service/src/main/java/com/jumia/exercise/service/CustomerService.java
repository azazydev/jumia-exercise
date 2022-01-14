package com.jumia.exercise.service;

import com.jumia.exercise.model.Customer;
import com.jumia.exercise.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahmed Al3zazy
 * customer service
 */
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * @return all customers without pagination
     */
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    /**
     * @param pageable
     * @return paginated customers
     */
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
