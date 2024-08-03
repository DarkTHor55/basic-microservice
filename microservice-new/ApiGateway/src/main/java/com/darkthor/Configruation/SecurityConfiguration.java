package com.darkthor.Configruation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration  {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
       return http
                 .csrf(ServerHttpSecurity.CsrfSpec::disable)
                 .authorizeExchange(e->e.pathMatchers("/eureka/**")
                         .permitAll()
                         .anyExchange().authenticated()
                 ).oauth2ResourceServer(oauth2 -> oauth2
                       .jwt(Customizer.withDefaults())
               ).build();

    }
}
