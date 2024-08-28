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
        browserFunctions.goToUrl(Config.get(URL));
        Assert.assertTrue(homePage.isOnPage());
        homePage.clickOnWomenTopButton();
    }

    @Test(dependsOnMethods = "goToUrl")
    public void selectItems() throws InterruptedException {
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
        womenPage.goToPayment();
    }

    @Test(dependsOnMethods = "addToCartItems")
    public void fillShippingOptions() {
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
    }
}
