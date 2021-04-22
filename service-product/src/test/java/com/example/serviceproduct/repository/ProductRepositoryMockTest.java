package com.example.serviceproduct.repository;

import com.example.serviceproduct.entity.Product;
import com.example.serviceproduct.help.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.UUID;

import static com.example.serviceproduct.enums.Status.CREATED;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    void Data() {
        var categoryOne = categoryRepository.save(ProductFactory.getCategory());
        categoryRepository.save(ProductFactory.getCategory());
        categoryRepository.save(ProductFactory.getCategory());

        productRepository.save(ProductFactory.getProduct(categoryOne));
        productRepository.save(ProductFactory.getProduct(categoryOne));
        productRepository.save(ProductFactory.getProduct(categoryOne));
    }

    @Test
    void whenFindByCategory_thenReturnListProduct(){
        Data();
        var category = categoryRepository.findAll().get(0);
        var actual = productRepository.findByCategory(category).size();

        var product = Product.builder()
                .id(UUID.randomUUID())
                .name("Computer")
                .category(category)
                .description("")
                .stock(10D)
                .price(1249.99D)
                .status(CREATED)
                .createAt(LocalDate.now())
                .build();

        productRepository.save(product);
        var list = productRepository.findByCategory(category);
        Assertions.assertEquals(list.size(), actual + 1);
    }
}
