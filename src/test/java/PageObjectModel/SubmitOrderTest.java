package PageObjectModel;

import java.io.IOException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest; // Yaha se user directly login page pr aa ja raha h
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	
	public int i=1;
	
	//(dataProvider="getData", groups= {"Purchase"})

	@Test(dataProvider="getData", groups= {"Purchase"} )
 public void submitOrder(String email,String password ,String productName) throws IOException, InterruptedException{
	
//	 String pname="ZARA COAT 3";
	 
//	 LandingPage landingPage=launchApplication();
     ProductCatalog productCatalouge= landingPage.LoginApplication(email,password);
	 
//	 ProductCatalog productCatalouge= new ProductCatalog(driver); //  ->  on  landing class
	 
	 List<WebElement>products=productCatalouge.getProductList();
	 productCatalouge.addProductToCart(productName);	 
	 CartPage cartPage=productCatalouge.gotoCartPage();

//	 CartPage cartPage=new CartPage(driver); //   -> on ProductCatalog calss
	 Boolean match= cartPage. VerifyProductDisplay(productName);
	 Assert.assertTrue(match);
	 CheckoutPage checkoutPage= cartPage.gotoCheckout();
	 checkoutPage.selectCountry("india");
	 ConfirmPage confirm=checkoutPage.submitOrder();
	 Thread.sleep(5000);
	 String confirmMessage = confirm.getConfirmationMessage();
      Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
      System.out.println("Ye kaam kr raha h");
     
 }

	@Test(dataProvider="getData",dependsOnMethods= {"submitOrder"})
	public void OrderHistory(String email,String password ,String productName) throws InterruptedException {
		 ProductCatalog productCatalouge= landingPage.LoginApplication(email,password);
		 OrderPage orderPage=productCatalouge.gotoOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		 }
	
	@DataProvider(name="getData")
	public Object[][] getdata(){
		System.out.println("number of user "+i++);
		
		// 2 dimentional array accept multiple sets
		return new Object[][] {{"Already@gmail.com","Already@123","IPHONE 13 PRO"},{"Rahuli@gmail.com","Rahul@1234","ZARA COAT 3"}};
	
	}
 };
 

