Feature: Vaccine Tracker

  Scenario: Retrieve pre-loaded list of vaccines from database
    Given the system has a list of standard vaccines for newborns in the database
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
    Given the system has a list of vaccines
    When the "Mark as Administered" action is triggered for a specific vaccine with a given date
    Then that vaccine's status should be updated to "administered" with the specified date in the database

  Scenario: Identify past due vaccines
    Given the system has a list of vaccines with due dates
    When the current date is checked
    Then the system should identify which vaccines are past due


