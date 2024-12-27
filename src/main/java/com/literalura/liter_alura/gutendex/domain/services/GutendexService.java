package com.literalura.liter_alura.gutendex.domain.services;

import com.literalura.liter_alura.gutendex.domain.dto.SearchResponse;

public interface GutendexService {
    SearchResponse searchBooks(String query);
}
