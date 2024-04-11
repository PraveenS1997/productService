package com.praveen.productService.dtos.categoryDtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CategoryDto {
    // if the Id value is not passed in the request body, it will be an update operation
    // If the Id value is passed in the request body, it will be an add operation
    private Long id;

    private String title;

    private String description;

    private Timestamp createdAt;
}
