package orangeHRM.pages;

import orangeHRM.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import orangeHRM.utils.AutomationConstants;

public class LoginPage extends BasePage {

    @FindBy(id = "divLoginForm")
    WebElement loginFormDiv;
    @FindBy(id = "txtUsername")
    public WebElement userName;
    @FindBy(id = "txtPassword")
    public WebElement password;
    @FindBy(name = "Submit")
    public WebElement loginBtn;
    @FindBy(xpath = "//div[@id='social-icons']/a[1]")
    public WebElement linkedInLnk;
    @FindBy(xpath = "//div[@id='social-icons']/a[2]")
    public WebElement faceBookLnk;
    @FindBy(xpath = "//div[@id='social-icons']/a[3]")
    public WebElement twitterLnk;
    @FindBy(xpath = "//div[@id='social-icons']/a[4]")
    public WebElement youTubeLnk;
    @FindBy(xpath = "//div[@id='social-icons']/a[1]/img")
    public WebElement linkedInImg;
    @FindBy(xpath = "//div[@id='social-icons']/a[2]/img")
    public WebElement faceBookImg;
    @FindBy(xpath = "//div[@id='social-icons']/a[3]/img")
    public WebElement twitterImg;
    @FindBy(xpath = "//div[@id='social-icons']/a[4]/img")
    public WebElement youTubeImg;
    @FindBy(id = "spanMessage")
    WebElement errorMsg;

    public LoginPage() {
        super();
        Utilities utils = new Utilities();
        utils.waitForPageToLoad(loginFormDiv);
    }

    public void open() {
        if (!driver.getCurrentUrl().equals(AutomationConstants.URL)) {
            driver.get(AutomationConstants.URL);
        }
    }

    public void Login(String name, String pwd) {
        userName.sendKeys(name);
        password.sendKeys(pwd);
        loginBtn.click();
        sleep();
    }

    public void LoginAsAdmin() {
        Login(AutomationConstants.adminUid, AutomationConstants.adminPwd);
    }

    public String getErrorMsg() {
        return errorMsg.getText();
    }
}