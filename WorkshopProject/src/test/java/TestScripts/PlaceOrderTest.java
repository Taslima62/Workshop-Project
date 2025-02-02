package TestScripts;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import tools.DriverFactory;


public class PlaceOrderTest extends BaseTest {
    @BeforeTest
    public void commonActions() throws InterruptedException {
        driver = DriverFactory.getDriver();
        driver.get(properties.getValue("url"));

    }

    @Test
    public void placeOrderAsGuestUser() {
        test = extent.createTest("Place Order Test");
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        HomePage homePage = new HomePage(driver);

        try{

            homePage.clickDesktops();
            homePage.clickMacCategory();
            homePage.clickIMacProduct();
            productPage.setQuantity("2");
            productPage.clickAddToCart();
            productPage.goToShoppingCartPage();
            cartPage.fillCouponCodeField("3455");
            cartPage.fillShippingTaxesFields("Thailand", "Bangkok", "4345");
            cartPage.fillGiftCertificateField("asd");
            cartPage.clickCheckout(test);
           // test.pass("Place Order as Guest User successful.");
    } catch (Exception e) {
        // Log failure in Extent Report
            test.fail("Test failed: " + e.getMessage());
            throw e; // Re-throw the exception to mark the test as failed in TestNG
        }
    }


}
