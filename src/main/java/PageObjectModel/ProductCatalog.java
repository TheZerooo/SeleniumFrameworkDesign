package PageObjectModel;

import java.util.List;
import AbstractComponent.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductCatalog extends AbstractComponents{
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver; // upr wale driver ko arggument wala driver assign kr diya h
		PageFactory.initElements(driver, this);	// Here We are using PageFactory
	}
	
  // List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart  = By.cssSelector(".btn.w-10.rounded");
	By toastMessage =By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList(){
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductbyName(String productName) {
		 WebElement prod=products.stream().filter(product->
		 product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		 return prod;
	}
	
	public  void addProductToCart(String productName)throws InterruptedException {
		WebElement prod=getProductbyName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))))
		
	}

}
