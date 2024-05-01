package com.praveen.productService.services;

import com.praveen.productService.dtos.productDtos.CreateProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.dtos.productDtos.UpdateProductDto;
import com.praveen.productService.exceptions.EntityNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    ProductDto addProduct(CreateProductDto productDto);

    ProductDto updateProduct(long id, UpdateProductDto productDto);

    void deleteProduct(long id);
}
