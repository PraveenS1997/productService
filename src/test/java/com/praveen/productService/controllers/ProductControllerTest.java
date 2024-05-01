package com.praveen.productService.controllers;

import com.praveen.productService.dtos.categoryDtos.CategoryDto;
import com.praveen.productService.dtos.productDtos.CreateProductDto;
import com.praveen.productService.dtos.productDtos.ProductDto;
import com.praveen.productService.dtos.productDtos.UpdateProductDto;
import com.praveen.productService.exceptions.EntityNotFoundException;
import com.praveen.productService.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    void givenValidIdShouldReturnCorrectProduct() {
        // Arrange
        ProductDto expectedProduct = getProductDto(1L, "Macbook Air M1", "Apple Laptops", 145000, new CategoryDto());

        when(productService.getProductById(1L))
                .thenReturn(expectedProduct);

        // Act
        ProductDto actualProduct = productController.getProductById(1L).getBody();

        // Assert
        Assertions.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void givenInvalidProductIdShouldThrowException(){
        // Arrange
        when(productService.getProductById(1L))
                .thenThrow(new EntityNotFoundException("Product with id 1 not found"));

        // Act and Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> productController.getProductById(1L));
    }

    @Test
    void getAllProductsShouldReturnExpectedProducts(){
        // Arrange
        ProductDto product1 = getProductDto(1L, "Macbook Air M1", "Apple Laptops", 145000, new CategoryDto());
        ProductDto product2 = getProductDto(2L, "Lenovo T490s", "Windows Laptop", 75000, new CategoryDto());

        List<ProductDto> expectedProducts = new ArrayList<>();
        expectedProducts.add(product1);
        expectedProducts.add(product2);

        when(productService.getAllProducts())
                .thenReturn(expectedProducts);

        // Act
        List<ProductDto> actualProducts = productController.getProducts().getBody();

        // Assert
        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void getAllProductsShouldReturnEmptyProducts(){
        // Arrange
        List<ProductDto> expectedProducts = new ArrayList<>();

        when(productService.getAllProducts())
                .thenReturn(expectedProducts);

        // Act
        List<ProductDto> actualProducts = productController.getProducts().getBody();

        // Assert
        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void addProductShouldReturnExpectedProduct() {
        // Arrange
        CreateProductDto createProductDto = getCreateProductDto();

        ProductDto expectedProduct = getProductDto(1L, createProductDto.getTitle(),
                createProductDto.getDescription(), createProductDto.getPrice(), createProductDto.getCategory());

        when(productService.addProduct(createProductDto))
                .thenReturn(expectedProduct);

        // Act
        ProductDto actualProduct = productController.addProduct(createProductDto).getBody();

        // Assert
        Assertions.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void addProductShouldThrowException() {
        // Arrange
        CreateProductDto createProductDto = getCreateProductDto();
        createProductDto.getCategory().setId(55L);

        when(productService.addProduct(createProductDto))
                .thenThrow(new EntityNotFoundException("Category with id 55 not found"));

        // Act and Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> productController.addProduct(createProductDto));
    }

    @Test
    void updateProductShouldReturnExpectedProduct() {
        // Arrange
        UpdateProductDto updateProductDto = getUpdateProductDto();

        ProductDto expectedProduct = getProductDto(1L, updateProductDto.getTitle(),
                updateProductDto.getDescription(), updateProductDto.getPrice(), updateProductDto.getCategory());

        when(productService.updateProduct(1L, updateProductDto))
                .thenReturn(expectedProduct);

        // Act
        ProductDto actualProduct = productController.updateProduct(1L, updateProductDto).getBody();

        // Assert
        Assertions.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void updateProductShouldThrowExceptionWhenInvalidProductGiven() {
        // Arrange
        UpdateProductDto updateProductDto = getUpdateProductDto();

        when(productService.updateProduct(100L, updateProductDto))
                .thenThrow(new EntityNotFoundException("Product with id 100 not found"));

        // Act and Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> productController.updateProduct(100L, updateProductDto));
    }

    @Test
    void updateProductShouldThrowExceptionWhenInvalidCategoryGiven() {
        // Arrange
        UpdateProductDto updateProductDto = getUpdateProductDto();
        updateProductDto.getCategory().setId(100L);

        when(productService.updateProduct(1L, updateProductDto))
                .thenThrow(new EntityNotFoundException("Category with id 100 not found"));

        // Act and Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> productController.updateProduct(1L, updateProductDto));
    }

    @Test
    void deleteProductShouldReturnNoContent() {
        // Arrange
        doNothing().when(productService).deleteProduct(1L);

        // Act and Assert
        Assertions.assertDoesNotThrow(() -> productController.deleteProduct(1L));
    }

    @Test
    void deleteProductShouldThrowException() {
        // Arrange
        doThrow(new EntityNotFoundException("Product with id 100 not found"))
                .when(productService).deleteProduct(100L);

        // Act and Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> productController.deleteProduct(100L));
    }

    private static CreateProductDto getCreateProductDto() {
        CategoryDto categoryDto = getCategoryDto();

        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setTitle("Macbook Pro");
        createProductDto.setDescription("Apple laptop");
        createProductDto.setPrice(145000);
        createProductDto.setImageUrl("https://www.apple.com");
        createProductDto.setCategory(categoryDto);
        return createProductDto;
    }


    private static UpdateProductDto getUpdateProductDto() {
        CategoryDto categoryDto = getCategoryDto();

        UpdateProductDto updateProductDto = new UpdateProductDto();
        updateProductDto.setTitle("Macbook Pro");
        updateProductDto.setDescription("Apple laptop");
        updateProductDto.setPrice(145000);
        updateProductDto.setImageUrl("https://www.apple.com");
        updateProductDto.setCategory(categoryDto);

        return updateProductDto;
    }

    private static CategoryDto getCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle("Laptops");
        categoryDto.setDescription("Apple and Windows laptops");
        return categoryDto;
    }

    private static ProductDto getProductDto(Long id, String title, String description, double price, CategoryDto categoryDto) {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setTitle(title);
        productDto.setDescription(description);
        productDto.setPrice(price);
        productDto.setCategory(categoryDto == null ? getCategoryDto() : categoryDto);

        return productDto;
    }
}