package com.amazonclone.productcatalog.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Product extends BaseModel{

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Category category;
}
