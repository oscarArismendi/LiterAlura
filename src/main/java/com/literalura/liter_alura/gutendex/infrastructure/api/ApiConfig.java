package com.literalura.liter_alura.gutendex.infrastructure.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    @Value("${gutendex.api.base-url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}
