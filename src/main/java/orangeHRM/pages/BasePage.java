package orangeHRM.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import orangeHRM.utils.BrowserFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage extends BrowserFactory {

    public static WebDriver driver = BrowserFactory.getDriver();
    public WebDriverWait wait = new WebDriverWait(driver, 30);

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    private ArrayList<String> getWindowHandles() {
        return new ArrayList<String>(driver.getWindowHandles());
    }

    public void switchToNewTab() {
        List<String> browserTabs = getWindowHandles();
        driver.switchTo().window(browserTabs.get(browserTabs.size() - 1));
    }

    public void switchToDefaultTab() {
        List<String> browserTabs = getWindowHandles();
        driver.switchTo().window(browserTabs.get(0));
    }

    public void waitForElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public static void hoverOver(WebElement hover) {
        Actions actions = new Actions(driver);
        actions.moveToElement(hover).perform();
    }

    public static void moveTo(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public static void HoverAndClick(WebElement elementToHover, WebElement elementToClick) {
        Actions action = new Actions(driver);
        action.moveToElement(elementToHover).click(elementToClick).build().perform();
    }

    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
