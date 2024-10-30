package com.amazonclone.productcatalog.Controller;


import com.amazonclone.productcatalog.Dtos.ProductDto;
import com.amazonclone.productcatalog.Model.Category;
import com.amazonclone.productcatalog.Model.Product;
import com.amazonclone.productcatalog.Repository.CategoryRepo;
import com.amazonclone.productcatalog.Service.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController controller;

    @MockBean
    private IProductService iProductService;


    @Test
   public void Test_getProductById_withValidProductId_ReturnProductSuccessfully(){
       //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone");
        product.setDescription("Iphone");
        product.setPrice(1.0);
        product.setImageUrl("abc");
        Category category = new Category();
        category.setId(1L);
        category.setName("Phone");
        category.setDescription("Phone");
        product.setCategory(category);
        when(iProductService.getProductById(1L)).thenReturn(product);

       //Ask
        ResponseEntity<ProductDto> response = controller.getProductById(1L);
       //Assert
        assertNotNull(response);
        assertEquals(response.getBody().getName(),"Iphone");
        assertEquals(response.getBody().getId(),1L);
        verify(iProductService,times(1)).getProductById(1L);
   }
    @Test
    public void Test_getProductById_withInvalidProductId_ReturnIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,()->controller.getProductById(-1L));
        verify(iProductService,times(0)).getProductById(-1L);
    }

    @Test
    public void Test_getProductById_productThrowsException(){
        Long productId = 2L;
        when(iProductService.getProductById(productId)).thenThrow(new RuntimeException("Something Went bad!"));
        assertThrows(Exception.class, () -> controller.getProductById(productId));
    }

    @Test
    public void Test_postProduct_withValidProduct_ReturnProductSuccessfully(){
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setDescription("IPhone");
        productDto.setName("Iphone");
        productDto.setPrice(1.0);

        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone");
        product.setDescription("Iphone");
        product.setPrice(1.0);

        when(iProductService.createProduct(any(Product.class))).thenReturn(product);

        ResponseEntity<ProductDto> response = controller.createProduct(productDto);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getName(),"Iphone");
    }

    @Test
    public void Test_replaceProduct_withValidProduct_ReturnProductSuccessfully(){
        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setDescription("IPhone16");
        productDto.setName("Iphone16");
        productDto.setPrice(2.0);

        Product product = new Product();
        product.setId(2L);
        product.setName("Iphone16");
        product.setDescription("Iphone16");
        product.setPrice(2.0);

        when(iProductService.replaceProduct(any(Product.class),any(Long.class))).thenReturn(product);

        ResponseEntity<ProductDto> response = controller.replaceProduct(productDto,1L);

        assertNotNull(response.getBody());
        assertEquals(response.getBody().getName(),"Iphone16");
    }
}