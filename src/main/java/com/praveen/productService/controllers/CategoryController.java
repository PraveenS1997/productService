package com.praveen.productService.controllers;

import com.praveen.productService.dtos.CategoryDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
     private final CategoryService categoryService;

     public CategoryController(CategoryService categoryService){
         this.categoryService = categoryService;
     }

     @GetMapping("/{id}")
     public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") long categoryId,
                                                    @RequestParam(defaultValue = "false")
                                                    boolean loadRelatedProducts) throws Exception{
         CategoryDto category = categoryService.getCategory(categoryId, loadRelatedProducts);
         return new ResponseEntity<>(category, HttpStatus.OK);
     }

     @GetMapping
     public ResponseEntity<List<CategoryDto>> getCategories() {
         List<CategoryDto> categories = categoryService.getAllCategories();
         return new ResponseEntity<>(categories, HttpStatus.OK);
     }

     @GetMapping("/{id}/products")
     public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable("id") long categoryId) {
         List<ProductDto> products = categoryService.getAllProductsByCategory(categoryId);
         return new ResponseEntity<>(products, HttpStatus.OK);
     }
}
