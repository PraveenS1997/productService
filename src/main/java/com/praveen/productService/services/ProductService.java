package com.praveen.productService.services;

import com.praveen.productService.dtos.productDtos.CreateProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.dtos.productDtos.UpdateProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id) throws ProductNotFoundException;

    ProductDto addProduct(CreateProductDto productDto) throws Exception;

    ProductDto updateProduct(long id, UpdateProductDto productDto) throws ProductNotFoundException;

    void deleteProduct(long id) throws ProductNotFoundException;
}
