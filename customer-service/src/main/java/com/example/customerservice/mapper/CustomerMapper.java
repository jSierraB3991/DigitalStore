package com.example.customerservice.mapper;

import com.example.customerservice.dto.CustomerDto;
import com.example.customerservice.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends AutoMapper<Customer, CustomerDto> {
}
