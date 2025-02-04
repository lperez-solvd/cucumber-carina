Feature: Sauce Demo login page
  In order to use Saucedemo, I need to login


  Scenario: Login - passing
    Given I am on login page
    When I enter my username
    And I enter my password
    And I submit my information
    Then page 'Home' should open