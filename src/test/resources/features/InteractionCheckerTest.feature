Feature: Interaction Checker

  Scenario: Input two medications and find interactions
    Given I have a predefined list of medication interactions
    When I send a request to check interactions between "Medication A" and "Medication B"
    Then the application should return the interaction details if found

  Scenario: Input multiple medications and find interactions
    Given I have a predefined list of medication interactions
    When I send a request to check interactions among "Medication A", "Medication B", and "Medication C"
    Then the application should return the interaction details if found

  Scenario: Input medications with no interactions
    Given I have a predefined list of medication interactions
    When I send a request to check interactions between "Medication X" and "Medication Y"
    Then the application should return a confirmation message stating no interactions found

  Scenario: Input one medication only
    Given I have a predefined list of medication interactions
    When I send a request to check interactions for "Medication A" only
    Then the application should return an error message stating that at least two medications are required for interaction check
