package com.literalura.liter_alura.books.domain.service;

import java.util.List;

import com.literalura.liter_alura.books.domain.entity.Book;
import com.literalura.liter_alura.gutendex.domain.dto.BookRecord;

public interface BookService {
    Book  saveBook(BookRecord bookRecord);
    List<BookRecord> getBooksByTitle(String title);
}