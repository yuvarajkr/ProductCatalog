package com.amazonclone.productcatalog.Repository;

import com.amazonclone.productcatalog.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> getProductById(long id);

    Product save(Product product);
}
