package com.example.serviceproduct.entity;

import com.example.serviceproduct.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
    private UUID id = UUID.randomUUID();

    @NotEmpty(message = "The field name is required")
    private String name;

    private String description;

    @Positive(message = "The field stock is not lower to Zero")
    private Double stock;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "create_at")
    private LocalDate createAt = LocalDate.now();

    @NotNull(message = "The field category is not null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
}
