package com.amazonclone.productcatalog.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Category extends BaseModel{

    private String category_name;

    private String category_description;

    private List<Product> products;
}
