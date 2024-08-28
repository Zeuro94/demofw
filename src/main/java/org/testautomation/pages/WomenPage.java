package org.testautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.CSS;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class WomenPage extends BasePage {

    @FindBy(css = "#maincontent > div.columns > div.column.main > div.products.wrapper.grid.products-grid > ol > li")
    private List<WebElement> itemsOnPage;

    @FindBy(css = "body > div.page-wrapper > div.breadcrumbs > ul")
    private WebElement womenTops;

    @FindBy(css= "div > div > strong")
    private WebElement nameElement;

    @FindBy(css = "div > div > div.swatch-opt-1812 > div.swatch-attribute.size > div")
    private List<WebElement> sizeOptionsElement;

    @FindBy(css = "div > div > div.swatch-opt-1812 > div.swatch-attribute.color > div")
    private List<WebElement> colorOptionsElement;

    @FindBy(css = "body > div.page-wrapper > header > div.header.content > div.minicart-wrapper > a")
    private WebElement cartButton;

    @FindBy(css = "#top-cart-btn-checkout")
    private WebElement proceedToCheckout;

    @FindBy(xpath = "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")
    private WebElement messageText;

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOnPage() {
        return waitForElementToBeDisplayed(womenTops);
    }

    public void addToCartByIndex(int index) {
        if (index < 1) {
            throw new IllegalArgumentException("Index should be 1 or greater");
        }
        String xpath = String.format("//li[%d]//div[1]//div[1]//div[4]//div[1]//div[1]//form[1]//button[1]//span[1]", index);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        addToCartButton.click();
    }

    public void goToPayment(){
        waitForElementToBeDisplayed(cartButton);
        cartButton.click();
        proceedToCheckout.click();
    }

    public String getMessageTest() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOf(messageText));
        return messageText.getText();
    }

    public WebElement getNthItem(int index) {
        if (index >= 0 && index < itemsOnPage.size()) {
            return itemsOnPage.get(index);
        } else {
            throw new IndexOutOfBoundsException(String.format("Invalid index: %d. There are only %d items in the grid.", index, itemsOnPage.size()));
        }
    }

    public List<WebElement> getNthItemSizeOptions(int index) {
        WebElement nthItem = getNthItem(index);
        WebElement sizeOptionsContainer = nthItem.findElement(By.cssSelector("div > div > div.swatch-opt-1812 > div.swatch-attribute.size"));
        return sizeOptionsContainer.findElements(By.cssSelector("div"));
    }

    public List<WebElement> getNthItemColorOptions(int index) {
        WebElement nthItem = getNthItem(index);
        WebElement colorOptionsContainer = nthItem.findElement(By.cssSelector("div > div > div.swatch-opt-1812 > div.swatch-attribute.color"));
        return colorOptionsContainer.findElements(By.cssSelector("div"));
    }

}
