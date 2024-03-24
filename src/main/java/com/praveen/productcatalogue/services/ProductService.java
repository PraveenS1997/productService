package com.praveen.productcatalogue.services;

import com.praveen.productcatalogue.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
}
