package com.example.book_management.service;

import com.example.book_management.entity.User;
import com.example.book_management.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Test
    void shouldRejectShortPasswordOnRegister() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserService svc = new UserService(repo, encoder);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> svc.register("john", "123"));
        assertTrue(ex.getMessage().contains("Password"));
    }

    @Test
    void loginShouldReturnTrueWhenPasswordMatches() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String raw = "secret";
        com.example.book_management.entity.User saved = new com.example.book_management.entity.User("john", encoder.encode(raw));
        Mockito.when(repo.findByUsername("john")).thenReturn(Optional.of(saved));
        UserService svc = new UserService(repo, encoder);
        assertTrue(svc.login("john", raw));
    }
}
