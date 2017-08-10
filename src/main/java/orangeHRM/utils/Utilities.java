package orangeHRM.utils;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Utilities extends BrowserFactory {

    public WebDriverWait wait = new WebDriverWait(driver, 60);

    public void waitForPageToLoad(final WebElement element) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(60, SECONDS)
                .pollingEvery(10, SECONDS)
                .ignoring(NoSuchElementException.class);

        Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver arg0) {
                if (element.isDisplayed()) {
                    return true;
                }
                return false;
            }
        };
        wait.until(function);
    }

    public void waitForElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementClickable(By by){
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

}
