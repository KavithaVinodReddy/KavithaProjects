package orangeHRM.pages;

import orangeHRM.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DashBoardPage extends BasePage {
    @FindBy(xpath = "//li[@id='menu_admin_viewAdminModule']/a/span[3]")
    WebElement adminTab;
    @FindBy(xpath = "//li[@id='menu_admin_UserManagement']")
    WebElement usersManagementMenu;
    @FindBy(xpath = "//li[@id='menu_pim_viewPimModule']")
    WebElement pimTab;
    @FindBy(xpath = "//li[@id='menu_admin_Organization']")
    WebElement organizationTab;
    @FindBy(xpath = "//a[@id='menu_admin_viewSystemUsers']")
    WebElement usersLink;
    @FindBy(xpath = "//a[@id='menu_admin_viewUserRoles']")
    WebElement userRoleLink;
    @FindBy(xpath = "//a[@id='menu_admin_viewLocations']")
    WebElement locationsLink;
    @FindBy(xpath = "//a[@id='menu_pim_viewEmployeeList']")
    WebElement empListLink;
    @FindBy(xpath = "//a[@id='menu_pim_addEmployee']")
    WebElement addEmpLink;
    @FindBy(xpath = "//div[@id='menu-profile']/a/span[2]")
    WebElement userMenu;
    @FindBy(xpath = "//a[@id='user-dropdown']/span[1]")
    WebElement userName;
    @FindBy(linkText = "Logout")
    WebElement logOut;

    public void expandPIMMenu() {
        try {
            driver.switchTo().frame("noncoreIframe");
        } catch (Exception ex) {

        }
        String className = driver.findElement(By.xpath("//li[@id='menu_pim_viewPimModule']/a")).getAttribute("class");
        System.out.println("PIM menu: " + className);
        if (!className.contains("active")) {
            pimTab.click(); /// this is giving issues a lot
            sleep();
            System.out.println("PIM menu clicked");
        }
        //if (isExpanded(pimTab))

    }

    public void expandAdminMenu() {
        waitForElement(By.id("menu_admin_viewAdminModule"));
        String className = driver.findElement(By.xpath("//li[@id='menu_admin_viewAdminModule']/a")).getAttribute("class");
        System.out.println("Admin menu: " + className);
        if (!className.contains("active")) {
            //if (!isExpanded(usersManagementLink))
            sleep(); //i don't like adding this but somereason getting intermittend errors here
            adminTab.click(); /// this is giving issues a lot
            System.out.println("Admin menu clicked");
        }
    }

    public void selectUsersLink() {
        expandUserManagementMenu();
        usersLink.click();
    }

    public boolean isExpanded(WebElement menu) {
        String className = menu.findElement(By.xpath("//a")).getAttribute("class");
        System.out.println(className);
        if (className.contains("active")) return true;
        else return false;
    }

    public void expandUserManagementMenu() {
        String className = driver.findElement(By.xpath("//li[@id='menu_admin_UserManagement']/a")).getAttribute("class");
        System.out.println("usersManagementMenu: " + className);
        if (!className.contains("active")) {
            usersManagementMenu.click();
            System.out.println("usersManagementMenu clicked");
        }
    }

    public void selectUsersRoleLink() {
        expandUserManagementMenu();
        userRoleLink.click();
        sleep(); //todo- add wait for some element instead of sleep
    }

    public void expandOrganizationMenu() {
        organizationTab.click();
    }

    public void selectLocationsLink() {
        locationsLink.click();
    }

    public void selectAddEmployee() {
        addEmpLink.click();
        sleep();
    }

    public void selectEmployeeList() {
        expandPIMMenu();
        new WebDriverWait(driver, 30).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='menu_pim_viewEmployeeList']")));
        empListLink.click();
    }

    public void logOut() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("user-dropdown")));
        // TODO- tried all other wait but only thread sleep is working-fix it
        sleep();
        userMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_menu")));
        logOut.click();
    }

    public String getLoggedInUserName() {
        return userName.getText();
    }
}