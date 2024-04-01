package com.praveen.productService.services;

import com.praveen.productService.dtos.CreateProductDto;
import com.praveen.productService.dtos.FakeProductDto;
import com.praveen.productService.dtos.ProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;
import com.praveen.productService.mappers.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private static final String FAKE_STORE_API_URL = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        FakeProductDto[] fakeProducts = restTemplate.getForObject(
                FAKE_STORE_API_URL,
                FakeProductDto[].class);

        if(fakeProducts == null){
            return new ArrayList<>();
        }

        List<ProductDto> products = new ArrayList<>();
        for (FakeProductDto fakeProduct : fakeProducts) {
            products.add(Mapper.convertToProductDto(fakeProduct));
        }

        return products;
    }

    @Override
    public ProductDto getProductById(Long id) throws ProductNotFoundException {
        FakeProductDto fakeProductDto = restTemplate.getForObject(
                FAKE_STORE_API_URL + "/" + id,
                FakeProductDto.class);

        if(fakeProductDto == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return Mapper.convertToProductDto(fakeProductDto);
    }

    @Override
    public ProductDto addProduct(CreateProductDto productDto) {
        FakeProductDto fakeProductDto = new FakeProductDto();
        fakeProductDto.setTitle(productDto.getTitle());
        fakeProductDto.setPrice(productDto.getPrice());
        fakeProductDto.setDescription(productDto.getDescription());
        fakeProductDto.setImage(productDto.getImageUrl());
        fakeProductDto.setCategory(productDto.getCategory());

        FakeProductDto savedFakeProductDto = restTemplate.postForObject(
                FAKE_STORE_API_URL,
                fakeProductDto,
                FakeProductDto.class);

        if(savedFakeProductDto == null) {
            throw new RuntimeException("Failed to save product");
        }

        return Mapper.convertToProductDto(savedFakeProductDto);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) throws ProductNotFoundException {
        FakeProductDto fakeProductDto = restTemplate.getForObject(
                FAKE_STORE_API_URL + "/" + id,
                FakeProductDto.class);

        if(fakeProductDto == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        fakeProductDto.setTitle(productDto.getTitle());
        fakeProductDto.setPrice(productDto.getPrice());
        fakeProductDto.setDescription(productDto.getDescription());
        fakeProductDto.setImage(productDto.getImageUrl());
        fakeProductDto.setCategory(productDto.getCategory());

        restTemplate.put(FAKE_STORE_API_URL + "/" + id, fakeProductDto);

        return Mapper.convertToProductDto(fakeProductDto);
    }

    @Override
    public void deleteProduct(long id) {
        restTemplate.delete(FAKE_STORE_API_URL + "/" + id);
    }
}
