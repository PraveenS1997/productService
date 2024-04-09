package com.praveen.productService.mappers;

import com.praveen.productService.dtos.ProductDto;
import com.praveen.productService.models.Product;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setCategory(product.getCategory().getTitle());
        productDto.setCreatedAt(product.getCreatedAt());
        return productDto;
    }
}
