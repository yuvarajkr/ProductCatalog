package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISearchService {

    Page<Product> searchProduct(String searchText, int page, int rows);
}
