package com.darkthor.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.*;

@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient customWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8083").build();
    }
}
