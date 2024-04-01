package com.praveen.productService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDto {
    private String title;

    private double price;

    private String description;

    private String imageUrl;

    private String  category;
}
