package com.example.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "region")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {
    @Id
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

}
