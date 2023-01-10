package com.programmingtechie.productservice.service;

import com.programmingtechie.productservice.Repository.ProductRepository;
import com.programmingtechie.productservice.dto.ProductRequest;
import com.programmingtechie.productservice.dto.ProductResponse;
import com.programmingtechie.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Service
@RequiredArgsConstructor     //at compile time it will create all the constructors for us
public class ProductService {
    private  final ProductRepository productRepository;



    public void createProduct(ProductRequest productRequest){
        Product product=Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        productRepository.save(product);

    }

    public List<ProductResponse> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
    }


}
