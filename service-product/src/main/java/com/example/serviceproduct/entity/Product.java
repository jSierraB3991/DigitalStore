package com.example.serviceproduct.entity;

import com.example.serviceproduct.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "status <> 'DELETE'")
public class Product {

    @Id
    private UUID id;

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
