package com.TG.pages.magento;

import com.TG.utilities.driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CategoryPage {
    public CategoryPage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(css = ".product-item-link")
    public List<WebElement> productLinks;

    private WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(20));

    public String selectRandomProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productLinks));
        Assert.assertFalse("No products found in the category", productLinks.isEmpty());
        
        Random random = new Random();
        int randomIndex = random.nextInt(productLinks.size());
        WebElement selectedProduct = productLinks.get(randomIndex);
        String productName = selectedProduct.getText();
        System.out.println("Selected product: " + productName);
        
        wait.until(ExpectedConditions.elementToBeClickable(selectedProduct));
        selectedProduct.click();
        
        return productName;
    }
}
