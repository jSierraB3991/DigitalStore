package com.example.serviceproduct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
}
