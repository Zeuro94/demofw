package org.testautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testautomation.pages.ShippingPage;
import org.testautomation.pages.WomenPage;

import java.util.List;

public class Main {

    static WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();

        driver.get("https://magento.softwaretestingboard.com/women/tops-women.html");
        driver.manage().window().maximize();

        WomenPage womenTopsPage  = new WomenPage(driver);
        List<WebElement> sizeOptions = womenTopsPage.getNthItemSizeOptions(0);

        sizeOptions.get(1).click();

        List<WebElement> colorOptions = womenTopsPage.getNthItemColorOptions(0);

        colorOptions.get(1).click();
        womenTopsPage.addToCartByIndex(0);

        System.out.println(womenTopsPage.getMessageTest());
        womenTopsPage.goToPayment();

        ShippingPage shippingPage = new ShippingPage(driver);
        shippingPage.setEmail("email@email.com");
        shippingPage.setFirstName("test");
        shippingPage.setLastName("test");
        //shippingPage.setCompany("test");
        shippingPage.setStreetAddress("streetTest");
        shippingPage.setCity("city");
        shippingPage.setStateProvince("Alabama");
        shippingPage.setPostalCode("121212");
        shippingPage.setCountry("United States");
        shippingPage.setPhoneNumberField("+4072323232");
        shippingPage.setShippingMethod(1);
        shippingPage.clickOnNextButton();
        shippingPage.clickOnPlaceOrderButton();
        System.out.println(shippingPage.getPageTitle());
    }

}