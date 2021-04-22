package com.example.customerservice.mapper;

import java.util.List;

public interface AutoMapper<Entity, Dto> {

    Dto getDto(Entity entity);

    Entity getEntity(Dto dto);

    List<Dto> getDto(List<Entity> entity);

    List<Entity> getEntity(List<Dto> dto);
}
