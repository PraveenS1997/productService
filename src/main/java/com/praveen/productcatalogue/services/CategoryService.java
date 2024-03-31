package com.praveen.productcatalogue.services;

import com.praveen.productcatalogue.dtos.CategoryDto;
import com.praveen.productcatalogue.dtos.ProductDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    List<ProductDto> getAllProductsByCategory(Long categoryId);
}
