package com.TG.pages.magento;

import com.TG.utilities.driver;
import com.TG.utilities.reusableMethods;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(css = "input[data-role='cart-item-qty']")
    public WebElement quantityInput;

    @FindBy(css = "button[title='Update Shopping Cart']")
    public WebElement updateCartButton;

    @FindBy(css = ".grand.totals .price")
    public WebElement totalPrice;

    @FindBy(css = "button[data-role='proceed-to-checkout']")
    public WebElement proceedToCheckoutButton;

    private WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(20));
    private String initialTotalPrice;

    public void waitForCartPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(quantityInput));
    }

    public String getInitialTotalPrice() {
        initialTotalPrice = totalPrice.getText();
        System.out.println("Initial total price: " + initialTotalPrice);
        return initialTotalPrice;
    }

    public void updateQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        quantityInput.sendKeys(Keys.TAB);
        reusableMethods.waitFor(2);
        
        try {
            if (updateCartButton.isDisplayed()) {
                updateCartButton.click();
            }
        } catch (Exception e) {
            // Update might happen automatically
        }
        
        reusableMethods.waitFor(3);
    }

    public void verifyTotalPriceUpdated() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        String updatedTotalPrice = totalPrice.getText();
        System.out.println("Updated total price: " + updatedTotalPrice);
        
        Assert.assertNotEquals("Total price should be updated", initialTotalPrice, updatedTotalPrice);
        System.out.println("Total price verification passed!");
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
        proceedToCheckoutButton.click();
    }
}
