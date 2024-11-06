package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Model.Product;
import com.amazonclone.productcatalog.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService{

    @Autowired
    public ProductRepo productRepo;

    @Override
    public Page<Product> searchProduct(String searchText, int page, int rows) {
        return productRepo.findProductByName(searchText, PageRequest.of(page,rows));
    }
}
