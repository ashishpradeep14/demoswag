package basepkg;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Base_class {
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriver driver;

    public String url = "https://www.saucedemo.com/";

    @BeforeSuite
    public void browser_launch() {
        extent = Reporterclass.sam();
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get(url);
    }

    @AfterSuite
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }
}