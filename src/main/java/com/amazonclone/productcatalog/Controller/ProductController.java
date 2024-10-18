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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDto> products() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
           productDtos.add(convertProductToProductDto(product));
        }
        return productDtos;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long prodId) {
        System.out.println("Fetching product with ID: " + prodId);
            if(prodId<=0){
                throw new IllegalArgumentException("Invalid product ID");
            }

            Product product = productService.getProductById(prodId);

            if(product == null) {
                throw new NullPointerException("Product with ID: " + prodId + " not found");
            }

            MultiValueMap<String,String> header = new LinkedMultiValueMap<>();
            header.add("called By", "Yuvaraj");
            return new ResponseEntity<>(convertProductToProductDto(product),header,HttpStatus.OK);
    }


    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        Product product = convertProductDtoToProduct(productDto);
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(convertProductToProductDto(createdProduct), HttpStatus.CREATED);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<ProductDto> replaceProduct(@RequestBody ProductDto productDto,@PathVariable Long id){
        Product input = convertProductDtoToProduct(productDto);
        Product product = productService.replaceProduct(input,id);
        return new ResponseEntity<>(convertProductToProductDto(product),HttpStatus.OK);
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        if(productDto.getCategory()!=null){
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
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
}
