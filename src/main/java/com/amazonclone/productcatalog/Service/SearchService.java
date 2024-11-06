package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Dtos.SortParam;
import com.amazonclone.productcatalog.Dtos.SortType;
import com.amazonclone.productcatalog.Model.Product;
import com.amazonclone.productcatalog.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService{

    @Autowired
    public ProductRepo productRepo;

    @Override
    public Page<Product> searchProduct(String searchText, int page, int rows, List<SortParam> sortParamList) {
        Sort sort = null;
        if (!sortParamList.isEmpty()) {
            if(sortParamList.get(0).equals(SortType.ASC))
                sort = sort.by(sortParamList.get(0).getParameter());
            else
                sort = sort.by(sortParamList.get(0).getParameter());

            for(int i=1;i<sortParamList.size();i++){
                if(sortParamList.get(i).equals(SortType.ASC)){
                    sort.and(sort.by(sortParamList.get(i).getParameter()));
                }
                else
                    sort.and(sort.by(sortParamList.get(i).getParameter()));
            }
        }
        return productRepo.findProductByName(searchText, PageRequest.of(page,rows,sort));
    }
}
