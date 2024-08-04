package com.darkthor.Controller;

import com.darkthor.Model.Order;
import com.darkthor.Request.OrderRequest;
import com.darkthor.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<ResponseEntity<String>> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return CompletableFuture.supplyAsync(()-> new ResponseEntity<>("Order placed successfully", HttpStatus.OK));
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException exception) {
        return CompletableFuture.supplyAsync(()-> "Oops! Something went wrong. Please try again later.");
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(orderService.allOrders(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = orderService.orderById(id);
        if (order != null){
            return new ResponseEntity<>(order,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
