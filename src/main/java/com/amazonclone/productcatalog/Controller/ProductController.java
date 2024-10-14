package com.amazonclone.productcatalog.Controller;

import com.amazonclone.productcatalog.Dtos.CategoryDto;
import com.amazonclone.productcatalog.Dtos.ProductDto;
import com.amazonclone.productcatalog.Model.Category;
import com.amazonclone.productcatalog.Model.Product;
import com.amazonclone.productcatalog.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/products")
    public List<ProductDto> products() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
           productDtos.add(convertProductToProductDto(product));
        }
        return productDtos;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long prodId) {
        System.out.println("Fetching product with ID: " + prodId);
        try{
            if(prodId<=0){
                throw new IllegalArgumentException("Invalid product ID");
            }
            Product product = productService.getProductById(prodId);

            if(product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            MultiValueMap<String,String> header = new LinkedMultiValueMap<>();
            header.add("called By", "Yuvaraj");
            return new ResponseEntity<>(convertProductToProductDto(product),header,HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    private ProductDto convertProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory()!=null){
            CategoryDto category = new CategoryDto();
            category.setId(product.getCategory().getId());
            category.setName(product.getCategory().getName());
            productDto.setCategory(category);
        }
        return productDto;
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody Product product){
        return null;
    }
}
