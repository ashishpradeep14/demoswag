package reportgenerationpkg;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler; // Correct Selenium FileHandler import
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class reportinmaven {
    
    WebDriver driver;
    String url = "https://www.saucedemo.com/";
    ExtentSparkReporter reporter;
    ExtentReports extent;
    ExtentTest test;
    
    @BeforeTest
    public void setupReport() {
        // Ensure the reports directory exists
        File reportDir = new File("./reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        reporter = new ExtentSparkReporter("./reports/demoreports.html");
        reporter.config().setDocumentTitle("Automation report");
        reporter.config().setReportName("function testing");
        
        reporter.config().setTheme(Theme.DARK);
        
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("hostname", "localhost");
        extent.setSystemInfo("os", "windows11");
        extent.setSystemInfo("testname", "ashish");
        extent.setSystemInfo("browsername", "chrome");
        
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openBrowser(ITestResult result) {
        // Create the ExtentTest instance before the test method runs to avoid NullPointerExceptions
        test = extent.createTest(result.getMethod().getMethodName());
        driver.get(url);
    }

    @Test
    public void titleverification() {
        String exp = "Swag Labs";
        String act = driver.getTitle();
        assertEquals(act, exp);
    }

    @Test
    public void buttontextverification() {
        WebElement btntxt = driver.findElement(By.name("login-button"));
        String text = btntxt.getAttribute("value");
        // Note: SauceDemo login button value is actually "Login" (capital L), 
        // so changing "login" to "Login" prevents intentional failure unless testing failure behavior.
        String exp = "Login"; 
        assertEquals(text, exp);
    }

    @AfterMethod
    public void captureStatus(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test case failed is: " + result.getName());
            test.log(Status.FAIL, "Reason for failure: " + result.getThrowable());
            
            String screenshotPath = screenshotmethod(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
            
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test case skipped is: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test case passed successfully.");
        }
    }

    @AfterTest
    public void teardown() {
        extent.flush();
        if (driver != null) {
            driver.quit(); // Always quit the driver to close browser processes
        }
    }

    public static String screenshotmethod(WebDriver driver, String screenshotname) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = screenshotname + ".png";
        String destination = "./reports/" + fileName;
        FileHandler.copy(src, new File(destination));
        return fileName; // Return just the file name if HTML report and screenshots are in the same folder
    }
}