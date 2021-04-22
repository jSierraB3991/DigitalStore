package com.example.customerservice.service;

import com.example.customerservice.entity.Region;

import java.util.List;

public interface RegionService {

    List<Region> findAll();

    Region save(Region region);
}
