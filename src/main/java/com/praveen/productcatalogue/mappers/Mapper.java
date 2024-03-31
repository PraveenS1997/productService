package com.praveen.productcatalogue.mappers;

import com.praveen.productcatalogue.dtos.CategoryDto;
import com.praveen.productcatalogue.dtos.FakeCategoryDto;
import com.praveen.productcatalogue.dtos.FakeProductDto;
import com.praveen.productcatalogue.dtos.ProductDto;

import java.util.function.Function;

public class Mapper {
    public static CategoryDto convertToCategoryDto(FakeCategoryDto fakeCategoryDto) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle(fakeCategoryDto.getTitle());

        return categoryDto;
    }

    public static ProductDto convertToProductDto(FakeProductDto fakeProductDto) {
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
