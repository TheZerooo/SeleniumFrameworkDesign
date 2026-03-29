package PageObjectModel;

//import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicSubmitOrder {
 public static void main(String[] args) throws InterruptedException{
	 
	 WebDriverManager.edgedriver().setup();
	 WebDriver driver=new EdgeDriver();
	 driver.manage().window().maximize();
	 

	 String pname="ZARA COAT 3";
	 
	      // Send your driver at login page
     LandingPage landingPage=new LandingPage(driver);
     landingPage.goTo();
     ProductCatalog productCatalouge= landingPage.LoginApplication("Rahuli@gmail.com","Rahul@1234");
	 
//	 ProductCatalog productCatalouge= new ProductCatalog(driver); //  ->  on  landing class
	 
	 List<WebElement>products=productCatalouge.getProductList();
	 productCatalouge.addProductToCart(pname);	 
	 CartPage cartPage=productCatalouge.gotoCartPage();

//	 CartPage cartPage=new CartPage(driver); //   -> on ProductCatalog calss
	 Boolean match= cartPage. VerifyProductDisplay(pname);
	 Assert.assertTrue(match);
	 CheckoutPage checkoutPage= cartPage.gotoCheckout();
	 checkoutPage.selectCountry("india");
	 ConfirmPage confirm=checkoutPage.submitOrder();
	 Thread.sleep(5000);
	 String confirmMessage = confirm.getConfirmationMessage();
      Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
      System.out.println("Ye kaam kr raha h");
      driver.close();
 };
 
}
