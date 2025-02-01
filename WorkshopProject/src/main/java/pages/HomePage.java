package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.WaitUtil;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    @FindBy(linkText = "My Account")
    private WebElement myAccount;
    @FindBy(linkText = "Logout")
    private WebElement logoutBtn;
    @FindBy(linkText = "Desktops")
    private WebElement desktops;

    @FindBy(linkText = "Mac (1)")
    private WebElement macCategory;

    @FindBy(linkText = "iMac")
    private WebElement iMacProduct;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize PageFactory
    }

    public void clickDesktops() {
        desktops.click();
    }
    public void clickMacCategory() {
        macCategory.click();
    }

    public void clickIMacProduct() {
        iMacProduct.click();
    }

    public void logout(){
        WebElement myAccountButton = driver.findElement(By.xpath("//a[@href='https://tutorialsninja.com/demo/index.php?route=account/account']"));
        myAccountButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));

        logoutButton.click();
    }

}
