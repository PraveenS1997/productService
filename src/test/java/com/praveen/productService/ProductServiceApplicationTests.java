package com.praveen.productService;

import com.praveen.productService.models.Category;
import com.praveen.productService.models.Product;
import com.praveen.productService.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductServiceApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void testCreateCategory() {
        Category category1 = categoryRepository.findById(1L).get();

        System.out.println("Debug");

        List<Product> products = category1.getProducts();

        System.out.println("Debug");
    }
}
