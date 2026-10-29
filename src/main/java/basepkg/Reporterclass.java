package basepkg;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler; // Correct FileHandler for Selenium
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporterclass {
    public static WebDriver driver;
    public static ExtentSparkReporter reporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    
    public static ExtentReports sam() {
        // Provide a valid path for the HTML report
        reporter = new ExtentSparkReporter("./Reports/extentReport.html");
        reporter.config().setDocumentTitle("Automation report");
        reporter.config().setReportName("Functional test");
        reporter.config().setTheme(Theme.DARK);
        
        // Initialize ExtentReports before setting system info
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        
        extent.setSystemInfo("hostname", "localhost");
        extent.setSystemInfo("os", "windows");
        extent.setSystemInfo("testname", "Ashish");
        extent.setSystemInfo("browser", "chrome");
        
        return extent;
    }
    
    @AfterMethod 
    public void browserclose(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test case failed is: " + result.getName());
            test.log(Status.FAIL, "Test case failed is: " + result.getThrowable());
            
            
            
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test case skipped is: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test case passed is: " + result.getName());
        }
    }
     
    public static String screenshotmethod(WebDriver driver, String screenshotname) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "./Screenshots/" + screenshotname + ".png";
        FileHandler.copy(src, new File(destination));
        return destination;
    }
}