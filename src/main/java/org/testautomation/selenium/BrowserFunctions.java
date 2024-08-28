package org.testautomation.selenium;

import org.openqa.selenium.WebDriver;

public class BrowserFunctions {

    WebDriver driver;

    public BrowserFunctions(WebDriver driver) {
        this.driver = driver;
    }

    public void closeBrowser() {
        driver.close();
    }

    public void goToUrl(String url) {
        driver.get(url);
    }
}
