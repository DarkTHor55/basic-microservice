package com.darkthor.Request;

import com.darkthor.Model.OrderLineItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private List<OrderLineItemRequest>orderLineItems;
}
