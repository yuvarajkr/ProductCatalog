package com.amazonclone.productcatalog.Controller;

import com.amazonclone.productcatalog.Dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlowTest {
    @Autowired
    private ProductController productController;

    @Test
    public void Test_Create_Replace_GetProduct_WithStub_RunSuccessfully(){
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Iphone 15");

        //Act
        ProductDto response = productController.createProduct(productDto).getBody();
        ResponseEntity<ProductDto> productDtoResponseEntity = productController
                .getProductById(1L);
        productDto.setName("Iphone 16");

        ResponseEntity<ProductDto> replacedProduct = productController.replaceProduct(productDto,1L);
        ResponseEntity<ProductDto> productDtoResponseEntity2 = productController
                .getProductById(1L);

        //Assert
        assertEquals("Iphone 15",productDtoResponseEntity.getBody().getName());
        assertEquals("Iphone 16",productDtoResponseEntity2.getBody().getName());
    }
}
