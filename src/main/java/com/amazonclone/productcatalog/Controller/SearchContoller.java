package com.amazonclone.productcatalog.Controller;

import com.amazonclone.productcatalog.Dtos.SearchInputDto;
import com.amazonclone.productcatalog.Model.Product;
import com.amazonclone.productcatalog.Service.ISearchService;
import com.amazonclone.productcatalog.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchContoller {

    @Autowired
    private ISearchService searchService;

    @PostMapping
    public Page<Product> searchProduct(@RequestBody SearchInputDto searchInputDto) {
        if(searchInputDto.getKeyword()==null){
            throw new IllegalArgumentException("searchInput cannot be null");
        }

        Page<Product> response = searchService.searchProduct(searchInputDto.getKeyword(),
                                                 searchInputDto.getPage(),
                                                 searchInputDto.getRows());
        return null;
    }
}
