package com.amazonclone.productcatalog.Service;

import com.amazonclone.productcatalog.Dtos.CategoryDto;
import com.amazonclone.productcatalog.Dtos.FakestoreProductDto;
import com.amazonclone.productcatalog.Model.Category;
import com.amazonclone.productcatalog.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductService implements IProductService {

    @Autowired
    public RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakestoreProductDto fakestoreProductDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                                                    FakestoreProductDto.class,id).getBody();
        if (fakestoreProductDto == null) {
            System.out.println("FakestoreProductDto is null");
        } else {
            System.out.println("Fetched FakestoreProductDto: ID=" + fakestoreProductDto.getId()
                    + ", Title=" + fakestoreProductDto.getTitle()
                    + ", Description=" + fakestoreProductDto.getDescription()
                    + ", Category=" + fakestoreProductDto.getCategory()
                    + ", Image=" + fakestoreProductDto.getImage()
                    + ", Price=" + fakestoreProductDto.getPrice());
        }
        return convertProductDtoToProduct(fakestoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakestoreProductDto[] productList = restTemplate.getForEntity("https://fakestoreapi.com/products",
                                                    FakestoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for(FakestoreProductDto fakestoreProductDto : productList){
            products.add(convertProductDtoToProduct(fakestoreProductDto));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        return null;
    }

    public Product convertProductDtoToProduct(FakestoreProductDto fakestoreProductDto) {
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
