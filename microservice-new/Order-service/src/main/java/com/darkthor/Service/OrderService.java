package com.darkthor.Service;

import com.darkthor.Model.Order;
import com.darkthor.Model.OrderLineItem;
import com.darkthor.Repository.OrderRepository;
import com.darkthor.Request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.*;

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
    private final WebClient webClient;
    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems=orderRequest.getOrderLineItems()
                .stream()
                .map(mapper::toOrderItem)
                .toList();
        order.setOrderLineItems(orderLineItems);
        // call in ventory service and check  place order is in sotck or not
        boolean result=webClient.get()
                .uri("http://localhost:8083/api/v1/inventory")
                .retrieve()
                .bodyToMono(Boolean.class)// mono is use to read data from webclient
                .block();

        if(result){
            orderRepository.save(order);
        }else {
            throw new RuntimeException("Not enough stock to place order");
        }
    }


    public List<Order> allOrders() {
        return orderRepository.findAll();
    }


    public Order orderById(Long id) {
        return orderRepository.findById(id).orElseThrow(null);
    }
}
