package com.darkthor.Service;

import com.darkthor.Model.Order;
import com.darkthor.Model.OrderLineItem;
import com.darkthor.Repository.OrderRepository;
import com.darkthor.Request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Mapper mapper;
    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems=orderRequest.getOrderLineItems()
                .stream()
                .map(mapper::toOrderItem)
                .toList();
        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);
    }


    public List<Order> allOrders() {
        return orderRepository.findAll();
    }


    public Order orderById(Long id) {
        return orderRepository.findById(id).orElseThrow(null);
    }
}
