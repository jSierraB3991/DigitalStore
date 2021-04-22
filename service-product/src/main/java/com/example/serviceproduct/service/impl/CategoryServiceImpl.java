package com.example.serviceproduct.service.impl;

import com.example.serviceproduct.entity.Category;
import com.example.serviceproduct.repository.CategoryRepository;
import com.example.serviceproduct.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID uuid) {
        return categoryRepository.findById(uuid).orElseThrow();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(UUID uuid, Category category) {
        findById(uuid);
        category.setId(uuid);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(UUID uuid) {
        findById(uuid);
        categoryRepository.deleteById(uuid);
    }
}
