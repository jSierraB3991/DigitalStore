package com.example.serviceproduct.dto;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private UUID id = UUID.randomUUID();

    @NotEmpty(message = "The field name is required")
    private String name;

    private String description;

    @Positive(message = "The field stock is not lower to Zero")
    private Double stock;

    private Double price;

    private Status status;

    private LocalDate createAt = LocalDate.now();

    @NotNull(message = "The field category is not null")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
}
