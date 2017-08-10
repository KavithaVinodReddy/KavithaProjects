package orangeHRM.pages;

import cucumber.api.DataTable;
import orangeHRM.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AddEmployeeOverlayPage extends BasePage {

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "middleName")
    WebElement middleName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(xpath = "//div[@id='location_inputfileddiv']/div/input")
    WebElement location;
    @FindBy(xpath = "//div[@id='location_inputfileddiv']/div/ul/li/span")
    List<WebElement> locations;
    @FindBy(id = "addPhotographBtn")
    WebElement addPhotographButton;
    @FindBy(xpath = "//div[@class='schema-form-section col s12 m12 l6']/sf-decorator[4]/div/label")
    WebElement loginDetailsChk;
    @FindBy(id = "username")
    WebElement userName;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;
    @FindBy(xpath = "//input[@class='select-dropdown']")
    WebElement statusInput;
    @FindBy(id = "status")
    WebElement status;
    @FindBy(id = "systemUserSaveBtn")
    WebElement saveButton;
    @FindBy(xpath = "//a[@class='modal-action modal-close waves-effect waves-green btn']")
    WebElement cancelButton;
    @FindBy(id="addEmployeeModal")
    WebElement modalDiv;

    public static String newUser_uid;
    public static String newUser_pwd;

    public AddEmployeeOverlayPage(){
        super();
        Utilities utilities = new Utilities();
        //sleep();
        driver.switchTo().defaultContent();
        utilities.waitForPageToLoad(modalDiv);
        System.out.println("finished waiting for the page to load");
    }

    public void createEmployee(String firstName, String middleName, String lastName, String location) {
        this.firstName.sendKeys(firstName);
        this.middleName.sendKeys(middleName);
        this.lastName.sendKeys(lastName);
        this.locations.get(1).click(); // use the input and select the value
        this.saveButton.click();
    }

    public void fillTheForm(DataTable table) {
        List<List<String>> raw = table.raw();
        waitForElement(By.id("firstName"));
        firstName.sendKeys(raw.get(1).get(0));  ///intermittent issues to local element
        middleName.sendKeys(raw.get(1).get(1));
        lastName.sendKeys(raw.get(1).get(2));
        location.click(); //TODO select the location correctly
        sleep();
        locations.get(2).click(); // use the input and select the value
        loginDetailsChk.click();
        newUser_uid = raw.get(1).get(4) + Math.random();
        newUser_pwd = raw.get(1).get(5);
        userName.sendKeys(newUser_uid);
        password.sendKeys(newUser_pwd);
        confirmPassword.sendKeys(raw.get(1).get(6));
    }

    public void save() {
        saveButton.click();
    }

    public void cancel() {
        this.cancelButton.click();
    }

    public void addPhotograph() {
        this.addPhotographButton.click();
    }
}