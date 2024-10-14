package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Model.Product;

import java.util.List;

public interface IProductService {

    public Product getProductById(Long id);

    public List<Product> getAllProducts();

    public Product createProduct(Product product);

    public Product replaceProduct(Product product, Long id);

}
