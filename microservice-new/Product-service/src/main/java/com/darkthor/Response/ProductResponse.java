package com.darkthor.Response;

import com.darkthor.Model.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String productname;
    private boolean created;
    private Product product;
}
