package com.praveen.productService.services;

import com.praveen.productService.dtos.CreateProductDto;
import com.praveen.productService.dtos.GetProductDto;
import com.praveen.productService.dtos.UpdateProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    List<GetProductDto> getAllProducts();

    GetProductDto getProductById(Long id) throws ProductNotFoundException;

    GetProductDto addProduct(CreateProductDto productDto) throws Exception;

    GetProductDto updateProduct(long id, UpdateProductDto productDto) throws ProductNotFoundException;

    void deleteProduct(long id) throws ProductNotFoundException;
}
