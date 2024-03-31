package com.praveen.productcatalogue.services;

import com.praveen.productcatalogue.dtos.CategoryDto;
import com.praveen.productcatalogue.dtos.FakeCategoryDto;
import com.praveen.productcatalogue.dtos.FakeProductDto;
import com.praveen.productcatalogue.dtos.ProductDto;
import com.praveen.productcatalogue.mappers.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService{
    private static final String FAKE_STORE_API_URL_GET_CATEGORIES = "https://fakestoreapi.com/products/categories";
    private static final String FAKE_STORE_API_URL= "https://fakestoreapi.com/products/category";

    private final HashMap<Long, String> categoryMap = new HashMap<>() {{
        put(1L, "electronics");
        put(2L, "jewelery");
        put(3L, "men's clothing");
        put(4L, "women's clothing");
    }};

    private final RestTemplate restTemplate;

    public FakeStoreCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CategoryDto> getAllCategories() {
        String[] response = restTemplate.getForObject(
                FAKE_STORE_API_URL_GET_CATEGORIES,
                String[].class);

        if(response == null){
            return new ArrayList<>();
        }

        List<CategoryDto> categories = new ArrayList<>();
        for (String category : response) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setTitle(category);
            categories.add(categoryDto);
        }

        return categories;
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        String categoryName = categoryMap.get(categoryId);

        if(categoryName == null){
            return new ArrayList<>();
        }

        FakeProductDto[] fakeProducts = restTemplate.getForObject(
                FAKE_STORE_API_URL + "/" + categoryName,
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
}
