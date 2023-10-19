Feature: Medication Log

  Scenario: Add new medication with all required fields
    Given I am on the medication log page
    When I fill in the fields for medication name, dosage, frequency, and duration
    Then the new medication should appear in the list of all active medications

  Scenario: View list of all active medications
    Given I have added medications to the log
    When I visit the medication log page
    Then I should see a list of all active medications

  Scenario: Archive completed or expired medications
    Given I have completed or expired medications in the active list
    When I click the "Archive" button next to a completed or expired medication
    Then that medication should be removed from the active list
    And it should be moved to the archived list