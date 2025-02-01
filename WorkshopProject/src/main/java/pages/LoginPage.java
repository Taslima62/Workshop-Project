package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.DriverFactory;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver = DriverFactory.getDriver();

    /*public LoginPage(WebDriver driver){
        this.driver = driver;
    }*/

    public void waitForBodyContent() throws InterruptedException {
       // logger.info("Wait for body content");
       //WebElement bodyContent = driver.findElement(By.id("email"));
      // new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions.visibilityOf(bodyContent));
        //WaitUtil.waitUntilElementIsVisible(bodyContent);
       Thread.sleep(2000);
    }
    public void login(String username, String password) throws InterruptedException {
        //Thread.sleep(1000);
        // Navigate to Registration Page
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Login")).click();
        WebElement emailField = driver.findElement(By.id("input-email"));
        WebElement passwordField = driver.findElement(By.id("input-password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Login']"));

        emailField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(loginButton));
    }
}
