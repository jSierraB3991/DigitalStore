package com.example.serviceproduct.repository;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> , JpaSpecificationExecutor<Product>{

    List<Product> findByCategory(Category category);
}
