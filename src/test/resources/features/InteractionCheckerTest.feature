Feature: Interaction Checker

  Background:
    Given I am an authenticated user
    And I have access to the Drug Interaction API with RXCUIs "207106+152923+656659" and sources "ONCHigh+DrugBank"

  Scenario Outline: Check interactions between medications
    When I send a request to check interactions among "<MedicationList>"
    Then the application should return "<ResponseMessage>"

    Examples:
      | MedicationList         | ResponseMessage                                                                         |
      | 207106, 152923         | interaction details if found                                                            |
      | 207106, 152923, 656659 | interaction details if found                                                            |
      | 123456, 654321         | confirmation message stating no interactions found                                      |
      | 207106                 | error message stating that at least two medications are required for interaction check  |


#  Resource: https://webkul.com/blog/parametrization-in-cucumber/