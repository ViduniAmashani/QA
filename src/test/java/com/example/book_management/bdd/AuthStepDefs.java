package com.example.book_management.bdd;

import com.example.book_management.entity.User;
import com.example.book_management.repository.UserRepository;
import com.example.book_management.service.UserService;
import io.cucumber.java.en.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthStepDefs {

    // Use simple in-memory repository for BDD (spring context may provide JPA repo if configured).
    private final UserRepository repo;
    private final UserService svc;
    private String lastResult;

    public AuthStepDefs(UserRepository repo) {
        this.repo = repo;
        this.svc = new UserService(repo, new BCryptPasswordEncoder());
    }

    @Given("a registered user with username {string} and password {string}")
    public void registerUser(String username, String password) {
        try {
            svc.register(username, password);
        } catch (Exception ignored) { }
    }

    @When("I login with username {string} and password {string}")
    public void i_login(String username, String password) {
        boolean ok = svc.login(username, password);
        lastResult = ok ? "Login successful" : "Invalid credentials";
    }

    @Then("the login result is {string}")
    public void check_result(String expected) {
        assertEquals(expected, lastResult);
    }

}
