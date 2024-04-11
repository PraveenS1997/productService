package com.praveen.productService.repositories;

import com.praveen.productService.projections.ProductProjection;
import com.praveen.productService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByDescriptionContaining(String description);

    @Query("SELECT p.title title, p.description description FROM Product p WHERE p.title like %?1%")
    List<ProductProjection> findByTitle(String title);
}
