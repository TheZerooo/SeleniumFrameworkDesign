package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath="//button[contains(@class,'ta-item')][.//span[text()=' India']]")
	WebElement selectCountry;

	@FindBy(css=".action__submit ")
	WebElement submit;

	By result=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		  Actions a= new Actions(driver);
//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		  a.sendKeys(country,countryName).build().perform();
		  waitForElementToAppear(result);
	       
		  selectCountry.click();
	}
	
	public ConfirmPage submitOrder(){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("arguments[0].click();",submit);
		
		return new ConfirmPage(driver);
	}

}
