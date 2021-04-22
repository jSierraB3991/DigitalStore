package com.example.serviceproduct.service;

import com.example.serviceproduct.help.factory.ProductFactory;
import com.example.serviceproduct.repository.CategoryRepository;
import com.example.serviceproduct.repository.ProductRepository;
import com.example.serviceproduct.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, categoryRepository);
    }

    @Test
    void whenValidGetId_thenReturnProduct() {
        var product = ProductFactory.getProduct(ProductFactory.getCategory());
        Mockito.when(productRepository.findById(any()))
                .thenReturn(Optional.of(product));

        var found = productService.findById(UUID.randomUUID());
        Assertions.assertEquals(product.getName(), found.getName());
    }


    @Test
    void whenValidUpdateStock_thenReturnNewStock() {
        var product = ProductFactory.getProduct(ProductFactory.getCategory());
        Mockito.when(productRepository.findById(any()))
                .thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(product))
                .thenReturn(product);
        var newProductStock = productService.updateStock(UUID.randomUUID(), 23456.34D);
        Assertions.assertEquals(product.getStock(), newProductStock.getStock());
    }
}
