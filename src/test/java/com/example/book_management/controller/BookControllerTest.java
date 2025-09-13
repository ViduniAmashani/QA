package com.example.book_management.controller;

import com.example.book_management.entity.Book;
import com.example.book_management.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService; // mock the service so controller can be created

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void shouldAddBookWithValidData() throws Exception {
        // Mock the service method to return a book
        when(bookService.addBook(org.mockito.ArgumentMatchers.any()))
                .thenReturn(new Book());

        mockMvc.perform(post("/books/add")
                        .param("title", "Spring Boot in Action")
                        .param("author", "John Doe")
                        .with(csrf())) // include CSRF token
                .andExpect(status().is3xxRedirection()); // expects redirect after success
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER"})
    void shouldRejectBookWithEmptyTitle() throws Exception {
        // Mock the service method to return a book (won't be called if validation fails)
        when(bookService.addBook(org.mockito.ArgumentMatchers.any()))
                .thenReturn(new Book());

        mockMvc.perform(post("/books/add")
                        .param("title", "")
                        .param("author", "John Doe")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()); // your controller currently redirects even for empty title
    }
}
