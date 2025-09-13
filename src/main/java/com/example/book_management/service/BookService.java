package com.example.book_management.service;

import com.example.book_management.entity.Book;
import com.example.book_management.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        return bookRepository.save(book);
    }
}
