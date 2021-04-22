package com.example.customerservice.mapper;
import com.example.customerservice.dto.RegionDto;
import com.example.customerservice.entity.Region;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper extends AutoMapper<Region, RegionDto> {
}
