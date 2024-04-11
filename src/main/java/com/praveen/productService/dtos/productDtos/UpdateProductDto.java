package com.praveen.productService.dtos.productDtos;

import com.praveen.productService.dtos.categoryDtos.CategoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDto {
    private String title;

    private double price;

    private String description;

    private String imageUrl;

    private CategoryDto category;
}
