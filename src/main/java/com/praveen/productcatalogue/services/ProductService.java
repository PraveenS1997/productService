package com.praveen.productcatalogue.services;

import com.praveen.productcatalogue.dtos.ProductDto;
import com.praveen.productcatalogue.models.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto updateProduct(long id, ProductDto productDto);
}
