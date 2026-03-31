package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import AbstractComponent.AbstractComponents;

public class RegistrationPage extends AbstractComponents {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userMobile")
    WebElement userMobile;

    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationSelect;

    @FindBy(css = "input[value='Male'][formcontrolname='gender']")
    WebElement genderMale;

    @FindBy(css = "input[value='Female'][formcontrolname='gender']")
    WebElement genderFemale;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(css = "input[formcontrolname='required']")
    WebElement ageCheckbox;

    @FindBy(id = "login")
    WebElement registerButton;

    @FindBy(css = "a[href*='login']")
    WebElement loginLink;

    public void enterFirstName(String fname) {
        firstName.sendKeys(fname);
    }

    public void enterLastName(String lname) {
        lastName.sendKeys(lname);
    }

    public void enterEmail(String email) {
        userEmail.sendKeys(email);
    }

    public void enterPhoneNumber(String phone) {
        userMobile.sendKeys(phone);
    }

    public void selectOccupation(String occupation) {
        Select select = new Select(occupationSelect);
        select.selectByVisibleText(occupation);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            genderMale.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            genderFemale.click();
        }
    }

    public void enterPassword(String password) {
        userPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPass) {
        confirmPassword.sendKeys(confirmPass);
    }

    public void checkAgeConfirmation() {
        ageCheckbox.click();
    }

    public void clickRegister() {
        registerButton.click();
    }

    public LandingPage goToLogin() {
        loginLink.click();
        return new LandingPage(driver);
    }

    public void registerUser(String fname, String lname, String email, String phone, String occupation, String gender, String password, String confirmPass) {
        enterFirstName(fname);
        enterLastName(lname);
        enterEmail(email);
        enterPhoneNumber(phone);
        selectOccupation(occupation);
        selectGender(gender);
        enterPassword(password);
        enterConfirmPassword(confirmPass);
        checkAgeConfirmation();
        clickRegister();
    }
}
