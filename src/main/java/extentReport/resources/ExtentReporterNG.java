package extentReport.resources;

import com.aventstack.extentreports.ExtentReports; // brain -> machine
import com.aventstack.extentreports.reporter.ExtentSparkReporter;// frontend

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
    // current project directory , creates the folder reports and the file index.html automatically
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Result");
        reporter.config().setDocumentTitle("Test Results");
        
//The folder reports and the file index.html will be created later by the ExtentSparkReporter when you generate the report:
       ExtentReports  extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Rahul Shetty");
        return extent;
    }
}
