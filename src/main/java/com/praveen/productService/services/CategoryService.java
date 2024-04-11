package com.praveen.productService.services;

import com.praveen.productService.dtos.categoryDtos.CategoryProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;

import java.util.List;

public interface CategoryService {
    CategoryProductDto getCategory(Long categoryId) throws Exception;

    List<CategoryProductDto> getAllCategories();

    List<ProductDto> getAllProductsByCategory(Long categoryId);
}
