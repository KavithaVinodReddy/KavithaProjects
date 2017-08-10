package orangeHRM.pages;

import orangeHRM.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class EmployeeListPage extends BasePage {

    @FindBy(css=".pim-container")
    WebElement contentElement;
    @FindBy(id = "addEmployeeButton")  //find the plus button id or
            WebElement plusButton;
    @FindBy(xpath = "//table[@id='employeeListTable']/tbody/tr")
    List<WebElement> employees;
    @FindBy(css = ".dropdown-button.dropdown")
    WebElement removeOption;


    public EmployeeListPage(){
        super();
        Utilities utilities = new Utilities();
        driver.switchTo().defaultContent();
        utilities.waitForPageToLoad(contentElement);
        System.out.println("EMp list page: waited for the page to laod");
    }

    public void clickPlusBtn() {
        plusButton.click();
    }

    public void deleteAnEmployee(String name) {
        for (int i = 1; i < employees.size(); i++) {
            String xpath = "//table[@id='employeeListTable']/tbody/tr[" + i + "]/td[4]";
            String employeeName = driver.findElement(By.xpath(xpath)).getText();
            if (employeeName.equals(name)) {
                xpath = "//table[@id='employeeListTable']/tbody/tr[" + i + "]/td[1]";
                driver.findElement(By.xpath(xpath)).click();
                waitForElement(By.cssSelector(".dropdown-button.dropdown"));
                removeOption.click();
                By linkBy= By.linkText("Delete Selected");
                waitForElement(linkBy);
                driver.findElement(linkBy).click();
                sleep();
                driver.findElement(By.cssSelector(".btn.primary-btn")).click();
                sleep();
                break;

            }
        }
    }

    public boolean doesEmployeeExists(String name) {
        //'ohrm-pagination').getAttribute('count')
        waitForElement(By.tagName("ohrm-pagination"));
        sleep();
        System.out.println(employees.size());
        for (int i = 1; i < employees.size(); i++) {
            String xpath = "//table[@id='employeeListTable']/tbody/tr[" + i + "]/td[4]";
            String employeeName = driver.findElement(By.xpath(xpath)).getText();
            System.out.println(employeeName);
            if (employeeName.equals(name)) {
                System.out.println("found");
                return true;
            }
        }
        return false;
    }
}
