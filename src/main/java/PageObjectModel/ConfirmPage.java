package PageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponents;

public class ConfirmPage extends AbstractComponents {

	WebDriver driver;
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}

}
