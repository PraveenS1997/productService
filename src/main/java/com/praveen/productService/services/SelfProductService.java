package com.praveen.productService.services;

import com.praveen.productService.dtos.productDtos.CreateProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.dtos.productDtos.UpdateProductDto;
import com.praveen.productService.exceptions.EntityNotFoundException;
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

        for (Product product : products) {
            ProductDto productDto = mapper.mapProductToProductDto(product);
            result.add(productDto);
        }

        return result;
    }

    @Override
    public ProductDto getProductById(Long id) throws EntityNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }

        return mapper.mapProductToProductDto(product.get());
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
                    .orElseThrow(() -> new EntityNotFoundException("Category with id " + productDto.getCategory().getId() + " not found"));

            product.setCategory(category);
        }

        Product savedProduct = productRepository.save(product);

        return mapper.mapProductToProductDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(long id, UpdateProductDto productDto) throws EntityNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());

        Category category = categoryRepository.findById(productDto.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found"));

        category.setTitle(productDto.getCategory().getTitle());
        category.setDescription(productDto.getCategory().getDescription());
        Category updatedCategory = categoryRepository.save(category);

        product.setCategory(updatedCategory);

        Product updatedProduct = productRepository.save(product);

        return mapper.mapProductToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(long id) throws EntityNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new EntityNotFoundException("Product with id " + id + " not found");
        }

        productRepository.deleteById(id);
    }
}
