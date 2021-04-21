package com.example.serviceproduct.entity;

import com.example.serviceproduct.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "product")
@Entity
public class Product {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;
    private String description;
    private Double stock;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "create_at")
    private LocalDate createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
