package com.suraj.NeoShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String brand;
    private int quantity;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "product")
    private List<Image> images;

    public Product(String name, BigDecimal price, String brand, int quantity, String description,Category category) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.description = description;
        this.category=category;
    }
}
