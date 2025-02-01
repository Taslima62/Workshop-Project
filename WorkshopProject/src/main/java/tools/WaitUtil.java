package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static tools.DriverFactory.getDriver;

public class WaitUtil {
    private static final int DEFAULT_WAIT = 40;

    public static void waitFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static void waitFor(long time, TimeUnit timeUnit) {
        long millis = TimeUnit.MILLISECONDS.convert(time, timeUnit);
        waitFor(millis);
    }

    public static void waitForSeconds(long seconds) {
        waitFor(seconds, TimeUnit.SECONDS);
    }

    public static WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_WAIT));
    }



    public static WebElement waitUntilElementIsVisible(WebElement element) {
        return WaitUtil.getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitUntilElementIsVisible(By elementLocator) {
        return WaitUtil.getWait().until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    public static boolean waitUntilElementIsInvisible(WebElement element) {
        return WaitUtil.getWait().until(ExpectedConditions.invisibilityOf(element));
    }

    public static boolean waitUntilElementIsInvisible(By elementLocator) {
        return WaitUtil.getWait().until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }

    public static WebElement waitUntilElementIsClickable(WebElement element) {
        return WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitUntilElementIsClickable(By elementLocator) {
        return WaitUtil.getWait().until(ExpectedConditions.elementToBeClickable(elementLocator));
    }
}

