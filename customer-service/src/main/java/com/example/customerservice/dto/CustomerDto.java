package com.example.customerservice.dto;

import com.example.customerservice.entity.Region;
import com.example.customerservice.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private UUID id = UUID.randomUUID();

    @NotNull(message = "The Field Number Id is Required")
    @NotEmpty(message = "The Field Number Id is Required")
    @Size(min = 7, max = 11, message = "The field number id is range of 7 to 11")
    private String numberId;

    @NotNull(message = "The field field first name is required")
    @NotEmpty(message = "The field field first name is required")
    private String firstName;

    @NotNull(message = "The field field last name is required")
    @NotEmpty(message = "The field field last name is required")
    private String lastName;

    @Email(message = "The Email is wrong")
    @NotNull(message = "The field field email is required")
    @NotEmpty(message = "The field field email is required")
    private String email;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull(message = "The field field region is required")
    private Region region;

    private Status status;

    private LocalDate createAt = LocalDate.now();
}
