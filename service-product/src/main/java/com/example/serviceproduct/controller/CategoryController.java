package com.example.serviceproduct.controller;

import com.example.serviceproduct.entity.Category;
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

    @GetMapping
    public ResponseEntity<List<Category>> findALl(){
        var categories = categoryService.findAll();
        if(categories.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody Category category){
        try {
            var categorySave = categoryService.save(category);
            return ResponseEntity.ok(categorySave);
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
