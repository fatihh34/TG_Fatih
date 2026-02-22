package com.TG.pages.magento;

import com.github.javafaker.Faker;
import com.TG.utilities.driver;
import com.TG.utilities.reusableMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    public CheckoutPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(css = "input[value='guest']")
    public WebElement guestCheckoutRadio;

    @FindBy(css = "input[name='email']")
    public WebElement emailField;

    @FindBy(css = "input[name='firstname']")
    public WebElement firstNameField;

    @FindBy(css = "input[name='lastname']")
    public WebElement lastNameField;

    @FindBy(css = "input[name='company']")
    public WebElement companyField;

    @FindBy(css = "input[name='street[0]']")
    public WebElement streetAddressField;

    @FindBy(css = "select[name='country_id']")
    public WebElement countryDropdown;

    @FindBy(css = "select[name='region_id']")
    public WebElement stateDropdown;

    @FindBy(css = "input[name='city']")
    public WebElement cityField;

    @FindBy(css = "input[name='postcode']")
    public WebElement zipCodeField;

    @FindBy(css = "input[name='telephone']")
    public WebElement phoneNumberField;

    @FindBy(css = "button[data-role='opc-continue']")
    public WebElement continueButton;

    @FindBy(css = "button[data-role='opc-continue'][title='Next']")
    public WebElement nextButton;

    @FindBy(css = "input[name='shipping_method']")
    public WebElement shippingMethodRadio;

    @FindBy(css = "button[title='Place Order']")
    public WebElement placeOrderButton;

    @FindBy(css = ".page-title span")
    public WebElement orderSuccessMessage;

    private WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(20));
    private Faker faker = new Faker();

    public void waitForCheckoutPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(emailField));
    }

    public void selectGuestCheckout() {
        try {
            if (guestCheckoutRadio.isDisplayed()) {
                guestCheckoutRadio.click();
            }
        } catch (Exception e) {
            // Guest checkout might be default
        }
    }

    public void fillGuestCheckoutForm() {
        String email = faker.internet().emailAddress();
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(email);
        System.out.println("Email entered: " + email);
        
        reusableMethods.waitFor(1);
        
        firstNameField.sendKeys(faker.name().firstName());
        lastNameField.sendKeys(faker.name().lastName());
        companyField.sendKeys(faker.company().name());
        streetAddressField.sendKeys(faker.address().streetAddress());
        
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("United States");
        reusableMethods.waitFor(1);
        
        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByIndex(1);
        
        cityField.sendKeys(faker.address().city());
        zipCodeField.sendKeys(faker.address().zipCode());
        phoneNumberField.sendKeys(faker.phoneNumber().cellPhone());
        
        System.out.println("Shipping address completed");
    }

    public void continueToShipping() {
        reusableMethods.waitFor(2);
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            continueButton.click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(nextButton));
                nextButton.click();
            } catch (Exception e2) {
                WebElement continueBtn = driver.getDriver().findElement(By.cssSelector("button[data-role='opc-continue']"));
                continueBtn.click();
            }
        }
        
        reusableMethods.waitFor(3);
    }

    public void selectShippingMethodAndContinue() {
        try {
            if (shippingMethodRadio.isDisplayed()) {
                shippingMethodRadio.click();
                reusableMethods.waitFor(1);
            }
        } catch (Exception e) {
            // Shipping method might be auto-selected
        }
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextButton.click();
        } catch (Exception e) {
            try {
                WebElement nextBtn = driver.getDriver().findElement(By.cssSelector("button[title='Next']"));
                nextBtn.click();
            } catch (Exception e2) {
                // Continue button might have different selector
            }
        }
        
        reusableMethods.waitFor(3);
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
        reusableMethods.waitFor(5);
    }

    public void verifyOrderSuccess() {
        wait.until(ExpectedConditions.visibilityOf(orderSuccessMessage));
        String successText = orderSuccessMessage.getText();
        System.out.println("Order success message: " + successText);
        
        Assert.assertTrue("Order should be successfully placed", 
            successText.toLowerCase().contains("thank") || 
            successText.toLowerCase().contains("success") ||
            successText.toLowerCase().contains("order"));
        
        System.out.println("Order placement verified successfully!");
    }
}
