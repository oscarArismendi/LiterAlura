package com.literalura.liter_alura.authors.domain.service;

import java.util.List;

import com.literalura.liter_alura.authors.domain.entity.Author;

public interface AuthorService {
    List<Author> getAll();

    List<Author> getAuthorsAliveInYear(int year);
}
