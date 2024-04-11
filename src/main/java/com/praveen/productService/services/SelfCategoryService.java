package com.praveen.productService.services;

import com.praveen.productService.dtos.CategoryDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.mappers.Mapper;
import com.praveen.productService.models.Category;
import com.praveen.productService.models.Product;
import com.praveen.productService.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    public SelfCategoryService(CategoryRepository categoryRepository, Mapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto getCategory(Long categoryId, boolean loadRelatedProducts) throws Exception{
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found");
        }

        return loadRelatedProducts ? mapper.mapCategoryToCategoryDtoWithProducts(optionalCategory.get())
                : mapper.mapCategoryToCategoryDtoWithoutProducts(optionalCategory.get());
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        List<CategoryDto> result = new ArrayList<>();

        for(Category c : categories){
            result.add(mapper.mapCategoryToCategoryDtoWithProducts(c));
        }

        return result;
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isEmpty()){
            return new ArrayList<>();
        }

        List<ProductDto> products = new ArrayList<>();

        for(Product p : optionalCategory.get().getProducts()){
            products.add(mapper.mapProductToProductDto(p));
        }

        return products;
    }
}