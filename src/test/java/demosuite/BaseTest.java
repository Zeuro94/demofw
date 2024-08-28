package demosuite;

import org.openqa.selenium.WebDriver;
import org.testautomation.model.RecordOneProduct;
import org.testautomation.model.RecordTwoProducts;
import org.testautomation.pages.HomePage;
import org.testautomation.pages.ShippingPage;
import org.testautomation.pages.WomenPage;
import org.testautomation.selenium.BrowserFunctions;
import org.testautomation.selenium.BrowserSelection;
import org.testautomation.utility.Config;
import org.testautomation.utility.JsonUtility;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import javax.naming.NameNotFoundException;

import java.time.Duration;

import static org.testautomation.utility.Constants.BROWSER;

public abstract class BaseTest {

    WebDriver driver;
    HomePage homePage;
    ShippingPage shippingPage;
    WomenPage womenPage;
    BrowserFunctions browserFunctions;
    RecordOneProduct recordOneProduct;
    RecordTwoProducts recordtwoProducts;

    @BeforeSuite
    public void setupConfig() {
        Config.initialize();
    }

    @BeforeTest
    @Parameters({"testData", "testData2"})
    public void setup (String testData, String testData2) throws NameNotFoundException {
        driver = new BrowserSelection(Config.get(BROWSER)).getDriver();
        browserFunctions = new BrowserFunctions(driver);
        homePage = new HomePage(driver);
        womenPage = new WomenPage(driver);
        shippingPage = new ShippingPage(driver);
        recordOneProduct = JsonUtility.getTestData(testData, RecordOneProduct.class);
        recordtwoProducts = JsonUtility.getTestData(testData2, RecordTwoProducts.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            browserFunctions.closeBrowser();
        }
    }
}
