Feature: Children Management

  Scenario: Parent able to add a child to their profile for tracking vaccines and medication
    Given the parent is logged in with credentials "test@email.com" and password "password12345"
    When the parent adds a child with name "Han Solo" and date of birth "2024-05-04"
    Then the child should be successfully added to the parent's profile

  Scenario: Parent able to view a list of their children
    Given the parent is logged in with credentials "test@email.com" and password "password12345"
    When the parent views the list of their children
    Then the list should include "Han Solo"

  Scenario: Parent able to edit a child's details
    Given the parent is logged in with credentials "test@email.com" and password "password12345"
    And the child "Han Solo" exists in the parent's profile
    When the parent edits the child's name to "Han Solo Jr."
    Then the child's name should be updated to "Han Solo Jr."

  Scenario: Parent able to view a child's profile
    Given the parent is logged in with credentials "test@email.com" and password "password12345"
    And the child "Han Solo" exists in the parent's profile
    When the parent views the profile of "Han Solo"
    Then the profile should display the name "Han Solo" and date of birth "2024-05-04"

