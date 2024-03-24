package com.praveen.productcatalogue.services;

import com.praveen.productcatalogue.dtos.FakeProductDto;
import com.praveen.productcatalogue.models.Category;
import com.praveen.productcatalogue.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FakeStoreProductService implements ProductService {
    private static final String FAKE_STORE_API_URL = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeProductDto[] fakeProducts = restTemplate.getForObject(
                FAKE_STORE_API_URL,
                FakeProductDto[].class);

        if(fakeProducts == null) {
            return null;
        }

        return Stream.of(fakeProducts)
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        FakeProductDto fakeProductDto = restTemplate.getForObject(
                FAKE_STORE_API_URL + "/" + id,
                FakeProductDto.class);

        if(fakeProductDto == null) {
            return null;
        }

        return convertToProduct(fakeProductDto);
    }

    private Product convertToProduct(FakeProductDto fakeProductDto) {
        Product product = new Product();
        product.setId(fakeProductDto.getId());
        product.setTitle(fakeProductDto.getTitle());
        product.setPrice(fakeProductDto.getPrice());
        product.setDescription(fakeProductDto.getDescription());
        product.setImageUrl(fakeProductDto.getImageUrl());

        Category category = new Category();
        category.setId(1);
        category.setTitle(fakeProductDto.getCategory());
        category.setDescription(fakeProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
