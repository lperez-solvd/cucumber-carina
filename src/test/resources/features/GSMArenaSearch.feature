Feature: GSM Arena Search testing
  In order to use Cucumber in my project, I want to check how to test GSM Arena Search

  @demo
  Scenario: GSM Arena open page - passing
    Given I am on the main page
    When I click 'Search' input and search
    Then page 'Search' should be open
    And page 'Search' should contains search text
