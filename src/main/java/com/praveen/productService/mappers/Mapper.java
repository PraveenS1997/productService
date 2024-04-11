package com.praveen.productService.mappers;

import com.praveen.productService.dtos.categoryDtos.CategoryDto;
import com.praveen.productService.dtos.categoryDtos.CategoryProductDto;
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

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setTitle(product.getCategory().getTitle());
        categoryDto.setDescription(product.getCategory().getDescription());
        categoryDto.setCreatedAt(product.getCategory().getCreatedAt());

        productDto.setCategory(categoryDto);

        return productDto;
    }

    public CategoryProductDto mapCategoryToCategoryDtoWithProducts(Category category) {
        CategoryProductDto categoryDto = new CategoryProductDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCreatedAt(category.getCreatedAt());

        List<ProductDto> products = new ArrayList<>();
        for (Product product : category.getProducts()){
            ProductDto productDto = new ProductDto();

            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            productDto.setImageUrl(product.getImageUrl());
            productDto.setCreatedAt(product.getCreatedAt());

            products.add(productDto);
        }
        categoryDto.setProducts(products);

        return categoryDto;
    }
}
