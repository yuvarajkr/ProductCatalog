package com.amazonclone.productcatalog.Repository;

import com.amazonclone.productcatalog.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
