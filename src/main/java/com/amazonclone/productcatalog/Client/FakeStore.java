package com.amazonclone.productcatalog.Client;

import com.amazonclone.productcatalog.Dtos.FakestoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStore {

    @Autowired
    public RestTemplateBuilder restTemplateBuilder;

    public FakestoreProductDto getProductById(Long id) {
        ResponseEntity<FakestoreProductDto> fakestoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.GET,null, FakestoreProductDto.class, id);
        return fakestoreProductDtoResponseEntity.getBody();
    }

    public List<FakestoreProductDto> getAllProduct(){
        ResponseEntity<FakestoreProductDto[]> response = requestForEntity("https://fakestoreapi.com/products",HttpMethod.GET,null,FakestoreProductDto[].class);
        List<FakestoreProductDto> fakestoreProductDtoList = new ArrayList<>();
        for(FakestoreProductDto fakestoreProductDto : response.getBody()){
            fakestoreProductDtoList.add(fakestoreProductDto);
        }
        return fakestoreProductDtoList;
    }

    public FakestoreProductDto createProduct(FakestoreProductDto product) {
        ResponseEntity<FakestoreProductDto> fakestoreProductDto = requestForEntity("https://fakestoreapi.com/products", HttpMethod.POST, product, FakestoreProductDto.class);
        return fakestoreProductDto.getBody();
    }

    public FakestoreProductDto replaceProduct(FakestoreProductDto fakeProductDto, Long id) {
        ResponseEntity<FakestoreProductDto> response = requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PUT,fakeProductDto, FakestoreProductDto.class,id);
        return response.getBody();
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

}
