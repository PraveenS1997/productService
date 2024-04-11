package com.praveen.productService.dtos.categoryDtos;

import com.praveen.productService.dtos.productDtos.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class CategoryProductDto {
    private Long id;

    private String title;

    private String description;

    private Timestamp createdAt;

    private List<ProductDto> products;
}
