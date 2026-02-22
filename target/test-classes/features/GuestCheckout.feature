@magento
Feature: Magento Guest Checkout Flow
  As a guest user
  I want to complete a checkout process
  So that I can purchase products without creating an account

  Scenario: Complete guest checkout with random product from Gear > Bags
    Given user is on the Magento home page
    When user navigates to Gear > Bags category
    And user selects a random product from the list
    And user adds the selected product to the cart
    And user opens the cart and updates quantity to 2
    And user verifies the total price is updated correctly
    And user proceeds to checkout
    And user completes checkout as guest with dummy data
    And user chooses a shipping method and continues
    And user submits the order
    Then user verifies that order is successfully placed
