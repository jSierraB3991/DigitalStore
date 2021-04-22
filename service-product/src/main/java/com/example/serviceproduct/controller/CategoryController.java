package com.example.serviceproduct.controller;

import com.example.serviceproduct.dto.CategoryDto;
import com.example.serviceproduct.mappers.CategoryMapper;
import com.example.serviceproduct.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findALl() {
        var categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categoryMapper.getDto(categories));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@Valid @RequestBody CategoryDto category) {
        try {
            var categorySave = categoryService.save(categoryMapper.getEntity(category));
            return ResponseEntity.ok(categoryMapper.getDto(categorySave));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
