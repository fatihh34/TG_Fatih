package com.TG.utilities;



import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class reusableMethods {


    public static void assertAndPrintMessage(WebElement element, String attribute, String expectedMessage) {

        String actualMessage;

        if (attribute.equalsIgnoreCase("text")) {
            reusableMethods.waitForVisibility(element, 20);
            actualMessage = element.getText();
        } else {
            reusableMethods.waitForVisibility(element, 20);
            actualMessage = element.getAttribute(attribute);
        }


        Assert.assertEquals(expectedMessage, actualMessage);

        System.out.println("Expected Message: " + expectedMessage);
        System.out.println("Actual Message: " + actualMessage);
    }

    //Bu method yuklemek istedigimiz fotografi secmemizi saglar.
    public static void uploadFile(String fileName, WebElement uploadElement) {
        String separator = System.getProperty("file.separator");
        String path = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "resources" + separator + "fotos" + separator + fileName;
        reusableMethods.waitFor(2);
        uploadElement.sendKeys(path);
    }


    public static void highlightElement(WebElement element) {
        // JavaScriptExecutor kullanarak öğeyi sarı renkle vurgula
        String highlightScript = "arguments[0].style.border='3px solid yellow';";
        ((JavascriptExecutor) driver.getDriver()).executeScript(highlightScript, element);

        // Öğenin rengini belirli bir süre sonra geri çevirin
        try {
            Thread.sleep(2000); // 2 saniye bekleyin
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String resetScript = "arguments[0].style.border='';";
        ((JavascriptExecutor) driver.getDriver()).executeScript(resetScript, element);
    }

    public static void waitForAndClick(WebDriver driver, WebElement element) {
        int maxWaitTimeInSeconds = 10;
        int intervalInMillis = 500;
        long startTime = System.currentTimeMillis();

        while ((System.currentTimeMillis() - startTime) < (maxWaitTimeInSeconds * 1000)) {
            try {
                if (element.isDisplayed() && element.isEnabled()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    break;
                }
            } catch (Exception e) {
                // Element not found or not clickable, continue waiting
            }
            try {
                Thread.sleep(intervalInMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * JavaScript ile webelement olusturma
     *
     * @param javascriptYolu internet sitesinden sag klik ile JS yolunu kopyala ile alınan metin olacak
     */
    public static WebElement webelementJavaScript(String javascriptYolu) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        WebElement webElement = (WebElement) js.executeScript("return " + javascriptYolu + "");
        return webElement;
    }

    //JS GetAttributeValue
    public static void getValueByJS(String id, String attributeName) {
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        String attribute_Value = (String) js.executeScript("return document.getElementById('" + id + "')." + attributeName);
        System.out.println("Attribute Value: = " + attribute_Value);

    }

    public static String getCssJavaScript(String xpath) {
        WebElement spanElement = driver.getDriver().findElement(By.xpath(xpath));

        // JavaScriptExecutor kullanarak metni alın
        String metin = (String) ((JavascriptExecutor) driver.getDriver()).executeScript("return arguments[0].textContent;", spanElement);

        return metin;

    }


    /**
     * Bu metot ile elementin className değeri string olarak verilerek o classtaki text alinir.
     *
     * @param className text degeri alinmak istenen class ismi string olarak verilir
     * @return
     */
    public static String getTextWithJavaScript(String className) {
        WebElement element = driver.getDriver().findElement(By.className(className));

        // JavaScriptExecutor kullanarak elementin içeriğini al
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver.getDriver();
        String text = (String) jsExecutor.executeScript("return arguments[0].textContent;", element);
        return text;
    }

    public static void robotClassDosyaYukleme(String filePath) {
        try {
            reusableMethods.bekle(3);
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            Robot robot = new Robot();
            //pressing ctrl+v
            robot.keyPress(KeyEvent.VK_CONTROL);
            reusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_V);
            reusableMethods.bekle(3);
            //releasing ctrl+v
            robot.keyRelease(KeyEvent.VK_CONTROL);
            reusableMethods.bekle(3);
            robot.keyRelease(KeyEvent.VK_V);
            reusableMethods.bekle(3);
            System.out.println("PASSED");
            //pressing enter
            reusableMethods.bekle(3);
            robot.keyPress(KeyEvent.VK_ENTER);
            reusableMethods.bekle(3);
            //releasing enter
            robot.keyRelease(KeyEvent.VK_ENTER);
            reusableMethods.bekle(3);
            System.out.println("ENTER");
        } catch (Exception e) {
        }
    }

    //Alert Wait
    public static void alertWait(int sayi) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(sayi));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //SwitchToWindow1
    public static void switchToWindowNew(int sayi) {
        List<String> tumWindowHandles = new ArrayList<String>(driver.getDriver().getWindowHandles());
        driver.getDriver().switchTo().window(tumWindowHandles.get(sayi));
    }

    /**
     * Javascript ile click yapma
     */

    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }


    //========Switching Window=====//
    public static void switchToWindow(String targetTitle) {
        String origin = driver.getDriver().getWindowHandle();
        for (String handle : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(handle);
            if (driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        driver.getDriver().switchTo().window(origin);
    }

    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(driver.getDriver());
        actions.moveToElement(element).perform();
    }

    //==========Return a list of string given a list of Web Element====////
    public static List<String> stringListeCevir(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //   HARD WAIT WITH THREAD.SLEEP
//   waitFor(5);  => waits for 5 second
    public static void bekle(int saniye) {
        try {
            Thread.sleep(saniye * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //===============Explicit Wait==============//
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                bekle(1);
            }
        }
    }

    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeout) {
        //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver.getDriver())
                .withTimeout(Duration.ofSeconds(3))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(1));//Check for the element every 1 second

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });

        return element;
    }

    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }


    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle) {

        Set<String> allWindowsHandles = driver.getDriver().getWindowHandles();

        for (String each : allWindowsHandles) {

            driver.getDriver().switchTo().window(each);

            System.out.println("Current URL: " + driver.getDriver().getCurrentUrl());

            if (driver.getDriver().getCurrentUrl().contains(expectedInUrl)) {
                break;
            }
        }

        //5. Assert:Title contains “expectedInTitle”
        String actualTitle = driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }


    public static void verifyTitle(String expectedTitle) {

        Assert.assertEquals(driver.getDriver().getTitle(), expectedTitle);

    }


    public static void waitForInvisibilityOf(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }


    public static void verifyURLContains(String expectedInURL) {
        Assert.assertTrue(driver.getDriver().getCurrentUrl().contains(expectedInURL));
    }


    public static List<String> dropdownOptionsAsString(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);


        List<WebElement> actualOptionsAsWebElement = select.getOptions();


        List<String> actualOptionsAsString = new ArrayList<>();


        for (WebElement each : actualOptionsAsWebElement) {
            actualOptionsAsString.add(each.getText());
        }

        return actualOptionsAsString;

    }


    public static void clickRadioButton(List<WebElement> radioButtons, String attributeValue) {

        for (WebElement each : radioButtons) {

            if (each.getAttribute("value").equalsIgnoreCase(attributeValue)) {
                each.click();
            }
        }
    }


    public static void switchToWindow1(String targetTitle) {
        String origin = driver.getDriver().getWindowHandle();
        for (String handle : driver.getDriver().getWindowHandles()) {
            driver.getDriver().switchTo().window(handle);
            if (driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        driver.getDriver().switchTo().window(origin);
    }


    public static void hover1(WebElement element) {
        Actions actions = new Actions(driver.getDriver());
        actions.moveToElement(element).perform();
    }


    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }


    public static List<String> getElementsText1(By locator) {

        List<WebElement> elems = driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }


    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static WebElement waitForVisibility1(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    public static WebElement waitForVisibility1(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static WebElement waitForClickablility1(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public static WebElement waitForClickablility1(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static void waitForPageToLoad1(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }


    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue("Element not visible: " + by, driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);

        }
    }


    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse("Element should not be visible: " + by, driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
    }


    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);

        }
    }


    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }


    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].click();", element);
    }


    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public static void doubleClick(WebElement element) {
        new Actions(driver.getDriver()).doubleClick(element).build().perform();
    }


    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }


    public static void highlight(WebElement element) {
        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        waitFor(1);
        ((JavascriptExecutor) driver.getDriver()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }


    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }


    public static void clickWithTimeOut1(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }


    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) driver.getDriver();
        jse.executeScript(command, element);

    }


    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) driver.getDriver();
        jse.executeScript(command);

    }


    public static void clickWithWait(By by, int attempts) {
        int counter = 0;

        while (counter < attempts) {
            try {

                clickWithJS(driver.getDriver().findElement(by));

                break;
            } catch (WebDriverException e) {

                e.printStackTrace();
                ++counter;

                waitFor(1);
            }
        }
    }


    public static void waitForPresenceOfElement(By by, long time) {
        new WebDriverWait(driver.getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void retryClick(WebElement element) {
        for (int i = 0; i < 3; i++) {
            try {
                click(element);
                break;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                waitFor(2);
            }


        }

    }}
