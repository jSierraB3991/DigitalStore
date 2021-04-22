package com.example.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionDto {
    private UUID id = UUID.randomUUID();

    @NotEmpty(message = "The field Name is required")
    @NotNull(message = "The field Name is required")
    private String name;

}
