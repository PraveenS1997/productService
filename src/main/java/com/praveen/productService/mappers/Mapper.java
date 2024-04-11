package com.praveen.productService.mappers;

import com.praveen.productService.dtos.CategoryDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.models.Category;
import com.praveen.productService.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    public ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCreatedAt(product.getCreatedAt());

        return productDto;
    }

    public CategoryDto mapCategoryToCategoryDtoWithoutProducts(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCreatedAt(category.getCreatedAt());

        return categoryDto;
    }

    public CategoryDto mapCategoryToCategoryDtoWithProducts(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCreatedAt(category.getCreatedAt());

        List<ProductDto> products = new ArrayList<>();
        for (Product p : category.getProducts()){
            products.add(mapProductToProductDto(p));
        }
        categoryDto.setProducts(products);

        return categoryDto;
    }
}
