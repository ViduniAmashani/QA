package com.example.book_management.service;

import com.example.book_management.entity.Book;
import com.example.book_management.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private final BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private final BookService bookService = new BookService(bookRepository);

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setId(1L); // set ID for assertion
        book.setTitle("Spring Boot"); // set title for assertion

        Mockito.when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.addBook(book);
        assertNotNull(result.getId());
        assertEquals("Spring Boot", result.getTitle());
    }

    @Test
    void testBookTitleCannotBeEmpty() {
        Book book = new Book(); // title is null

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> bookService.addBook(book));
        assertEquals("Book title cannot be empty", ex.getMessage());
    }
}
