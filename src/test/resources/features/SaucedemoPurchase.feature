Feature: Sauce Demo Purchase test
  In order to purchase a product, I need to login first


  Scenario Outline: Purchase items with a user and order

    Given I am logged in with "<userID>"
    Then I should be on the homepage
    When I add products to the cart from "<orderID>"
    And I go to checkout
    And I submit my zipping information
    And I confirm my purchase
    Then page successful should open

    Examples:
      | userID | orderID |
      | 1      | 1       |
      | 1      | 2       |
      | 1      | 3       |
      | 1      | 4       |