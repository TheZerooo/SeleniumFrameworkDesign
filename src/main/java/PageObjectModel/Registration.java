package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponents;

public class Registration extends AbstractComponents {

    WebDriver driver;

    public Registration(WebDriver driver) {
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

    @FindBy(css = "[formcontrolname='occupation']")
    WebElement occupationDropdown;

    @FindBy(css = "input[value='Male'][formcontrolname='gender']")
    WebElement maleRadio;

    @FindBy(css = "input[value='Female'][formcontrolname='gender']")
    WebElement femaleRadio;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(css = "input[formcontrolname='required']")
    WebElement termsCheckbox;

    @FindBy(id = "login")
    WebElement submitBtn;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public void registerUser(String first, String last, String email, String mobile,
                             String occupation, String gender, String password, String confirmPwd) {
        firstName.sendKeys(first);
        lastName.sendKeys(last);
        userEmail.sendKeys(email);
        userMobile.sendKeys(mobile);
        selectOccupation(occupation);
        selectGender(gender);
        userPassword.sendKeys(password);
        confirmPassword.sendKeys(confirmPwd);
        termsCheckbox.click();
        submitBtn.click();}

    private void selectOccupation(String occupationValue) {
        occupationDropdown.click();
        driver.findElement(By.cssSelector("option[value*='" + occupationValue + "']")).click();
    }

    private void selectGender(String genderValue) {
        if (genderValue.equalsIgnoreCase("Male")) {
            maleRadio.click();
        } else if (genderValue.equalsIgnoreCase("Female")) {
            femaleRadio.click();
        }
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/#/auth/register");
    }

}
