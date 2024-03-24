package com.praveen.productcatalogue.dtos;

import com.praveen.productcatalogue.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductDto {
    private long id;

    private String title;

    private double price;

    private String description;

    private String imageUrl;

    private String category;
}
