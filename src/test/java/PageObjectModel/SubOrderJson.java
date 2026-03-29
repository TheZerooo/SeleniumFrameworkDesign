package PageObjectModel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
//import java.io.File;
import java.time.Duration;
import java.util.HashMap;
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

public class SubOrderJson extends BaseTest {
	
	
	public int i=1;

	@Test(dataProvider="getData", groups= {"Purchase"})
 public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
	
//	 String pname="ZARA COAT 3";
	 
//	 LandingPage landingPage=launchApplication();
     ProductCatalog productCatalouge= landingPage.LoginApplication(input.get("email"),input.get("password"));
	 
//	 ProductCatalog productCatalouge= new ProductCatalog(driver); //  ->  on  landing class
	 
	 List<WebElement>products=productCatalouge.getProductList();
	 productCatalouge.addProductToCart(input.get("product"));	 
	 CartPage cartPage=productCatalouge.gotoCartPage();

//	 CartPage cartPage=new CartPage(driver); //   -> on ProductCatalog calss
	 Boolean match= cartPage. VerifyProductDisplay(input.get("product"));
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
	public void OrderHistory(HashMap<String,String> input) throws InterruptedException {
		 ProductCatalog productCatalouge= landingPage.LoginApplication(input.get("email"),input.get("password"));
		 OrderPage orderPage=productCatalouge.gotoOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(input.get("product")));
		 }
	
	@DataProvider(name="getData")
	public Object[][] getdata() throws IOException{
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(0)}};
	}
 };
 

