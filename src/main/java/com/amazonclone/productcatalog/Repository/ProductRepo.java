package com.amazonclone.productcatalog.Repository;

import com.amazonclone.productcatalog.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Page<Product> findProductByName(String name, Pageable pageable);

    Optional<Product> getProductById(long id);

    Product save(Product product);

    @Query("SELECT p.name from Product p where p.id=:id")
    String findProductnameByProductId(long id);
}
