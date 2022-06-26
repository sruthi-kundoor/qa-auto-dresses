Feature: Add highest priced item to cart

  Scenario: Add item to cart
    Given I navigate to the website
    When I click on Dresses Menu
    And I select the highest price item
		Then Add the selected highest price item to the cart