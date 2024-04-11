package com.praveen.productService.dtos.productDtos;

import com.praveen.productService.dtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    private String title;

    private double price;

    private String description;

    private String imageUrl;

    private CategoryDto category;
}
