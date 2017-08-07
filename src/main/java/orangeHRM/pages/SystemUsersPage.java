package orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Vinod on 25/07/2017.
 */
public class SystemUsersPage extends BasePage {
    @FindBy(xpath = "//table[@class='highlight bordered']/tbody/tr[2]/td[8]")
    WebElement editBtn;
    @FindBy(xpath = "//table[@class='highlight bordered']/tbody/tr")
    List<WebElement> users;
    @FindBy(xpath = "//div[@id='status_inputfileddiv']/div/input")
    WebElement statusInput;
    @FindBy(xpath = "//div[@id='status_inputfileddiv']/div/ul/li/span")
    List<WebElement> status;
    @FindBy(id = "systemUserSaveBtn")
    WebElement save;

    public void editUser() {
        editBtn.click();
    }

    public void editUser(String name) {
        for (int i = 1; i < users.size(); i++) {
            String xpath = "//table[@class='highlight bordered']/tbody/tr[" + i + "]/td[2]";
            String userName = driver.findElement(By.xpath(xpath)).getText();
            if (userName.equals(name)) {
                String xpath1 = "//table[@class='highlight bordered']/tbody/tr[" + i + "]/td[8]";
                driver.findElement(By.xpath(xpath1)).click();
                sleep();
            }
        }
    }

    public String getUserRole(String name) {
        sleep();
        String role = "";
        System.out.println("getUserRole users size: " + users.size());
        for (int i = 1; i < users.size(); i++) {
            String xpath = "//table[@class='highlight bordered']/tbody/tr[" + i + "]/td[2]";
            String userName = driver.findElement(By.xpath(xpath)).getText();
            if (userName.equals(name)) {
                String xpath1 = "//table[@class='highlight bordered']/tbody/tr[" + i + "]/td[3]";
                role = driver.findElement(By.xpath(xpath1)).getText();
                break;
            }
        }

        return role;
    }

    public boolean doesUserExists(String name) {
        for (WebElement user : users) {
            String employeeName = user.findElement(By.xpath("//td[2]")).getText();
            if (employeeName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void changeStatus(String s) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("status_inputfileddiv")));
        statusInput.click();
        if (s.equalsIgnoreCase("Enabled")) {
            System.out.println("ENa");
            status.get(1).click();
        } else {
            System.out.println("dis");
            status.get(2).click();
        }
        save();
    }

    public void save() {
        save.click();
    }
}