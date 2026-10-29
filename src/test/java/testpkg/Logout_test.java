package testpkg;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Logout_test extends Base_class {

    @Test
    public void testUserLogoutWorkflow() throws InterruptedException {
        test = extent.createTest("Logout Workflow Test");

        driver.get(url);
        Login_page loginPage = new Login_page(driver);
        loginPage.loginToApp("standard_user", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        Product_page productPage = new Product_page(driver);
        productPage.logoutFromApp();

        wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
        String currentUrl = driver.getCurrentUrl();
        
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/", "Logout failed!");
        Assert.assertTrue(loginPage.isLoginLogoDisplayed(), "Login page logo is not visible after logout!");
        
        test.pass("Successfully logged out and redirected to login page.");
        
        // Console print statement for Logout message
        System.out.println("User successfully logged out and returned to login page!");

        Thread.sleep(2000);
    }
}