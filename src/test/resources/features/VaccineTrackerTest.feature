Feature: Vaccine Tracker

#  Background:
#    Given I am an authenticated user

  Scenario: Adding a new vaccine
    Given a new vaccine with name "Hepatitis B"
    When the vaccine is added to the system
    Then the system should return the created vaccine

  Scenario: Retrieving all vaccines
    Given there are existing vaccines in the system for a child at 2 months
    When I retrieve all vaccines
    Then I should get a list of vaccines

  Scenario: Retrieving a vaccine by ID
    Given there is a vaccine with ID 1 in the system
    When I retrieve the vaccine with ID 1
    Then I should get the vaccine details


#
#  Scenario: Retrieve list of administered vaccines for a child
#    Given the system has a record of vaccines administered to a child
#    When a request is made to fetch the list of administered vaccines for the child
#    Then the system should return the list of vaccines administered to the child
#
#  Scenario: Retrieve list of remaining vaccines for a child
#    Given the system has a record of vaccines to be administered to a child
#    When a request is made to fetch the list of remaining vaccines for the child
#    Then the system should return the list of vaccines yet to be administered to the child
#
#  Scenario: Update a vaccine as administered in the database
#    When the "Mark as Administered" action is triggered for "HepB" vaccine with a date of "2023-10-18"
#    Then "HepB" status should be updated to "administered" with the date "2023-10-18"
