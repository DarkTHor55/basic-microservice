package com.darkthor.Request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineItemRequest {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
