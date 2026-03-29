package rahulshettyacademy;

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
//import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class StandAloneTest {
 public static void main(String[] args) throws InterruptedException{
	 WebDriverManager.edgedriver().setup();
	 WebDriver driver=new EdgeDriver();
	 driver.manage().window().maximize();
	 driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	 driver.findElement(By.id("userEmail")).sendKeys("Rahuli@gmail.com");
	 driver.findElement(By.id("userPassword")).sendKeys("Rahul@1234");
	 driver.findElement(By.id("login")).click();
	 
	 WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(20));
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	 
//	 Thread.sleep(3000); // Time Lagta h Bhai
	 List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));

//	 List<WebElement> products=driver.findElements(By.className("container"));
//	 System.out.println(products);
	 
	 
	 /*
	 WebElement[] Parray= products.toArray(WebElement[]::new);
	 for(int i=0;i<products.size();i++) {
		 WebElement p=Parray[i];
		 System.out.println("Product name is"+p);
		 
	 }
	 */
	 
	 String name="ZARA COAT 3";
	 
//	 System.out.println("I am running 1");
	 WebElement prod=products.stream().filter(product->
	 product.findElement(By.cssSelector("b")).getText().equals(name)).findFirst().orElse(null);
	 
//	 System.out.println(prod);
//	 System.out.println("I am running 2");
	 prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
//	 System.out.println("I am running 3");
	 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	 wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	 

	 
	 //  * For simple attribute ->>>   //Tagname[@attribute='value'] 
	 driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
   Thread.sleep(9000);
	 
	 List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	 System.out.println(cartProducts);
	 Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(name));
	System.out.println(match);
	 Assert.assertTrue(match);
	 
	 System.out.println("Assert got matched");

	 WebElement clickbutton=driver.findElement(By.cssSelector(".totalRow button"));
	 
//sari galti edge ki h uski scroll krne ka tarika kharabh iss liye center chnage ho jata border or footer 
	 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", clickbutton);
	 clickbutton.click();
	 
	 /*
	  
	  * To perform advance complex actionwe need Actions method of selenium
	  * a ko bola ki tumko send krna h india ko jaha placeholder= Select Country likha hua h
	  * build()-> sbb compile kr do
	  * Compiles all the specified actions into a single, complete sequence of steps.
	  * perform()-> Executes the sequence of steps (typing "india" into the field) in the browser.
	 
	 */
	 
      Actions a= new Actions(driver);
      a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
      
      Thread.sleep(9000);

      System.out.println("In the way of india1");
      WebElement country= driver.findElement(By.xpath("//button/span[text()=' India']\r\n"));
      System.out.println("In the way of india2");
      JavascriptExecutor js = (JavascriptExecutor) driver;
      System.out.println("In the way of india3");
      js.executeScript("arguments[0].click();", country);
      System.out.println("In the way of india4");
      System.out.println("I am working click india");
      
      Thread.sleep(9000);
      WebElement sub= driver.findElement(By.cssSelector(".action__submit "));
//      JavascriptExecutor js = (JavascriptExecutor) driver;
      System.out.println("In the way of submit1");
      js.executeScript("arguments[0].click();", sub);
      System.out.println("I am working click submit"); 
	 
      Thread.sleep(9000);
     String thx= driver.findElement(By.cssSelector(".hero-primary")).getText();
     Thread.sleep(9000);
     System.out.println(thx);
      Assert.assertTrue(thx.equalsIgnoreCase("Thankyou for the order."));
      System.out.println("Ye kaam kr raha h");
 };
 
}
