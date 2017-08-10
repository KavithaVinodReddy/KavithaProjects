package orangeHRM.pages;


import orangeHRM.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoardPage extends BasePage {


    @FindBy(id = "left-menu")
    WebElement leftMenu;
    @FindBy(css = ".level1#menu_admin_viewAdminModule")
    WebElement adminTab;
    @FindBy(xpath = "//li[@id='menu_admin_UserManagement']")
    WebElement usersManagementMenu;
    @FindBy(css = ".level1#menu_pim_viewPimModule")
    WebElement pimTab;
    @FindBy(xpath = "//li[@id='menu_admin_Organization']")
    WebElement organizationTab;
    @FindBy(xpath = "//a[@id='menu_admin_viewSystemUsers']")
    WebElement usersLink;
    @FindBy(xpath = "//a[@id='menu_admin_viewUserRoles']")
    WebElement userRoleLink;
    @FindBy(xpath = "//a[@id='menu_admin_viewLocations']")
    WebElement locationsLink;
    @FindBy(css = "#menu_pim_viewEmployeeList")
    WebElement empListLink;
    @FindBy(xpath = "//a[@id='menu_pim_addEmployee']")
    WebElement addEmpLink;
    @FindBy(xpath = "//div[@id='menu-profile']/a/span[2]")
    WebElement userMenu;
    @FindBy(xpath = "//a[@id='user-dropdown']/span[1]")
    WebElement userName;
    @FindBy(linkText = "Logout")
    WebElement logOut;
    Utilities utilities = new Utilities();

    public DashBoardPage() {
        super();
        System.out.println("waiting for the page to load");
        waitForElement(By.id("noncoreIframe"));
        driver.switchTo().frame("noncoreIframe");
        utilities.waitForPageToLoad(leftMenu);
    }

    public void expandPIMMenu() {
        waitForElement(By.id("menu_pim_viewPimModule"));
        if (isPIMExpanded()) return;
        pimTab.click();
        sleep(); // waiting for the page to load
        System.out.println("PIM menu clicked to expand");
    }

    public boolean isPIMExpanded() {
        try {
            if (pimTab.findElement(By.cssSelector("a.collapsible-header.active")) != null) {
                System.out.println("its expanded");
                return true;
            } else {
                System.out.println("its collapsed");
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public void collapsePIMMenu() {
        if (!isPIMExpanded()) return;
        else {
            pimTab.click(); /// this is giving issues a lot
            sleep(); // waiting for the page to load
            System.out.println("PIM menu clicked to collapse");
        }
    }

    //todo-refactor to have separate methods
    public void expandAdminMenu() {
        waitForElement(By.id("menu_admin_viewAdminModule"));
        String className = driver.findElement(By.xpath("//li[@id='menu_admin_viewAdminModule']/a")).getAttribute("class");
        System.out.println("Admin menu: " + className);
        if (!className.contains("active")) {
            sleep(); //i don't like adding this but some reason getting intermittent errors here
            adminTab.click(); /// this is giving issues a lot
            System.out.println("Admin menu clicked");
        }
    }

    public void selectUsersLink() {
        expandUserManagementMenu();
        usersLink.click();
        sleep();//wait till the page loads
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
        utilities.waitForElementClickable(By.id("menu_admin_viewUserRoles"));
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
        waitForElement(By.id("menu_pim_addEmployee"));
        addEmpLink.click();
        sleep();
    }

    public void selectEmployeeList() {
        expandPIMMenu();
        utilities.waitForElement(By.id("menu_pim_viewEmployeeList"));
        empListLink.click();
        //sleep();// wait till the page loads
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