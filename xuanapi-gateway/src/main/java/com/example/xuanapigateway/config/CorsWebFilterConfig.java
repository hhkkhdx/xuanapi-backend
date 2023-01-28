package com.example.xuanapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.server.ServerWebExchange;

@Configuration
public class CorsWebFilterConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfigurationSource corsConfigurationSource = new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(ServerWebExchange serverWebExchange) {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.addAllowedHeader("*");
                corsConfig.addAllowedMethod("*");
                corsConfig.addAllowedOriginPattern("*");
                corsConfig.setMaxAge(1800L);
                corsConfig.setAllowCredentials(true);
                return corsConfig;
            }
        };
        return new CorsWebFilter(corsConfigurationSource);
    } }
