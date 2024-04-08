package com.praveen.productService.controllers;

import com.praveen.productService.dtos.CreateProductDto;
import com.praveen.productService.dtos.GetProductDto;
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
    public ResponseEntity<GetProductDto> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        GetProductDto product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetProductDto>> getProducts() {
        List<GetProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GetProductDto> addProduct(@RequestBody CreateProductDto productDto) throws Exception{
        GetProductDto product = productService.addProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetProductDto> updateProduct(@PathVariable("id") long id, @RequestBody UpdateProductDto productDto) throws ProductNotFoundException {
        GetProductDto product = productService.updateProduct(id, productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
