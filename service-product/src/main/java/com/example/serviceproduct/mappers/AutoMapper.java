package com.example.serviceproduct.mappers;

import java.util.List;

public interface AutoMapper<Entity, Dto> {
    Entity getEntity(Dto dto);

    Dto getDto(Entity entity);

    List<Entity> getEntity(List<Dto> dto);

    List<Dto> getDto(List<Entity> entity);
}
