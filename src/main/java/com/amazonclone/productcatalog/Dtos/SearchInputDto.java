package com.amazonclone.productcatalog.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchInputDto {
    private String keyword;
    private Integer page;
    private Integer rows;
    //private List<SortParam> sortParamList = new ArrayList<>();
}
