package demosuite;

import org.openqa.selenium.WebElement;
import org.testautomation.utility.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testautomation.utility.Constants.URL;

public class SuiteTest extends BaseTest {

    @Test
    public void goToUrl() {
        // Open Google Chrome instance and navigate to desired page, checking if is on correct page and click to Women Tops Products.
        browserFunctions.goToUrl(Config.get(URL));
        Assert.assertTrue(homePage.isOnPage());
        homePage.clickOnWomenTopButton();
    }

    @Test(dependsOnMethods = "goToUrl")
    public void selectItems() throws InterruptedException {

        //Check if is it on Women Tops page, check if it will be on or more products.
        //Based on values from json file the product will have size and color.
        
        Assert.assertTrue(womenPage.isOnPage());
        if (recordOneProduct.multipleProduct()) {
            List<WebElement> sizeOptions = womenPage.getNthItemSizeOptions(recordtwoProducts.productNumber1());
            sizeOptions.get(recordtwoProducts.sizeOption1()).click();
            List<WebElement> colorOptions = womenPage.getNthItemColorOptions(recordtwoProducts.productNumber1());
            colorOptions.get(recordtwoProducts.colorOption1()).click();
            womenPage.addToCartByIndex(recordtwoProducts.cartIndex1());

            List<WebElement> sizeOptions2 = womenPage.getNthItemSizeOptions(recordtwoProducts.productNumber2());
            sizeOptions2.get(recordtwoProducts.sizeOption2()).click();
            List<WebElement> colorOptions2 = womenPage.getNthItemColorOptions(recordtwoProducts.productNumber2());
            colorOptions2.get(recordtwoProducts.colorOption2()).click();
            womenPage.addToCartByIndex(recordtwoProducts.cartIndex2());
        } else {
            List<WebElement> sizeOptions = womenPage.getNthItemSizeOptions(recordOneProduct.productNumber());
            sizeOptions.get(recordOneProduct.sizeOption()).click();

            List<WebElement> colorOptions = womenPage.getNthItemColorOptions(recordOneProduct.productNumber());
            colorOptions.get(recordOneProduct.colorOption()).click();

            womenPage.addToCartByIndex(recordOneProduct.cartIndex());
        }

        Thread.sleep(3000);
    }

    @Test(dependsOnMethods = "selectItems")
    public void addToCartItems() {
        //One single statement to segregate adding, checkout and filling shipping info.
        //The following method will click on cart icon and proceed to checkout.
        womenPage.goToPayment();
    }

    @Test(dependsOnMethods = "addToCartItems")
    public void fillShippingOptions() {
        // Method will, check if is on right page and after will fill field for shipping.
        Assert.assertTrue(shippingPage.isOnPage());
        shippingPage.setEmail(recordOneProduct.email());
        shippingPage.setFirstName(recordOneProduct.firstName());
        shippingPage.setLastName(recordOneProduct.lastName());
        shippingPage.setStreetAddress(recordOneProduct.address());
        shippingPage.setCity(recordOneProduct.city());
        shippingPage.setStateProvince(recordOneProduct.stateProvince());
        shippingPage.setPostalCode(recordOneProduct.postalCode());
        shippingPage.setCountry(recordOneProduct.country());
        shippingPage.setPhoneNumberField(recordOneProduct.phoneNumber());
        shippingPage.setShippingMethod(recordOneProduct.shippingMethod());
        shippingPage.clickOnNextButton();
        shippingPage.clickOnPlaceOrderButton();
        Assert.assertTrue(pageTitle.getText().equals("Thank you for your purchase!");
    }
}
