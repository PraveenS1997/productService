package com.praveen.productService.services;

import com.praveen.productService.dtos.productDtos.CreateProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.dtos.productDtos.UpdateProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;
import com.praveen.productService.mappers.Mapper;
import com.praveen.productService.models.Category;
import com.praveen.productService.models.Product;
import com.praveen.productService.repositories.CategoryRepository;
import com.praveen.productService.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SelfProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                              Mapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> result = new ArrayList<>();

        for(Product p : products){
            ProductDto productDto = mapper.mapProductToProductDto(p);
            productDto.setCategory(mapper.mapCategoryToCategoryDtoWithoutProducts(p.getCategory()));

            result.add(productDto);
        }

        return result;
    }

    @Override
    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        ProductDto result = mapper.mapProductToProductDto(product.get());
        result.setCategory(mapper.mapCategoryToCategoryDtoWithoutProducts(product.get().getCategory()));

        return result;
    }

    @Override
    public ProductDto addProduct(CreateProductDto productDto) throws Exception {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // if the category id is not provided, create a new category
        if(productDto.getCategory().getId() == null){
            Category category = new Category();
            category.setTitle(productDto.getCategory().getTitle());
            category.setDescription(productDto.getCategory().getDescription());
            category.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }

        // if the category id is provided, check if the category exists
        else{
            Category category = categoryRepository.findById(productDto.getCategory().getId())
                    .orElseThrow(() -> new ProductNotFoundException("Category with id " + productDto.getCategory().getId() + " not found"));

            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);

        ProductDto result = mapper.mapProductToProductDto(savedProduct);
        result.setCategory(mapper.mapCategoryToCategoryDtoWithoutProducts(product.getCategory()));

        return result;
    }

    @Override
    public ProductDto updateProduct(long id, UpdateProductDto productDto) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        Category category = categoryRepository.findById(productDto.getCategory().getId())
                .orElseThrow(() -> new ProductNotFoundException("Category with id " + id + " not found"));

        category.setTitle(productDto.getCategory().getTitle());
        category.setDescription(productDto.getCategory().getDescription());

        product.setCategory(category);

        categoryRepository.save(category);
        Product updatedProduct = productRepository.save(product);

        ProductDto result = mapper.mapProductToProductDto(updatedProduct);
        result.setCategory(mapper.mapCategoryToCategoryDtoWithoutProducts(updatedProduct.getCategory()));

        return result;
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        productRepository.deleteById(id);
    }
}
