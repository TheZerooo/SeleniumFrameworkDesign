package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectModel.CartPage;
import PageObjectModel.OrderPage;

public class AbstractComponents{WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement OrderHeader;

	@FindBy(css=".ng-animating")
	WebElement spinner;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions.invisibilityOf(ele));
		} catch (Exception e) {
			Thread.sleep(500);
		}
	}

	public CartPage gotoCartPage() throws InterruptedException {
		// Wait for spinner to disappear
		try {
			waitForElementToDisappear(spinner);
		} catch (Exception e) {
			// Continue even if spinner wait fails
		}

		// Scroll cart button into view
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", cartHeader);
		Thread.sleep(500);

		// Wait for element to be clickable
		waitForElementToBeClickable(cartHeader);

		// Click with fallback to JavaScript click
		try {
			cartHeader.click();
		} catch (Exception e) {
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", cartHeader);
		}

		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}

	public OrderPage gotoOrderPage() throws InterruptedException {
		// Wait for spinner to disappear
		try {
			waitForElementToDisappear(spinner);
		} catch (Exception e) {
			// Continue even if spinner wait fails
		}

		// Scroll order button into view
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", OrderHeader);
		Thread.sleep(500);

		// Wait for element to be clickable
		waitForElementToBeClickable(OrderHeader);

		// Click with fallback to JavaScript click
		try {
			OrderHeader.click();
		} catch (Exception e) {
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", OrderHeader);
		}

		OrderPage Oh=new OrderPage(driver);
		return Oh;
	}

	public void waitForElementToBeClickable(WebElement ele) {
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
}