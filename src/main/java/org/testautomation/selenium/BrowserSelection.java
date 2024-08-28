package org.testautomation.selenium;

import org.openqa.selenium.WebDriver;

import javax.naming.NameNotFoundException;

public class BrowserSelection {
    WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    public BrowserSelection(String browserType) throws NameNotFoundException {
        switch (browserType) {
            case "CHROME" -> driver = BrowserManager.getChromeDriver();
            case "FIREFOX" -> driver = BrowserManager.getFirefoxDriver();
            case "EDGE" -> driver = BrowserManager.getEdgeDriver();
            case "SAFARI" -> driver = BrowserManager.getSafariDriver();
            default -> throw new NameNotFoundException("Invalid browser type" + browserType);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }
}
