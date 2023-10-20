Feature: Authentication

  Scenario: User able to access public endpoints
    Given a valid public endpoint
    When I say hello
    Then Hello is shown

  Scenario: Successful registration
    Given I am on the registration page "/auth/users/register/"
    When I enter a valid email address "test@email.com" and a strong password "password12345"
    Then I should be successfully registered

  Scenario: User able to login and receive jwt token
    Given the registered user exists with credentials "test@email.com" and password "password 12345"
    When the user details are validated
    Then the user receives a jwt token
