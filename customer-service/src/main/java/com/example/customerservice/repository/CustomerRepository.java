package com.example.customerservice.repository;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByRegion(Region region);

    Optional<Customer> findByNumberId(String numberId);
}
