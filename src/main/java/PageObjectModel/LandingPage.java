package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponents;

public class LandingPage extends AbstractComponents{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);  //
		this.driver=driver; // upr wale driver ko arggument wala driver assign kr diya h
		PageFactory.initElements(driver, this);	// Here We are using PageFactory
	}
	
// WebElement userEmail=driver.findElement(By.id("userEmail"));// ye ita jada ikhna pd raha h iss liye PF

	@FindBy(id="userEmail")
	WebElement userEmail;


    @FindBy(id="userPassword")
	WebElement Passwords;

    @FindBy(id="login")
	WebElement submit;
    
    @FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;





    public ProductCatalog LoginApplication(String email,String Password) {
    	userEmail.sendKeys(email);
    	Passwords.sendKeys(Password);
    	submit.click();
   	 ProductCatalog productCatalouge= new ProductCatalog(driver);
   	 return productCatalouge;
    }
    
    public String getErrorMessage() {
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
    }
    
    public void goTo() {
    	driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

}