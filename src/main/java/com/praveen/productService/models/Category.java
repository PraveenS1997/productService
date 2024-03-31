package com.praveen.productService.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category {
    private long id;

    private String title;

    private List<Product> products;
}
