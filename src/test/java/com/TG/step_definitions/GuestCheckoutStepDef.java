package com.TG.step_definitions;

import com.TG.pages.magento.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GuestCheckoutStepDef {
    private HomePage homePage = new HomePage();
    private CategoryPage categoryPage = new CategoryPage();
    private ProductPage productPage = new ProductPage();
    private CartPage cartPage = new CartPage();
    private CheckoutPage checkoutPage = new CheckoutPage();

    @Given("user is on the Magento home page")
    public void user_is_on_the_magento_home_page() {
        homePage.navigateToHomePage();
    }

    @When("user navigates to Gear > Bags category")
    public void user_navigates_to_gear_bags_category() {
        homePage.navigateToGearBagsCategory();
    }

    @And("user selects a random product from the list")
    public void user_selects_a_random_product_from_the_list() {
        categoryPage.selectRandomProduct();
        productPage.waitForProductPageToLoad();
    }

    @And("user adds the selected product to the cart")
    public void user_adds_the_selected_product_to_the_cart() {
        productPage.addProductToCart();
    }

    @And("user opens the cart and updates quantity to {int}")
    public void user_opens_the_cart_and_updates_quantity_to(Integer quantity) {
        productPage.openCart();
        cartPage.waitForCartPageToLoad();
        cartPage.getInitialTotalPrice();
        cartPage.updateQuantity(quantity);
    }

    @And("user verifies the total price is updated correctly")
    public void user_verifies_the_total_price_is_updated_correctly() {
        cartPage.verifyTotalPriceUpdated();
    }

    @And("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
        checkoutPage.waitForCheckoutPageToLoad();
    }

    @And("user completes checkout as guest with dummy data")
    public void user_completes_checkout_as_guest_with_dummy_data() {
        checkoutPage.selectGuestCheckout();
        checkoutPage.fillGuestCheckoutForm();
    }

    @And("user chooses a shipping method and continues")
    public void user_chooses_a_shipping_method_and_continues() {
        checkoutPage.continueToShipping();
        checkoutPage.selectShippingMethodAndContinue();
    }

    @And("user submits the order")
    public void user_submits_the_order() {
        checkoutPage.placeOrder();
    }

    @Then("user verifies that order is successfully placed")
    public void user_verifies_that_order_is_successfully_placed() {
        checkoutPage.verifyOrderSuccess();
    }
}
