package com.example.serviceproduct.mappers;

import com.example.serviceproduct.dto.CategoryDto;
import com.example.serviceproduct.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends AutoMapper<Category, CategoryDto> {
}
