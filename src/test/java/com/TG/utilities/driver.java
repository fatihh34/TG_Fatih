package com.TG.utilities;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class driver {
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    private driver() {

    }

    public static WebDriver getDriver() {

        if (driverPool.get() == null){


            String browserType = ConfigurationReader.getProperty("browser");



            switch (browserType){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("lang=tr");
                    
                    // Advanced Cloudflare bot detection bypass
                    chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-web-security");
                    chromeOptions.addArguments("--allow-running-insecure-content");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    
                    // Remove automation flags
                    chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "enable-logging"});
                    chromeOptions.setExperimentalOption("useAutomationExtension", false);
                    
                    // Realistic user agent
                    chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
                    
                    // Add preferences to make browser look more real
                    chromeOptions.addArguments("--disable-features=IsolateOrigins,site-per-process");
                    chromeOptions.addArguments("--disable-site-isolation-trials");
                    
                    driverPool.set(new ChromeDriver(chromeOptions));
                    
                    // Advanced JavaScript injection to hide automation
                    org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driverPool.get();
                    
                    // Remove webdriver property
                    js.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
                    
                    // Override plugins
                    js.executeScript("Object.defineProperty(navigator, 'plugins', {get: () => [1, 2, 3, 4, 5]})");
                    
                    // Override languages
                    js.executeScript("Object.defineProperty(navigator, 'languages', {get: () => ['en-US', 'en', 'tr']})");
                    
                    // Override permissions
                    js.executeScript("const originalQuery = window.navigator.permissions.query; window.navigator.permissions.query = (parameters) => (parameters.name === 'notifications' ? Promise.resolve({ state: Notification.permission }) : originalQuery(parameters));");
                    
                    // Override chrome object
                    js.executeScript("window.chrome = {runtime: {}};");
                    
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;

                case "safari":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new SafariDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;

            }
        }

        return driverPool.get();

    }


    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}