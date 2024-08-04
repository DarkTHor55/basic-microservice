package com.darkthor.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomCircuitBreakerHealthIndicator implements HealthIndicator {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CustomCircuitBreakerHealthIndicator(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @Override
    public Health health() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("inventory");
        if (circuitBreaker.getState() == CircuitBreaker.State.OPEN) {
            return Health.down().withDetail("Circuit Breaker State", "OPEN").build();
        } else if (circuitBreaker.getState() == CircuitBreaker.State.HALF_OPEN) {
            return Health.status("HALF_OPEN").withDetail("Circuit Breaker State", "HALF_OPEN").build();
        } else {
            return Health.up().withDetail("Circuit Breaker State", "CLOSED").build();
        }
    }
}
