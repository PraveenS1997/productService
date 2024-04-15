package com.praveen.productService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(indexes = {@Index(name = "idx_product_title", columnList = "title", unique = true)})
public class Product extends BaseModel {
    private String title;

    private double price;

    private String description;

    private String imageUrl;

    @ManyToOne
    private Category category;
}
