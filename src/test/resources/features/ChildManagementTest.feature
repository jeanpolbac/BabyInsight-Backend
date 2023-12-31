Feature: Children Management

  Background:
    Given I am an authenticated user

  Scenario: Parent able to add a child to their profile for tracking vaccines and medication
    When the parent adds a child with name "Han Solo" and date of birth "2024-05-04"
    Then the child should be successfully added to the parent's profile

  Scenario: Parent able to view a list of their children
    When the parent views the list of their children
    Then the list should include "Han Solo"

  Scenario: Parent able to edit a child's details
    Given the child "Han Solo" exists in the parent's profile
    When the parent edits the child's name to "Han Solo Jr."
    Then the child's name should be updated to "Han Solo Jr."

  Scenario: Parent able to view a child's profile
    Given the child "Han Solo Jr." exists in the parent's profile
    When the parent views the profile of "Han Solo Jr."
    Then the profile should display the name "Han Solo Jr." and date of birth "2024-05-04"
