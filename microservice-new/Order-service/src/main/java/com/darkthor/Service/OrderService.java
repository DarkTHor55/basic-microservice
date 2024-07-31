package com.darkthor.Service;

import com.darkthor.Model.Order;
import com.darkthor.Model.OrderLineItem;
import com.darkthor.Repository.OrderRepository;
import com.darkthor.Request.OrderRequest;
import com.darkthor.Response.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Mapper mapper;
    private final WebClient webClient;
    private final Logger logger= LoggerFactory.getLogger(OrderService.class);

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItems()
                .stream()
                .map(mapper::toOrderItem)
                .toList();
        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItem::getSkuCode)
                .toList();

        // Call inventory service to check stock
        try {
            InventoryResponse[] inventoryResponsesArray = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/api/v1/inventory")
                            .queryParam("skuCode", String.join(",", skuCodes))
                            .build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .doOnNext(responses -> System.out.println("Inventory Responses: " + Arrays.toString(responses)))
                    .block();

            // Check for null response
            if (inventoryResponsesArray == null) {
                throw new RuntimeException("Failed to fetch inventory information");
            }

            boolean allInStock = Arrays.stream(inventoryResponsesArray)
                    .allMatch(InventoryResponse::isInStock);

            if (allInStock) {
                orderRepository.save(order);
            } else {
                throw new RuntimeException("Not enough stock to place order");
            }
        } catch (WebClientRequestException e) {
            logger.error("WebClient request error: {}", e.getMessage());
            throw new RuntimeException("Error while checking inventory", e);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            throw new RuntimeException("Error while checking inventory", e);
        }

    }

    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    public Order orderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
