package com.amazonclone.productcatalog.Controller;

import com.amazonclone.productcatalog.Model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> products() {
        return null;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return null;
    }
}
