package com.TG.pages.magento;

import com.TG.utilities.driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    public ProductPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(css = ".page-title-wrapper h1")
    public WebElement productTitle;

    @FindBy(css = "button[title='Add to Cart']")
    public WebElement addToCartButton;

    @FindBy(css = ".message-success")
    public WebElement successMessage;

    @FindBy(css = "a[href*='checkout/cart']")
    public WebElement cartLink;

    private WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(20));

    public void waitForProductPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        System.out.println("Product added to cart: " + successMessage.getText());
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        cartLink.click();
    }
}
