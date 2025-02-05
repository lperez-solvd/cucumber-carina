Feature: Sauce Demo login page
  In order to use Saucedemo, I need to login


  Scenario Outline: Login with different users

    Given I am on login page
    When I enter my username for "<userID>"
    And I enter my password
    And I submit my information
    Then the "<expected>" result should be found

    Examples:
      | userID | expected |
      | 1      | true     |
      | 2      | false    |
      | 4      | true     |
