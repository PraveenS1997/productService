package com.praveen.productService.services;

import com.praveen.productService.dtos.categoryDtos.CategoryProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;

import java.util.List;

public interface CategoryService {
    CategoryProductDto getCategory(Long categoryId);

    List<CategoryProductDto> getAllCategories();

    List<ProductDto> getAllProductsByCategory(Long categoryId);
}
