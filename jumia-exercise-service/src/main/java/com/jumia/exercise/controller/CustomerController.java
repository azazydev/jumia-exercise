package com.jumia.exercise.controller;

import com.jumia.exercise.dto.CustomerDto;
import com.jumia.exercise.model.Customer;
import com.jumia.exercise.modelmapper.CustomerMapper;
import com.jumia.exercise.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ahmed Al3zazy
 * customer controller
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> search(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                                    String country, String state) {

        List<Customer> customers = customerService.getAll();
        List<CustomerDto> customerDtos = customerMapper.toCustomerDto(customers);

        Page<CustomerDto> customerDtoPage = new PageImpl<>(filterCustomers(customerDtos, country, state, page, size), PageRequest.of(page, size), customers.size());

        return ResponseEntity.ok(customerDtoPage);
    }

    /**
     *
     * @param List<customerDto> list of customerDto to be filtered
     * @param country filter by country allows null
     * @param state filter by state allows null
     * @param page and @param size for pagination
     * @return List<customerDto> filtered list of customers
     */
    private List<CustomerDto> filterCustomers(List<CustomerDto> customerDtos, String country, String state, int page, int size) {

        List<CustomerDto> filteredCustomers = customerDtos.stream()
                .filter(c -> country == null || c.getCountry().equals(country))
                .filter(c -> state == null || c.getState().toString().equals(state))
                .skip(page * size).limit(size)
                .collect(Collectors.toList());
        return filteredCustomers;
    }
}
