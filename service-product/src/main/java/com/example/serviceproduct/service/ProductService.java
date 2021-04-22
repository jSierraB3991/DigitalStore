package com.example.serviceproduct.service;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Page<Product> findAllPageable(Pageable pageable);

    List<Product> findAll();

    Product findById(UUID uuid);

    Product save(Product product);

    Product update(UUID uuid, Product product);

    void delete(UUID uuid);

    List<Product> findByCategory(UUID category);

    Product updateStock(UUID uuid, Double quantity);
}
