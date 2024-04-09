package com.praveen.productService.services;

import com.praveen.productService.dtos.CreateProductDto;
import com.praveen.productService.dtos.ProductDto;
import com.praveen.productService.dtos.UpdateProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;
import com.praveen.productService.mappers.Mapper;
import com.praveen.productService.models.Category;
import com.praveen.productService.models.Product;
import com.praveen.productService.repositories.CategoryRepository;
import com.praveen.productService.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        Iterable<Product> products = productRepository.findAll();

        List<ProductDto> result = new ArrayList<>();
        products.forEach(product -> result.add(mapper.mapProductToProductDto(product)));

        return result;
    }

    @Override
    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return mapper.mapProductToProductDto(product);
    }

    @Override
    public ProductDto addProduct(CreateProductDto productDto) throws Exception {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        Category category = categoryRepository.findById(productDto.getCategory_id()).orElse(null);

        // ToDo: Need to change the exception type to a custom exception or a more appropriate exception
        if (category == null) {
            throw new Exception("Category with id " + productDto.getCategory_id() + " not found");
        }

        product.setCategory(category);
        productRepository.save(product);

        return mapper.mapProductToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(long id, UpdateProductDto productDto) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        productRepository.save(product);
        return mapper.mapProductToProductDto(product);
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        productRepository.deleteById(id);
    }
}
