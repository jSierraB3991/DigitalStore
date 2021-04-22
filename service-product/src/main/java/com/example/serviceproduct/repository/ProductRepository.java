package com.example.serviceproduct.repository;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>{

    List<Product> findByCategory(Category category);
}
