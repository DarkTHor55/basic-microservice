package com.darkthor.Response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private String skuCode;
    private boolean isInStock;
}
