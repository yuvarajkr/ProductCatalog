package com.amazonclone.productcatalog.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakestoreProductDto implements Serializable {

    private Long id;

    private String title;

    private String description;

    private String category;

    private String image;

    private Double price;

}
