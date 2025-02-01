package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ModulePage {
    private WebDriver driver;
    public ModulePage(WebDriver driver) {
        this.driver = driver;
    }
    public  void waitForLoading(){
        WebElement loading = driver.findElement(By.xpath("//p[(text()='Admin')]"));
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(loading));
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(loading));
    }
}
