package com.literalura.liter_alura.gutendex.application;

import org.springframework.stereotype.Service;

import com.literalura.liter_alura.gutendex.domain.dto.SearchResponse;
import com.literalura.liter_alura.gutendex.domain.services.GutendexService;
import com.literalura.liter_alura.gutendex.infrastructure.api.GutendexClient;

@Service
public class GutendexServiceImpl implements GutendexService {
    private final GutendexClient gutendexClient;

    public GutendexServiceImpl(GutendexClient gutendexClient) {
        this.gutendexClient = gutendexClient;
    }

    @Override
    public SearchResponse searchBooks(String query) {
        return gutendexClient.searchBooks(query);
    }
}
