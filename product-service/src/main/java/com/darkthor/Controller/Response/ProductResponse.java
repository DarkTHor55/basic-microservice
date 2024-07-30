package com.darkthor.Controller.Response;

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
