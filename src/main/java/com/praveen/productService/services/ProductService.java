package com.praveen.productService.services;

import com.praveen.productService.dtos.productDtos.CreateProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.dtos.productDtos.UpdateProductDto;
import com.praveen.productService.exceptions.EntityNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id) throws EntityNotFoundException;

    ProductDto addProduct(CreateProductDto productDto) throws Exception;

    ProductDto updateProduct(long id, UpdateProductDto productDto) throws EntityNotFoundException;

    void deleteProduct(long id) throws EntityNotFoundException;
}
