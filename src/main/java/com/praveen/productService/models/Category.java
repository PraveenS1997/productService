package com.praveen.productService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(indexes = {@Index(name = "idx_category_title", columnList = "title" , unique = true)})
public class Category extends BaseModel {
    private String title;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
