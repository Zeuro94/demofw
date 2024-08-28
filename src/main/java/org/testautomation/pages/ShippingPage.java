package org.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShippingPage extends BasePage{

    @FindBy(css = "#customer-email")
    private WebElement emailField;

    @FindBy(name = "firstname")
    private WebElement firstNameField;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    @FindBy(css = "#A039XOJ")
    private WebElement companyField;

    @FindBy(name = "street[0]")
    private WebElement streetAddressField;

    @FindBy(name = "city")
    private WebElement cityField;

    @FindBy(name = "region_id")
    private WebElement stateDropdown;

    @FindBy(name = "postcode")
    private WebElement postalCodeField;

    @FindBy(name = "country_id")
    private WebElement countryDropdown;

    @FindBy(name = "telephone")
    private WebElement phoneNumberField;

    @FindBy(css = "#checkout-shipping-method-load > table > tbody > tr")
    private List<WebElement> shippingMethodOptions;

    @FindBy(css = "#shipping-method-buttons-container > div > button > span")
    private WebElement nextButton;

    @FindBy(css = "span[data-bind=\"i18n: 'Place Order'\"]")
    private WebElement placeOrderButton;

    @FindBy(css = "span[data-ui-id='page-title-wrapper']")
    private WebElement pageTitle;

    public ShippingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return waitForElementToBeDisplayed(emailField);
    }

    public void setEmail(String email) {
        waitForElementToBeDisplayed(emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setFirstName(String firstName) {
        waitForElementToBeDisplayed(firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setCompany(String company) {
        companyField.clear();
        companyField.sendKeys(company);
    }

    public void setStreetAddress(String streetAddress) {
        streetAddressField.clear();
        streetAddressField.sendKeys(streetAddress);
    }

    public void setCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void setStateProvince(String stateName) {
        Select selectStateProvince = new Select(stateDropdown);
        selectStateProvince.selectByVisibleText(stateName);
    }

    public void setPostalCode(String postalCode) {
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void setCountry(String countryName) {
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(countryName);
    }

    public void setPhoneNumberField(String telephone) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(telephone);
    }

    public void setShippingMethod(int index) {
        if (index >= 0 && index < shippingMethodOptions.size()) {
            WebElement shippingMethodRow = shippingMethodOptions.get(index);
            WebElement radioButton = shippingMethodRow.findElement(By.cssSelector("input[type='radio']"));
            radioButton.click();
        } else {
            throw new IndexOutOfBoundsException(String.format("Invalid index: %d. There are only %d shipping methods available.", index, shippingMethodOptions.size()));
        }
    }

    public void clickOnNextButton() {
        nextButton.click();
    }

    public void clickOnPlaceOrderButton() {
        waitForElementToBeDisplayed(placeOrderButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
        js.executeScript("arguments[0].click();", placeOrderButton);
    }

    public String getPageTitle() throws InterruptedException {
        waitForElementToBeDisplayed(pageTitle);
        Thread.sleep(7000);
        return pageTitle.getText();
    }
}
