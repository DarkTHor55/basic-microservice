package com.darkthor.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.*;

@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient webClientBuilder() {
        return WebClient.builder().build();

    }
}
