Feature: Vaccine Tracker

  Background:
    Given I am an authenticated user
    And the system has a list of standard vaccines for newborns in the database

  Scenario: Retrieve pre-loaded list of vaccines
    When a request is made to fetch the list of vaccines
    Then the system should return the pre-loaded list of standard vaccines

  Scenario: Retrieve list of administered vaccines for a child
    Given the system has a record of vaccines administered to a child
    When a request is made to fetch the list of administered vaccines for the child
    Then the system should return the list of vaccines administered to the child

  Scenario: Retrieve list of remaining vaccines for a child
    Given the system has a record of vaccines to be administered to a child
    When a request is made to fetch the list of remaining vaccines for the child
    Then the system should return the list of vaccines yet to be administered to the child

  Scenario: Update a vaccine as administered in the database
    When the "Mark as Administered" action is triggered for "HepB" vaccine with a date of "2023-10-18"
    Then "HepB" status should be updated to "administered" with the date "2023-10-18"
