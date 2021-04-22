package com.example.serviceproduct.mappers;

import com.example.serviceproduct.dto.ProductDto;
import com.example.serviceproduct.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends AutoMapper<Product, ProductDto> {
}
