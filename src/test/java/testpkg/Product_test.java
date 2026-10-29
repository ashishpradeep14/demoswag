package testpkg;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Product_test extends Base_class {

    @Test
    public void testProductCatalog() throws InterruptedException {
        test = extent.createTest("Product Catalog & Cart Test");

        driver.get(url);
        test.info("Navigated to Swag Labs login page");

        Login_page loginPage = new Login_page(driver);
        loginPage.loginToApp("standard_user", "secret_sauce");
        test.info("Logged in successfully as standard_user");

        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        Product_page productPage = new Product_page(driver);
        
        test.info("Printing all product names and prices to console");
        productPage.printAllProductDetails();
        test.pass("Product catalog details successfully printed.");

        productPage.addAllProductsToCart();
        test.pass("Successfully added all products to the cart.");

        Thread.sleep(2000);
    }
}