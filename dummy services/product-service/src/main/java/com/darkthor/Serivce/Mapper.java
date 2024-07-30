package com.darkthor.Serivce;

import com.darkthor.Model.Product;
import com.darkthor.Repository.ProductRepository;
import com.darkthor.Request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Mapper {
    private final ProductRepository productRepository;
    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();
    }

    public List<Product> allProduct() {
        return  productRepository.findAll();
    }
}
