package com.example.customerservice.controller;

import com.example.customerservice.dto.RegionDto;
import com.example.customerservice.mapper.RegionMapper;
import com.example.customerservice.service.RegionService;
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
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {
    private final RegionMapper regionMapper;
    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDto>> findAll() {
        var list = regionService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(regionMapper.getDto(list));
    }

    @PostMapping
    public ResponseEntity<RegionDto> save(@RequestBody @Valid RegionDto regionDto) {
        try {
            var region = regionService.save(regionMapper.getEntity(regionDto));
            return ResponseEntity.ok(regionMapper.getDto(region));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
