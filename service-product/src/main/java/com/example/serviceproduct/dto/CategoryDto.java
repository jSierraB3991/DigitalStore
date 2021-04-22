package com.example.serviceproduct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {
    private UUID id = UUID.randomUUID();

    @NotEmpty(message = "The field name is required")
    private String name;
}
