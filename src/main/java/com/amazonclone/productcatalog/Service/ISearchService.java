package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Dtos.SortParam;
import com.amazonclone.productcatalog.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISearchService {

    Page<Product> searchProduct(String searchText, int page, int rows, List<SortParam> sortParams);
}
