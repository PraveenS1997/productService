package com.praveen.productService.services;

import com.praveen.productService.dtos.CategoryDto;
import com.praveen.productService.dtos.ProductDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    List<ProductDto> getAllProductsByCategory(Long categoryId);
}
