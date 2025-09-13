Feature: Book Management
  As a user
  I want to add a new book
  So that I can manage my library

  Scenario: Add a valid book
    Given I have a book with title "Spring Boot"
    When I add the book
    Then the system should save it successfully
