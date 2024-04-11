package com.praveen.productService.dtos;

import com.praveen.productService.dtos.productDtos.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class CategoryDto {
    // if the Id value is not passed in the request body, it will be an update operation
    // If the Id value is passed in the request body, it will be an add operation
    private Long id;

    private String title;

    private String description;

    private Timestamp createdAt;

    private List<ProductDto> products;
}
