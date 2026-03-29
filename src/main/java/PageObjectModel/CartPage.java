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

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		// Constructor 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitle;

	public Boolean VerifyProductDisplay(String Pname) throws InterruptedException {
//		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Thread.sleep(5000);
	    Boolean match=productTitle.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(Pname));
	    System.out.println("mai run kr gayi hu checkout point pr hu");
		return match;
	}
	
	public CheckoutPage gotoCheckout() {
		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("arguments[0].click();", checkOutEle);
		   return new CheckoutPage(driver);
//		 checkOutEle.click();
	}
	
	
}