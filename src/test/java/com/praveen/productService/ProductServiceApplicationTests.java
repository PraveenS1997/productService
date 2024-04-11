package com.praveen.productService;

import com.praveen.productService.projections.ProductProjection;
import com.praveen.productService.repositories.CategoryRepository;
import com.praveen.productService.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductServiceApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testCreateCategory() {
        List<ProductProjection> products = productRepository.findByTitle("neplus");

        System.out.println("Products: " + products.size());

        for (ProductProjection product : products) {
            System.out.println("Title: " + product.getTitle());
            System.out.println("Description: " + product.getDescription());
        }
    }
}
