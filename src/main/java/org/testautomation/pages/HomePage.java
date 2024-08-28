package org.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import javax.swing.*;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "a[id='ui-id-3'] span")
    private WebElement whatsNewButton;

    @FindBy(css = "a[id='ui-id-4'] span:nth-child(2)")
    private WebElement womenButton;

    @FindBy(css = "a[id='ui-id-9'] span:nth-child(2)")
    private WebElement womenTopsButton;

    @FindBy(css = "a[id='ui-id-5'] span:nth-child(2)")
    private WebElement menButton;

    @FindBy(css = "a[id='ui-id-6'] span:nth-child(2)")
    private WebElement gearButton;

    @FindBy(css = "a[id='ui-id-7'] span:nth-child(2)")
    private WebElement trainingButton;

    @FindBy(css = "a[id='ui-id-8'] span")
    private WebElement saleButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return waitForElementToBeDisplayed(saleButton);
    }

    public void clickOnWhatsNew() {
        whatsNewButton.click();
    }

    public void clickOnWomenButton() {
        womenButton.click();
    }

    public void clickOnMenButton() {
        menButton.click();
    }

    public void clickOnGearButton() {
        gearButton.click();
    }

    public void clickOnTrainingButton() {
        trainingButton.click();
    }

    public void clickOnSaleButton() {
        saleButton.click();
    }

    public void clickOnWomenTopButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(womenButton).perform();
        womenTopsButton.click();
    }
}
