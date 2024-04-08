package com.praveen.productService.services;

import com.praveen.productService.dtos.CreateProductDto;
import com.praveen.productService.dtos.GetProductDto;
import com.praveen.productService.dtos.UpdateProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;
import com.praveen.productService.models.Category;
import com.praveen.productService.models.Product;
import com.praveen.productService.repositories.CategoryRepository;
import com.praveen.productService.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<GetProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public GetProductDto getProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return new GetProductDto(product);
    }

    @Override
    public GetProductDto addProduct(CreateProductDto productDto) throws Exception {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());

        Category category = categoryRepository.findById(productDto.getCategory_id()).orElse(null);

        // ToDo: Need to change the exception type to a custom exception or a more appropriate exception
        if (category == null) {
            throw new Exception("Category with id " + productDto.getCategory_id() + " not found");
        }

        product.setCategory(category);
        productRepository.save(product);

        return new GetProductDto(product);
    }

    @Override
    public GetProductDto updateProduct(long id, UpdateProductDto productDto) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {

    }
}
