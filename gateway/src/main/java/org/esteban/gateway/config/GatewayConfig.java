package org.esteban.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public GlobalFilters globalFilters() {
        return new GlobalFilters();
    }
}
