package com.literalura.liter_alura.authors.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.liter_alura.authors.domain.entity.Author;
import com.literalura.liter_alura.authors.domain.service.AuthorService;
import com.literalura.liter_alura.authors.infrastructure.repository.AuthorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        List<Author> authors = authorRepository.findAll();

        if (authors.isEmpty()) {
            return null;
        }

        return authors;
    }

    @Override
    public List<Author> getAuthorsAliveInYear(int year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanOrDeathYearIsNull(year, year);
    }
}
