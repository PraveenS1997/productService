package com.praveen.productService.dtos.productDtos;

import com.praveen.productService.dtos.CategoryDto;
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

    private CategoryDto category;

    private Timestamp createdAt;
}
