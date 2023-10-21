Feature: Medication Log

  Background:
    Given I am an authenticated user

  Scenario: Add new medication with all required fields
    When I am on the medication log page
    And I fill in the fields for medication "TestMed", "20mg", "Once a day", and "5 days"
    Then the new medication should appear in the list of all active medications

  Scenario: View list of all active medications
    When I visit the medication log page
    Then I should see a list of all active medications

    Given I have a list of active medications for a child with ID "{childID}"
    When I archive a medication with ID "{medicationID}"
    Then the medication with ID "{medicationID}" should be in the archived list
