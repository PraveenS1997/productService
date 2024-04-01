package com.praveen.productService.controllers;

import com.praveen.productService.dtos.CreateProductDto;
import com.praveen.productService.dtos.ProductDto;
import com.praveen.productService.dtos.UpdateProductDto;
import com.praveen.productService.exceptions.ProductNotFoundException;
import com.praveen.productService.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
        ProductDto product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody CreateProductDto productDto) {
        ProductDto product = productService.addProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") long id, @RequestBody UpdateProductDto productDto) throws ProductNotFoundException {
        ProductDto product = productService.updateProduct(id, productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
