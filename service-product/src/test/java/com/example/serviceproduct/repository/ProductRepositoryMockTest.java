package com.example.serviceproduct.repository;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.UUID;

import static com.example.serviceproduct.enums.Status.CREATED;

@DataJpaTest
public class ProductRepositoryMockTest {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProductRepositoryMockTest.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    void Data() {
        categoryRepository.save(Category.builder().id(UUID.randomUUID()).name("Shoes").build());
        categoryRepository.save(Category.builder().id(UUID.randomUUID()).name("books").build());
        categoryRepository.save(Category.builder().id(UUID.randomUUID()).name("electronics").build());

        productRepository.save(Product.builder()
                .id(UUID.randomUUID())
                .name("adidas Cloudfoam Ultimate")
                .category(categoryRepository.findByName("Shoes").get())
                .description("Walk in the air in the black / black CLOUDFOAM ULTIMATE running shoe from ADIDAS")
                .stock(5D)
                .price(178.89D)
                .status(CREATED)
                .createAt(LocalDate.now())
                .build());
        productRepository.save(Product.builder()
                .id(UUID.randomUUID())
                .name("Spring Boot in Action")
                .category(categoryRepository.findByName("Shoes").get())
                .description("under armour Men")
                .stock(12D)
                .price(40.06D)
                .status(CREATED)
                .createAt(LocalDate.now())
                .build());
        productRepository.save(Product.builder()
                .id(UUID.randomUUID())
                .name("Under armour Men ''s Micro G Assert – 7")
                .category(categoryRepository.findByName("electronics").get())
                .description("under armour Men ")
                .stock(4D)
                .price(12.5D)
                .status(CREATED)
                .createAt(LocalDate.now())
                .build());
    }

    @Test
    void whenFindByCategory_thenReturnListProduct(){
        Data();
        var product = Product.builder()
                .id(UUID.randomUUID())
                .name("Computer")
                .category(categoryRepository.findByName("electronics").get())
                .description("")
                .stock(10D)
                .price(1249.99D)
                .status(CREATED)
                .createAt(LocalDate.now())
                .build();

        productRepository.save(product);
        var listAll = productRepository.findAll();
        var list = productRepository.findByCategory(product.getCategory());
        Assertions.assertEquals(list.size(), 2);
    }
}
