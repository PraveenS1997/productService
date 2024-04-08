package com.praveen.productService.services;

import com.praveen.productService.dtos.CategoryDto;
import com.praveen.productService.dtos.GetProductDto;
import com.praveen.productService.repositories.CategoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SelfCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    @Override
    public List<GetProductDto> getAllProductsByCategory(Long categoryId) {
        return List.of();
    }
}
