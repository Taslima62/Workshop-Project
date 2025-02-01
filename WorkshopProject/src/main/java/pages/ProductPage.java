package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.WaitUtil;

import java.time.Duration;

public class ProductPage {
    WebDriver driver;

    @FindBy(id = "input-quantity")
    private WebElement quantityField;

    @FindBy(id = "button-cart")
    private WebElement addToCartButton;


    @FindBy(css = "div.alert-success")
    private WebElement successMessage;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setQuantity(String quantity) {
        quantityField.clear();
        quantityField.sendKeys(quantity);
    }

    /*public void clickAddToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the Add to Cart button to be clickable
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        button.click();
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.alert-success")
        ));

    }*/
    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Scroll to ensure button is visible
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);

            // Wait for button to be clickable
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

            // Click using JavaScript as backup
            try {
                button.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            }

            // Wait for success message to confirm addition
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success")));

        } catch (Exception e) {
            System.out.println("Failed to click 'Add to Cart'. Retrying...");

            // Retry clicking the button
            try {
                Thread.sleep(10); // Small delay before retry
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success")));
            } catch (Exception retryException) {
                throw new RuntimeException("Add to Cart failed after retry.", retryException);
            }
        }
    }

    public void goToShoppingCartPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.alert-success")
        ));
        //WaitUtil.waitUntilElementIsVisible(successMessage);
        // Click the "Shopping Cart" link in the success message
        WebElement shoppingCartLink = successMessage.findElement(By.xpath(".//a[contains(text(), 'shopping cart')]"));
        shoppingCartLink.click();

        // Wait for the cart page to load
        wait.until(ExpectedConditions.urlContains("route=checkout/cart"));

        // Verify that the cart page is loaded
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("route=checkout/cart")) {
            System.out.println("Successfully navigated to the shopping cart page.");
        } else {
            System.out.println("Failed to navigate to the shopping cart page.");
        }
    }

}
