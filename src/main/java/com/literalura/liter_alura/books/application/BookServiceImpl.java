package com.literalura.liter_alura.books.application;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.literalura.liter_alura.authors.domain.entity.Author;
import com.literalura.liter_alura.authors.infrastructure.repository.AuthorRepository;
import com.literalura.liter_alura.books.domain.entity.Book;
import com.literalura.liter_alura.books.domain.service.BookService;
import com.literalura.liter_alura.books.infrastructure.repository.BookRepository;
import com.literalura.liter_alura.gutendex.domain.dto.AuthorRecord;
import com.literalura.liter_alura.gutendex.domain.dto.BookRecord;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book saveBook(BookRecord bookRecord) {
        // Convert BookRecord to Book entity
        Set<Author> authors = bookRecord.authors().stream()
            .map(this::findOrCreateAuthor)
            .collect(Collectors.toSet());

        Book book = new Book(
            null,
            bookRecord.title(),
            bookRecord.languages(),
            bookRecord.download_count(),
            authors
        );

        // Save the book and cascade to authors
        return bookRepository.save(book);
    }

    @Override
    public List<BookRecord> getBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(book -> new BookRecord(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthors().stream()
                        .map(author -> new AuthorRecord(author.getName(), author.getBirthYear(), author.getDeathYear()))
                        .collect(Collectors.toSet()),
                    book.getLanguages(),
                    book.getDownload_count()
                ))
                .collect(Collectors.toList());
    }

    private Author findOrCreateAuthor(AuthorRecord authorRecord) {
        return authorRepository.findByName(authorRecord.name())
            .orElseGet(() -> {
                Author author = new Author(
                    authorRecord.name(),
                    authorRecord.birth_year(),
                    authorRecord.death_year()
                );
                return authorRepository.save(author);
            });
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            return null;
        }

        return books;
    }
    
    @Override
    public Map<String, Long> getBooksCountByLanguage() {
        List<Object[]> results = bookRepository.countBooksByLanguage();
        return results.stream()
            .collect(Collectors.toMap(
                result -> (String) result[0],  // Language (key)
                result -> (Long) result[1]    // Count (value)
            ));
    }
}
