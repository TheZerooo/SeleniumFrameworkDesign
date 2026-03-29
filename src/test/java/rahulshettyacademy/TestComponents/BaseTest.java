package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		// Properties Class -> To  Use Global Data
		
		
		Properties prop=new Properties(); // prop is object of Properties class
		InputStream fis = BaseTest.class
			    .getClassLoader()
			    .getResourceAsStream("GlobalData.properties");

			prop.load(fis);
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
             WebDriverManager.chromedriver().setup();
			  driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			 WebDriverManager.edgedriver().setup();
			  driver=new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			 WebDriverManager.firefoxdriver().setup();
			  driver=new FirefoxDriver();
		}
		 
		 // Selenium 10 sec tak check karta rahega
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.manage().window().maximize();
		 return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		// read json to string -> json file k data ko string form mai read kr raha h
		String jsonContent =FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
	    ObjectMapper mapper =new ObjectMapper(); 
	    
	    // String to HashMap-Jackson Databind (HashMap k data ko List mai Store kr rahe h)
	    List<HashMap<String, String>> data =mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){
	    	
	    });
	    return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
		FileUtils.copyFile(source,file);
		return (System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
	}
	    
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver =initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	
	// If you want t run a test case always then write this -> ( alwaysRun=true )
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
}
