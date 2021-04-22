package com.example.serviceproduct.help.factory;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.entity.Product;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

import static com.example.serviceproduct.enums.Status.CREATED;

public class ProductFactory {

    public static Category getCategory(){
        var faker = new Faker();
        return Category.builder().id(UUID.randomUUID()).name(faker.pokemon().name()).build();
    }

    public static Product getProduct(Category category){
        var faker = new Faker();
        return Product.builder()
                .id(UUID.randomUUID())
                .name(faker.harryPotter().character())
                .category(category)
                .description(faker.harryPotter().book())
                .stock(5D)
                .price(178.89D)
                .status(CREATED)
                .createAt(LocalDate.now())
                .build();
    }
}
