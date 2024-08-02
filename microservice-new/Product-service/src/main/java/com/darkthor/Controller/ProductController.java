package com.darkthor.Controller;

import com.darkthor.Response.ProductResponse;
import com.darkthor.Model.Product;
import com.darkthor.Request.ProductRequest;
import com.darkthor.Serivce.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        Product product1 =productService.saveProduct(product);
        ProductResponse response = new ProductResponse();
        if (product1 == null) {
            response.setCreated(false);
            response.setProductname("Product Not created");
            response.setProduct(null);
            return ResponseEntity.badRequest().body(response);
        }else {
            response.setCreated(true);
            response.setProductname(product1.getName());
            response.setProduct(product1);
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.allProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) throws Exception {
        Product product = productService.getProductById(id);
        ProductResponse response = new ProductResponse();
        if (product==null){
            response.setCreated(false);
            response.setProductname("Product Not found");
            response.setProduct(null);
            return ResponseEntity.badRequest().body(response);
        }else{
            response.setCreated(true);
            response.setProductname(product.getName());
            response.setProduct(product);

           return ResponseEntity.ok(response);
        }
    }
}
