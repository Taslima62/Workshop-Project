package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tools.WaitUtil;

import java.time.Duration;

public class CartPage {
    WebDriver driver;


    @FindBy(linkText = "Use Coupon Code")
    private WebElement couponArea;
    @FindBy(id = "input-coupon")
    private WebElement couponField;

    @FindBy(linkText = "Estimate Shipping & Taxes")
    private WebElement shippingAndTaxesArea;

    @FindBy(linkText = "Use Gift Certificate")
    private WebElement giftCertArea;

    @FindBy(name = "gift_cert")
    private WebElement giftCertField;



    @FindBy(linkText = "Checkout")
    private WebElement checkoutButton;

    @FindBy(linkText = "Country")
    private WebElement countryField;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillCouponCodeField(String coupon) {
        couponArea.click();

        WaitUtil.waitUntilElementIsVisible(couponField);
        couponField.sendKeys(coupon);

    }
    public void fillShippingTaxesFields(String country, String region, String code){

        scrollToElement(shippingAndTaxesArea);
        shippingAndTaxesArea.click();
        //WaitUtil.waitUntilElementIsVisible(countryField);
        // Select Country
        WebElement countryDropdown = driver.findElement(By.name("country_id"));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText(country); //
       // countrySelect.selectByValue(country);

        // Select the region/state
        WebElement regionDropdown = driver.findElement(By.name("zone_id"));
        regionDropdown.click();
        Select regionSelect = new Select(regionDropdown);
        regionSelect.selectByVisibleText(region); // Example: Select Chaiyaphum

        // Enter the postcode
        WebElement postcodeField = driver.findElement(By.name("postcode"));
        postcodeField.clear();
        postcodeField.sendKeys(code);
    }
    public void fillGiftCertificateField(String cert){
        scrollToElement(giftCertArea);
        giftCertArea.click();
        WaitUtil.waitUntilElementIsVisible(giftCertField);
        giftCertField.sendKeys(cert);
    }


    public void clickCheckout(ExtentTest test) {
        try {
            checkoutButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.alert-danger")
            ));
            Assert.assertEquals(alertMessage.getText(),"Products marked with *** are not available in the desired quantity or not in stock!\n" +
                    "×");
            test.pass("Place Order as Guest User successful.");
        } catch (AssertionError e) {
            test.fail("Assertion Failed: " + e.getMessage());  // Log failure in Extent Report
            throw e;  // **Re-throw exception so TestNG marks the test as failed**
            } /*catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            throw new RuntimeException("Test failed due to exception", e);  // **Fail test if an exception occurs**
        }*/
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
