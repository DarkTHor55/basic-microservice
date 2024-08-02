package com.darkthor.Serivce;

import com.darkthor.Model.Product;
import com.darkthor.Repository.ProductRepository;
import com.darkthor.Request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Mapper mapper;

    public Product saveProduct(ProductRequest productRequest) {
        Product product=mapper.toProduct(productRequest);
        return productRepository.save(product);
    }

    public List<Product> allProduct() {
        return mapper.allProduct();
    }

    public Product getProductById(Integer id) throws Exception {
        return productRepository.findById(id).orElseThrow(()->new Exception("Product not found"));
    }
}
