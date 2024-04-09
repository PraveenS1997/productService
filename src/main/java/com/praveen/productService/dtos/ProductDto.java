package com.praveen.productService.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ProductDto {
    private Long id;

    private String title;

    private double price;

    private String description;

    private String imageUrl;

    private String category;

    private Timestamp createdAt;
}
