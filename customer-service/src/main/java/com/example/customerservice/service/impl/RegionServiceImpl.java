package com.example.customerservice.service.impl;

import com.example.customerservice.entity.Region;
import com.example.customerservice.repository.RegionRepository;
import com.example.customerservice.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }
}
