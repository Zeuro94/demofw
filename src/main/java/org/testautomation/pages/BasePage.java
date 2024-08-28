package org.testautomation.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected static WebDriver driver;
    private WebDriverWait wait;
    private WebElement webElement;
    List<WebElement> webElementList;
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    protected BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time
    }

    public abstract boolean isOnPage();

    protected Boolean waitForElementToBeDisplayed(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        return fluentWait
                .withMessage("Element " + element.toString() + " wasn't displayed after 30 seconds.")
                .until(webDriver -> element.isDisplayed());
    }

    public WebElement returnElementFromObject(By by, String value) {
        webElementList = driver.findElements(by);

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class);

        try {
            wait.until(webDriver -> webElementList);
        } catch (StaleElementReferenceException e) {
            log.info("Element is stale, trying to find it again...");
            try {
                wait.until(webDriver -> webElementList);
            } catch (Exception ex) {
                log.info("Failed to find element again: {} ", ex.getMessage());
            }
        } catch (Exception e) {
            log.info("Failed to interact with element: {}", e.getMessage());
        }

        for (WebElement element : webElementList) {
            if (element.getText().equalsIgnoreCase(value)) {
                webElement = element;
            }
        }
        return webElement;
    }
}
