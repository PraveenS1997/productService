package com.praveen.productService.services;

import com.praveen.productService.dtos.CategoryDto;
import com.praveen.productService.dtos.GetProductDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    List<GetProductDto> getAllProductsByCategory(Long categoryId);
}
