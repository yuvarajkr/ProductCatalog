package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Client.FakeStore;
import com.amazonclone.productcatalog.Dtos.CategoryDto;
import com.amazonclone.productcatalog.Dtos.FakestoreProductDto;
import com.amazonclone.productcatalog.Model.Category;
import com.amazonclone.productcatalog.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Primary
@Service
public class FakeProductService implements IProductService {

    @Autowired
    public RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStore fakeStore;

    @Override
    public Product getProductById(Long id) {
        FakestoreProductDto fakestoreProductDto = fakeStore.getProductById(id);
        return getProduct(fakestoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakestoreProductDto> fakestoreProductDtos = fakeStore.getAllProduct();
        List<Product> products = new ArrayList<>();
        for(FakestoreProductDto fakestoreProductDto : fakestoreProductDtos){
            products.add(getProduct(fakestoreProductDto));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakestoreProductDto fakestoreProductDto = fakeStore.createProduct(getFakeProductDto(product));
        return getProduct(fakestoreProductDto);
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        FakestoreProductDto fakestoreProductDto = fakeStore.replaceProduct(getFakeProductDto(input),id);
        return getProduct(fakestoreProductDto);
    }

    private FakestoreProductDto getFakeProductDto(Product product) {
        FakestoreProductDto fakestoreProductDto = new FakestoreProductDto();
        fakestoreProductDto.setId(product.getId());
        fakestoreProductDto.setDescription(product.getDescription());
        fakestoreProductDto.setTitle(product.getName());
        fakestoreProductDto.setDescription(product.getDescription());
        fakestoreProductDto.setPrice(product.getPrice());
        fakestoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory()!=null) {
            fakestoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakestoreProductDto;
    }

    private Product getProduct(FakestoreProductDto fakestoreProductDto) {
        Product product = new Product();
        if (fakestoreProductDto != null && fakestoreProductDto.getId() != null) {
            product.setId(fakestoreProductDto.getId());
            product.setName(fakestoreProductDto.getTitle());
            product.setDescription(fakestoreProductDto.getDescription());
            product.setPrice(fakestoreProductDto.getPrice());
            product.setImageUrl(fakestoreProductDto.getImage());
            Category category = new Category();
            category.setName(fakestoreProductDto.getCategory());
            product.setCategory(category);
        } else {
            System.out.println("FakestoreProductDto or ID is null, unable to convert.");
        }
        return product;
    }
}



