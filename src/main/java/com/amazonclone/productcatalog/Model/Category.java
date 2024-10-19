package com.amazonclone.productcatalog.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
public class Category extends BaseModel{

    private String name;

    private String description;

    @OneToMany(mappedBy="category")
    private List<Product> products;
}
