package com.praveen.productService.models;

import com.praveen.productService.dtos.CreateProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;

    private double price;

    private String description;

    private String imageUrl;

    @ManyToOne
    private Category category;
}
