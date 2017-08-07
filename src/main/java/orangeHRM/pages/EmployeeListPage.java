package orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class EmployeeListPage extends BasePage {

    @FindBy(id = "addEmployeeButton")  //find the plus button id or
            WebElement plusButton;
    @FindBy(xpath = "//table[@id='employeeListTable']/tbody/tr")
    List<WebElement> employees;
    @FindBy(css = ".dropdown-button.dropdown")
    WebElement removeOption;

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
                removeOption.click();
                driver.findElement(By.linkText("Delete Selected")).click();
                sleep();
                driver.findElement(By.cssSelector(".btn.primary-btn")).click();
                sleep();
                break;

            }
        }
    }

    public boolean doesEmployeeExists(String name) {
        for (int i = 1; i < employees.size(); i++) {
            String xpath = "//table[@id='employeeListTable']/tbody/tr[" + i + "]/td[4]";
            String employeeName = driver.findElement(By.xpath(xpath)).getText();
            if (employeeName.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
