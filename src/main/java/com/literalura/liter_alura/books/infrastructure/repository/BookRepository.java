package com.literalura.liter_alura.books.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.literalura.liter_alura.books.domain.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query(value = "SELECT unnest(languages) AS language, COUNT(*) FROM books GROUP BY language", nativeQuery = true)
    List<Object[]> countBooksByLanguage();

    List<Book> findTop10ByOrderByDownloadCountDesc();
}
