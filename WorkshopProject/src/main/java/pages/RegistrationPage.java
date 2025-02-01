package pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import tools.DriverFactory;
import tools.WaitUtil;

public class RegistrationPage {
    private WebDriver driver = DriverFactory.getDriver();
    WebElement successMessage;

    public void registrationTest(String firstname, String lastname, String email, String telephone, String password, String confirmPassword, String subscribe, ExtentTest test) {
        try{
            // Navigate to Registration Page
            driver.findElement(By.linkText("My Account")).click();
            driver.findElement(By.linkText("Register")).click();

            // Fill registration form
            driver.findElement(By.id("input-firstname")).sendKeys(firstname);
            driver.findElement(By.id("input-lastname")).sendKeys(lastname);
            driver.findElement(By.id("input-email")).sendKeys(email);
            driver.findElement(By.id("input-telephone")).sendKeys(telephone);
            driver.findElement(By.id("input-password")).sendKeys(password);
            driver.findElement(By.id("input-confirm")).sendKeys(confirmPassword);

            if (subscribe.equalsIgnoreCase("Yes")) {
                driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
            }

            driver.findElement(By.name("agree")).click();
            driver.findElement(By.cssSelector("input.btn-primary[value='Continue']")).click();

            // Validate success message
            //WaitUtil.waitUntilElementIsVisible(successMessage);
            successMessage = driver.findElement(By.xpath("//div[@id='content']//h1"));
            //WebElement successMessage = WaitUtil.waitUntilElementIsVisible(driver, By.xpath("//div[@id='content']//h1"), 10);
            String actualMessage = successMessage.getText();
            String expectedMessage = "Your Account Has Been Created!";

            // Assertion and reporting
            Assert.assertEquals(actualMessage, expectedMessage, "Registration failed: Expected success message not displayed!");
            test.pass("Registration successful for: " + firstname + " " + lastname);

        } catch (AssertionError e) {
            test.fail("Assertion Failed: " + e.getMessage());  // Log failure in Extent Report
            throw e;  // **Re-throw exception so TestNG marks the test as failed**
        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            throw new RuntimeException("Test failed due to exception", e);  // **Fail test if an exception occurs**
        }
    }

}
