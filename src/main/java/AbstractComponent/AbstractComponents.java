package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectModel.CartPage;
import PageObjectModel.OrderPage;

public class AbstractComponents{
	
	WebDriver driver;
	
	/* ye class LandingPage(child class) ka parent h tho 
	 
	 * Child class se jb driver parent class mai aate h tho parent class mai driver ko catch krne k liye
	                                    constructor banana padta h 
	                                    
	 *  child class se driver baijne k liye super keyword ka use krte h
	 
	 */
	
	
	public AbstractComponents(WebDriver driver) {
		// Constructor 
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement OrderHeader;
	
	public void waitForElementToAppear(By findBy) {
		 WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
//		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		 WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
		// **** wait 4 - second for disappear
		// WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
// **** wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage gotoCartPage() {
		cartHeader.click();
		 CartPage cartPage=new CartPage(driver);
		 return cartPage;
	}
	
	public OrderPage gotoOrderPage() {
		OrderHeader.click();
		 OrderPage Oh=new OrderPage(driver);
		 return Oh;
	}
}