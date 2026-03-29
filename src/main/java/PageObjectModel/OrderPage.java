package PageObjectModel;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponent.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		// Constructor 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productTitle;


	
	public boolean VerifyOrderDisplay(String pname){
		Boolean match=productTitle.stream().anyMatch(product->product.getText().equalsIgnoreCase(pname));
		return match;
		 
	}
	
	
}