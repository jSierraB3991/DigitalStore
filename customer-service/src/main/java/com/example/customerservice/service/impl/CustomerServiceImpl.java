package com.example.customerservice.service.impl;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.enums.Status;
import com.example.customerservice.repository.CustomerRepository;
import com.example.customerservice.repository.RegionRepository;
import com.example.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RegionRepository regionRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAllPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> findAllByRegion(UUID uuid) {
        return customerRepository.findByRegion(regionRepository.findById(uuid).orElseThrow());
    }

    @Override
    public Optional<Customer> findNumberId(String numberId) {
        return customerRepository.findByNumberId(numberId);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setStatus(Status.CREATED);
        return findNumberId(customer.getNumberId())
                .orElseGet(() -> customerRepository.save(customer));
    }

    @Override
    public Customer findById(UUID uuid) {
        return customerRepository.findById(uuid).orElseThrow();
    }

    @Override
    public Customer update(UUID uuid, Customer customer) {
        var customerData = findById(uuid);
        customerData.setEmail(customer.getEmail());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());
        customerData.setRegion(customer.getRegion());
        customerData.setStatus(customer.getStatus());
        return customerRepository.save(customerData);
    }

    @Override
    public void delete(UUID uuid) {
        findById(uuid);
        customerRepository.deleteById(uuid);
    }
}
