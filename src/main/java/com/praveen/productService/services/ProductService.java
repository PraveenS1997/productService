package com.praveen.productService.services;

import com.praveen.productService.dtos.CreateProductDto;
import com.praveen.productService.dtos.ProductDto;
import com.praveen.productService.dtos.UpdateProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id) throws ProductNotFoundException;

    ProductDto addProduct(CreateProductDto productDto) throws Exception;

    ProductDto updateProduct(long id, UpdateProductDto productDto) throws ProductNotFoundException;

    void deleteProduct(long id) throws ProductNotFoundException;
}
