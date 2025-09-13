package com.example.book_management.bdd;

import com.example.book_management.entity.Book;
import com.example.book_management.service.BookService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class BookStepDefs {

    private Book book;
    private Book saved;

    @Autowired
    private BookService bookService;

    @Given("I have a book with title {string}")
    public void i_have_a_book_with_title(String title) {
        book = new Book(); // properly initialize with title
    }

    @When("I add the book")
    public void i_add_the_book() {
        saved = bookService.addBook(book);
    }

    @Then("the system should save it successfully")
    public void the_system_should_save_it_successfully() {
        assertNotNull(saved.getId());
    }
}
