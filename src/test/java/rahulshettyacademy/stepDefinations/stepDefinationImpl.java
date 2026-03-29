package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import PageObjectModel.CartPage;
import PageObjectModel.CheckoutPage;
import PageObjectModel.ConfirmPage;
import PageObjectModel.LandingPage;
import PageObjectModel.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;

public class stepDefinationImpl extends BaseTest{
  public LandingPage landingPage;
  public ProductCatalog productCatalog;
  public ConfirmPage confirmationPage;
  @Given("I landed on Ecommerce Page")
  public void I_landed_on_Ecommerce_Page() throws IOException{
	  landingPage =launchApplication();
  }
  
  @Given("^Logged in with username (.+) and password (.+)$")
  public void logged_in_username_and_password(String username, String password) {
	  productCatalog = landingPage.LoginApplication(username,password);
  }
  
  @When("^I add product (.+) to Cart$")
  public void i_add_product_to_cart(String productName) throws InterruptedException {
	  List<WebElement> products= productCatalog.getProductList();
	  productCatalog.addProductToCart(productName);
  }
  
  @When("^Checkout (.+) and submit the order$")
  public void checkout_submit_order(String productName) throws InterruptedException {
	  CartPage cartPage = productCatalog.gotoCartPage();
	  
	  Boolean match = cartPage.VerifyProductDisplay(productName);
	  Assert.assertTrue(match);
	  CheckoutPage checkoutPage = cartPage.gotoCheckout();
	  checkoutPage.selectCountry("india");
	   confirmationPage = checkoutPage.submitOrder();
  }
  
  @Then("{string} message is displayed on ConfirmationPage")
  public void message_displayed_confirmationPage(String string) {
	  String confirmMessage = confirmationPage.getConfirmationMessage();
	  Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	  driver.close();
  }
  
  //  *************  tidy Gerkin -> feature file paste aro step aa jaye ga(java step)
  
  @Then("^\"([^\"]*)\" message is displayed")
  public void something_message_is_displayed(String strArg1) {
	  Assert.assertEquals(strArg1, landingPage.getErrorMessage());
	  driver.close();
  }
}
