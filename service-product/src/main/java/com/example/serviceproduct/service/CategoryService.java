package com.example.serviceproduct.service;

import com.example.serviceproduct.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> findAll();

    Category findById(UUID uuid);

    Category save(Category category);

    Category update(UUID uuid, Category category);

    void delete(UUID uuid);
}
