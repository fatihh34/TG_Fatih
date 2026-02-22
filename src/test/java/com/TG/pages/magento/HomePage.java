package com.TG.pages.magento;

import com.TG.utilities.ConfigurationReader;
import com.TG.utilities.driver;
import com.TG.utilities.reusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[contains(text(),'Gear') or contains(@href,'gear')]")
    public WebElement gearMenu;

    @FindBy(xpath = "//a[contains(text(),'Bags') or contains(@href,'bags')]")
    public WebElement bagsSubMenu;
    
    @FindBy(css = "a[href*='gear']")
    public WebElement gearMenuByCss;
    
    @FindBy(css = "a[href*='bags']")
    public WebElement bagsSubMenuByCss;

    private WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(20));

    public void navigateToHomePage() {
        driver.getDriver().get(ConfigurationReader.getProperty("url"));
        reusableMethods.waitFor(3);
    }

    public void navigateToGearBagsCategory() {
        reusableMethods.waitFor(3);
        
        WebElement gearElement = null;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Gear') or contains(@href,'gear')]")));
            gearElement = gearMenu;
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='gear']")));
                gearElement = gearMenuByCss;
            } catch (Exception e2) {
                driver.getDriver().get(ConfigurationReader.getProperty("url") + "/gear/bags.html");
                wait.until(ExpectedConditions.titleContains("Bags"));
                return;
            }
        }
        
        Actions actions = new Actions(driver.getDriver());
        actions.moveToElement(gearElement).perform();
        reusableMethods.waitFor(2);
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(bagsSubMenu));
            bagsSubMenu.click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(bagsSubMenuByCss));
                bagsSubMenuByCss.click();
            } catch (Exception e2) {
                driver.getDriver().get(ConfigurationReader.getProperty("url") + "/gear/bags.html");
            }
        }
        
        wait.until(ExpectedConditions.or(
            ExpectedConditions.titleContains("Bags"),
            ExpectedConditions.urlContains("bags")
        ));
        reusableMethods.waitFor(2);
    }
}
