package com.example.book_management.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class LoginAndRegisterUITest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Make sure ChromeDriver is installed and path set
        driver = new ChromeDriver();
        driver.get("http://localhost:8080"); // Adjust to your app’s URL
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testRegisterPageLoads() {
        driver.get("http://localhost:8080/register");
        String title = driver.getTitle();
        assertTrue(title.contains("Register"));
    }

    @Test
    void testLoginPageLoads() {
        driver.get("http://localhost:8080/login");
        WebElement usernameField = driver.findElement(By.name("username"));
        assertNotNull(usernameField);
    }
}
