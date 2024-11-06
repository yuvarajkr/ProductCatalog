package com.amazonclone.productcatalog.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String parameter;
    private SortType sortType;
}
