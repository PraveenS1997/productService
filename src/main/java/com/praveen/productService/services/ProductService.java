package com.praveen.productService.services;

import com.praveen.productService.dtos.ProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id) throws ProductNotFoundException;
    ProductDto updateProduct(long id, ProductDto productDto) throws ProductNotFoundException;
}
