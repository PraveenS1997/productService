package com.praveen.productcatalogue.services;

import com.praveen.productcatalogue.dtos.FakeProductDto;
import com.praveen.productcatalogue.dtos.ProductDto;
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

        return Stream.of(fakeProducts)
                .map(this::convertToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        FakeProductDto fakeProductDto = restTemplate.getForObject(
                FAKE_STORE_API_URL + "/" + id,
                FakeProductDto.class);

        if(fakeProductDto == null) {
            return null;
        }

        return convertToProductDto(fakeProductDto);
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

        return convertToProductDto(fakeProductDto);
    }

    private ProductDto convertToProductDto(FakeProductDto fakeProductDto) {
        ProductDto productDto = new ProductDto();
        productDto.setId(fakeProductDto.getId());
        productDto.setTitle(fakeProductDto.getTitle());
        productDto.setPrice(fakeProductDto.getPrice());
        productDto.setDescription(fakeProductDto.getDescription());
        productDto.setImageUrl(fakeProductDto.getImage());
        productDto.setCategory(fakeProductDto.getCategory());

        return productDto;
    }
}
