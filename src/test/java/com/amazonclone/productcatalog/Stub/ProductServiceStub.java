package com.amazonclone.productcatalog.Stub;

import com.amazonclone.productcatalog.Model.Product;
import com.amazonclone.productcatalog.Service.IProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class ProductServiceStub implements IProductService {

    Map<Long, Product> products = new HashMap<Long, Product>();

    @Override
    public Product getProductById(Long id) {
        return products.get(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return products.put(product.getId(), product);
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return products.put(id, product);
    }
}
