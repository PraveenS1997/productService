package com.praveen.productcatalogue.services;

import com.praveen.productcatalogue.dtos.FakeProductDto;
import com.praveen.productcatalogue.dtos.ProductDto;
import com.praveen.productcatalogue.mappers.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    public ProductDto getProductById(Long id) {
        FakeProductDto fakeProductDto = restTemplate.getForObject(
                FAKE_STORE_API_URL + "/" + id,
                FakeProductDto.class);

        if(fakeProductDto == null) {
            return null;
        }

        return Mapper.convertToProductDto(fakeProductDto);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        FakeProductDto fakeProductDto = restTemplate.getForObject(
                FAKE_STORE_API_URL + "/" + id,
                FakeProductDto.class);

        if(fakeProductDto == null) {
            return null;
        }

        fakeProductDto.setTitle(productDto.getTitle());
        fakeProductDto.setPrice(productDto.getPrice());
        fakeProductDto.setDescription(productDto.getDescription());
        fakeProductDto.setImage(productDto.getImageUrl());
        fakeProductDto.setCategory(productDto.getCategory());

        restTemplate.put(FAKE_STORE_API_URL + "/" + id, fakeProductDto);

        return Mapper.convertToProductDto(fakeProductDto);
    }
}
