package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<Customer> findAll();

    Page<Customer> findAllPage(Pageable pageable);

    List<Customer> findAllByRegion(UUID uuid);

    Optional<Customer> findNumberId(String numberId);

    Customer save(Customer customer);

    Customer findById(UUID uuid);

    Customer update(UUID uuid, Customer customer);

    void delete(UUID uuid);
}
