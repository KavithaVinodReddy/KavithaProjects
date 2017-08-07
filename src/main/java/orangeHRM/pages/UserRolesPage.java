package orangeHRM.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserRolesPage extends BasePage {
    @FindBy(css = ".btn-floating.btn-large")
    WebElement addUserRoleBtn;
    @FindBy(xpath = "//table[@class='highlight bordered']/tbody/tr")
    List<WebElement> userRoles;
    @FindBy(css = ".dropdown-button.dropdown")
    WebElement removeOption;

    public void addUserRole() {
        addUserRoleBtn.click();
        sleep(); //opens the new page todo- use wait methods instead of sleep
    }

    public boolean isRoleAdded(String name) {
        for (WebElement element : userRoles) {
            String roleName = element.findElement(By.xpath("//td[2]/ng-include/span")).getText();
            if (name.equals(name)) return true;
        }
        return false;
    }

    public void deleteRole(String role) {
        for (WebElement element : userRoles) {
            String roleName = element.findElement(By.xpath("//td[2]/ng-include/span")).getText();
            if (roleName.equals(role)) {
                element.findElement(By.xpath("//td[1]")).click();
                removeOption.click();
                driver.findElement(By.linkText("Delete Selected")).click();
                sleep();
                driver.findElement(By.cssSelector(".btn.primary-btn")).click();
                sleep();
                break;
            }
        }
    }

}
