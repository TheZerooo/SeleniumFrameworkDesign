package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extentReport.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	 @Override
	    public void onTestStart(ITestResult result) {
	        // Executes when a test starts
		 
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread id(ErrorValidationTest) ->Test
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        // Executes when a test passes
	    	extentTest.get().log(Status.PASS,"Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        // Executes when a test fails
	    	extentTest.get().fail(result.getThrowable());//This line records the failure reason (exception) in the test report.
	    	
	    	try {
	    	driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	    	String filePath=null;
	    	}catch (Exception el) {
	    		el.printStackTrace();
	    	}
	    	// ScreenShort , Attach to report
	    	
	    	 String filePath=null;
	    	try {
				filePath=getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        // Executes when a test is skipped
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	        // Rarely used
	    }

	    @Override
	    public void onTestFailedWithTimeout(ITestResult result) {
	        // Executes when test fails due to timeout
	    }

	    @Override
	    public void onStart(ITestContext context) {
	        // Executes before <test> tag in testng.xml
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        // Executes after <test> tag in testng.xml
	    	extent.flush();
	    }
}
