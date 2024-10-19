package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Model.Product;
import com.amazonclone.productcatalog.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.getProductById(id);
        if(product.isPresent()) return product.get();
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        Product product1 = getProductById(id);
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setImageUrl(product.getImageUrl());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory());
        return productRepo.save(product);
    }
}
