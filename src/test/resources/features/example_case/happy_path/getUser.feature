#@example_case #@happyPath
Feature: getUser - Happy Path

  #@example_case #@GENERAL
  Scenario: Clients want to get the user information
    Given The endpoint is "UserRequestService"
    And The user id is "1"
    When I try to get document
    Then Verify the response contains
      | Key                      | Value            |
      | userName                 | Freud            |