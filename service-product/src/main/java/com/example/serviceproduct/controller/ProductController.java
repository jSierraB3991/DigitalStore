package com.example.serviceproduct.controller;

import com.example.serviceproduct.dto.ProductDto;
import com.example.serviceproduct.dto.ProductUpdateStock;
import com.example.serviceproduct.mappers.ProductMapper;
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
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        var list = productService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productMapper.getDto(list));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDto>> findAllPageable(Pageable pageable) {
        var list = productService.findAllPageable(pageable);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list.map(productMapper::getDto));
    }

    @GetMapping("/category/{uuid-category}")
    public ResponseEntity<List<ProductDto>> findAllByCategory(@PathVariable("uuid-category") UUID category) {
        var list = productService.findByCategory(category);
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productMapper.getDto(list));
    }

    @GetMapping("/{uuid-product}")
    public ResponseEntity<ProductDto> findById(@PathVariable("uuid-product") UUID product) {
        try {
            var productSave = productService.findById(product);
            return ResponseEntity.ok(productMapper.getDto(productSave));
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto product) {
        try {
            var productSave = productService.save(productMapper.getEntity(product));
            return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.getDto(productSave));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{uuid-product}")
    public ResponseEntity<ProductDto> update(@PathVariable("uuid-product") UUID productUuid,
                                             @Valid @RequestBody ProductDto product) {
        try {
            var productSave = productService.update(productUuid, productMapper.getEntity(product));
            return ResponseEntity.ok().body(productMapper.getDto(productSave));
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
    public ResponseEntity<ProductDto> updateStock(@RequestBody ProductUpdateStock productUpdateStock) {
        try {
            var product = productService.updateStock(productUpdateStock.getUuid(), productUpdateStock.getStock());
            return ResponseEntity.ok().body(productMapper.getDto(product));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
