package PageObjectModel;

import java.io.IOException;
import rahulshettyacademy.TestComponents.Retry;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
	
  @Test(groups={"ErrorHandeling"},retryAnalyzer=Retry.class)
	public void submitOrder() throws IOException, InterruptedException {
		
//		 System.out.println("yes i check incorrect login1");
		landingPage.LoginApplication("Rahulii@gmail.com","Rahul@12340");
//		 System.out.println("yes i check incorrect login2");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
//		 System.out.println("yes i check incorrect login3");
		
	}
  @Test
  public void ProductErrorValidation() throws IOException, InterruptedException{
		
		 String pname="ZARA COAT 3";
	     ProductCatalog productCatalouge= landingPage.LoginApplication("Rahuli@gmail.com","Rahul@1234");
		 List<WebElement>products=productCatalouge.getProductList();
		 productCatalouge.addProductToCart(pname);	 
		 CartPage cartPage=productCatalouge.gotoCartPage();
		 Boolean match= cartPage. VerifyProductDisplay("ZARA COAT 33");
		 System.out.println("Match is"+match);
		 Assert.assertFalse(match);
}
};