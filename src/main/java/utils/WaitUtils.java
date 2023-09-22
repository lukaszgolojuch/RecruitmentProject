package utils;

import browsers.Browser;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public final class WaitUtils {

    private static final int TIMEOUT_IN_SECONDS = 30;
    private static final int POLLING_TIME_IN_SECONDS = 1;

    private static Wait<WebDriver> wait;

    public WaitUtils(Browser browser) {
        wait = new FluentWait<>(browser.getDriver())
                .withTimeout(Duration.ofSeconds(TIMEOUT_IN_SECONDS))
                .pollingEvery(Duration.ofSeconds(POLLING_TIME_IN_SECONDS))
                .ignoring(NoSuchElementException.class);
    }

    public static void waitUntilElementClickable(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitUntilElementVisible(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForInvisibilityOfStringInElement(WebElement webElement, String notExpectedString) {
        wait.until((ExpectedCondition<Boolean>) driver ->
                !(webElement.getText().contains(notExpectedString)));
    }

}
