package orangeHRM.stepdefs.login;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import orangeHRM.pages.LoginPage;
import orangeHRM.utils.AutomationConstants;
import orangeHRM.utils.BrowserFactory;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class LoginStep {

    private LoginPage loginPage;
    private String userName;
    private String password;

    @Before
    public void setUp(){
        BrowserFactory.startBrowser();
    }

    @After
    public void cleanUp(){
        BrowserFactory.stopBrowser();
        System.out.println("closing the browser");
    }

    @Given("^I am on the Login page$")
    public void openLoginPage()  {
        loginPage = new LoginPage();
        loginPage.open();
        assertEquals(AutomationConstants.URL, loginPage.getUrl());
    }

    @When("^I enter userName as \"([^\"]*)\", password as \"([^\"]*)\"$")
    public void enterInvalidOrBlankDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @When("^I login with below credentials")
    public void enterValidDetails(DataTable table) {
        this.userName = table.raw().get(0).get(0);
        this.password = table.raw().get(0).get(1);;
    }

    @And("^I select login")
    public void login()  {
        loginPage.Login(userName, password);
    }

    @Then("^I should see required \"([^\"]*)\"$")
    public void verifyRequiredMsg(String validationMessage)  {
        assertEquals(loginPage.getErrorMsg(), validationMessage);
    }


    @Then("^I should see invalid \"([^\"]*)\"$")
    public void verifyInvalidMsg(String validationMessage)  {
       // String msg = baseStep.getDriver().findElement(By.xpath("//*[@class='toast-message']")).getText();
        //assertEquals(msg, validationMessage);
    }

    @Then("^I should be redirected to the retryLogin page$")
    public void verifyRetryLoginPageDisplayed()  {
        assertTrue(loginPage.getUrl().endsWith("retryLogin"));
    }

    @Then("^I should be on the dashboard page$")
    public void verifyDashBoardDisplayed()  {
        assertTrue(loginPage.getUrl().endsWith("dashboard/index"));
    }
}