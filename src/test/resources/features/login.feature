Feature: User Login
  Scenario: Successful login
    Given a registered user with username "alice" and password "pass123"
    When I login with username "alice" and password "pass123"
    Then the login result is "Login successful"

  Scenario: Failed login
    Given a registered user with username "bob" and password "pw1234"
    When I login with username "bob" and password "wrong"
    Then the login result is "Invalid credentials"
