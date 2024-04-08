package com.praveen.productService.dtos;

import com.praveen.productService.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDto {

    public GetProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.category = product.getCategory().getTitle();
    }

    private Long id;

    private String title;

    private double price;

    private String description;

    private String imageUrl;

    private String category;
}
