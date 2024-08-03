package com.darkthor.Configruation;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/api/v1/product/**")
                        .uri("lb://PRODUCT-SERVICE"))
                .route("order-service", r -> r.path("/api/v1/order/**")
                        .uri("lb://ORDER-SERVICE"))
                .route("inventory-service", r -> r.path("/api/v1/inventory/**")
                        .uri("lb://INVENTORY-SERVICE"))
                .build();
    }
}
