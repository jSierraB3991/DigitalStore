package com.example.serviceproduct.controller;

import com.example.serviceproduct.dto.ProductUpdateStock;
import com.example.serviceproduct.entity.Product;
import com.example.serviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        var list = productService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Product>> findAllPageable(Pageable pageable) {
        var list = productService.findAllPageable(pageable);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/category/{uuid-category}")
    public ResponseEntity<List<Product>> findAllByCategory(@PathVariable("uuid-category") UUID category) {
        var list = productService.findByCategory(category);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{uuid-product}")
    public ResponseEntity<Product> findById(@PathVariable("uuid-product") UUID product) {
        try {
            var productSave = productService.findById(product);
            return ResponseEntity.ok(productSave);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
        try {
            var productSave = productService.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(productSave);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{uuid-product}")
    public ResponseEntity<Product> update(@PathVariable("uuid-product") UUID productUuid,
                                          @Valid @RequestBody Product product) {
        try {
            var productSave = productService.update(productUuid, product);
            return ResponseEntity.ok().body(productSave);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{uuid-product}")
    public ResponseEntity delete(@PathVariable("uuid-product") UUID productUuid) {
        try {
            productService.delete(productUuid);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Product> updateStock(@RequestBody ProductUpdateStock productUpdateStock) {
        try {
            var product = productService.updateStock(productUpdateStock.getUuid(), productUpdateStock.getStock());
            return ResponseEntity.ok().body(product);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
