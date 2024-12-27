package com.literalura.liter_alura.gutendex.infrastructure.api;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.literalura.liter_alura.gutendex.domain.dto.SearchResponse;

import reactor.netty.http.client.HttpClient;

@Component
public class GutendexClient {

    private final WebClient webClient;
    private final ApiConfig apiConfig; // Declare apiConfig as a field

    public GutendexClient(WebClient.Builder webClientBuilder, ApiConfig apiConfig) {

        HttpClient httpClient = HttpClient.create()
        .followRedirect(true);

        this.webClient = webClientBuilder
                .baseUrl(apiConfig.getBaseUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient)) // Use the custom HttpClient
                .build();
        this.apiConfig = apiConfig; // Initialize apiConfig
    }

    public SearchResponse searchBooks(String query) {
        String url = String.format("/books?search=%s", query);
        System.out.println("Constructed URL: " + apiConfig.getBaseUrl() + url);
    
        try {
            // Debug raw response
            // String rawResponse = webClient.get()
            // .uri(url)
            // .header("Accept", "application/json") // Ensure JSON response
            // .retrieve()
            // .bodyToMono(String.class)
            // .block();
    
            // System.out.println("Raw Response: " + rawResponse);
    
            // Parse response into SearchResponse
            return webClient.get()
                    .uri(url)
                    .header("Accept", "application/json")
                    .retrieve()
                    .bodyToMono(SearchResponse.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching books from Gutendex API", e);
        } 
    }
    
}
