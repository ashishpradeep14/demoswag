package testpkg;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Product_test extends Base_class {

    @Test
    public void verifySelectProductsAndMoveToCart() {
        test = extent.createTest("verifySelectProductsAndMoveToCart");

        
        Login_page loginPage = new Login_page(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

      
        Product_page productPage = new Product_page(driver);
        int productsToSelect = 3;
        productPage.selectMultipleProducts(productsToSelect);
        test.info("Selected " + productsToSelect + " products from the inventory page");

       
        productPage.clickCartButton();
        test.info("Clicked cart icon to move to cart page");

        
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Failed to navigate to the cart page!");

        test.pass("Products selected and successfully navigated to cart page");
    }
}