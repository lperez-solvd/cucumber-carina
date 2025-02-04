Feature: Sauce Demo Purchase test
  In order to purchase a product, I need to login first


  Scenario: Purchase - passing
    Given I am logged in
    When I add products to the cart
    And I go to checkout
    And I submit my zipping information
    And I confirm my purchase
    Then page successful should open