package com.example.serviceproduct.service.impl;

import com.example.serviceproduct.entity.Product;
import com.example.serviceproduct.repository.CategoryRepository;
import com.example.serviceproduct.repository.ProductRepository;
import com.example.serviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import static com.example.serviceproduct.enums.Status.CREATED;
import static com.example.serviceproduct.enums.Status.DELETE;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Product> findAllPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(UUID uuid) {
        return productRepository.findById(uuid).orElseThrow();
    }

    @Override
    public Product save(Product product) {
        product.setStatus(CREATED);
        return productRepository.save(product);
    }

    @Override
    public Product update(UUID uuid, Product product) {
        var productDb = findById(uuid);
        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setCategory(product.getCategory());
        productDb.setPrice(product.getPrice());
        return productRepository.save(product);
    }

    @Override
    public void delete(UUID uuid) {
        var product = findById(uuid);
        product.setStatus(DELETE);
        productRepository.save(product);
    }

    @Override
    public List<Product> findByCategory(UUID category) {
        return productRepository.findByCategory(categoryRepository.findById(category).orElseThrow());
    }

    @Override
    public Product updateStock(UUID uuid, Double quantity) {
        var product = findById(uuid);
        product.setStock(product.getStock() + quantity);
        return productRepository.save(product);
    }
}
