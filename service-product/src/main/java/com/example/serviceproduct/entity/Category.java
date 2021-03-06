package com.example.serviceproduct.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@ToString
public class Category {
    @Id
    private UUID id;

    private String name;
}
