package com.praveen.productService.services;

import com.praveen.productService.dtos.CategoryDto;
import com.praveen.productService.dtos.productDtos.ProductDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    List<ProductDto> getAllProductsByCategory(Long categoryId);
}
