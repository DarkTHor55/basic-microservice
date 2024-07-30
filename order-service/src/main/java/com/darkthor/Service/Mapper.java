package com.darkthor.Service;

import com.darkthor.Model.OrderLineItem;
import com.darkthor.Request.OrderLineItemRequest;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public OrderLineItem toOrderItem(OrderLineItemRequest orderLineItemRequest) {
            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setPrice(orderLineItemRequest.getPrice());
            orderLineItem.setQuantity(orderLineItemRequest.getQuantity());
            orderLineItem.setSkuCode(orderLineItemRequest.getSkuCode());
            return orderLineItem;
    }
}
