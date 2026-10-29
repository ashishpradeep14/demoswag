package testpkg;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Cart_page;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Cart_test extends Base_class {

    @Test
    public void verifyCartAndProceedToCheckout() {
        test = extent.createTest("verifyCartAndProceedToCheckout");

        // 1. Login
        Login_page loginPage = new Login_page(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        // 2. Select products and navigate to cart
        Product_page productPage = new Product_page(driver);
        productPage.selectMultipleProducts(3);
        productPage.clickCartButton();
        test.info("Added products to cart and navigated to Cart page");

        // 3. Click Checkout from Cart page
        Cart_page cartPage = new Cart_page(driver);
        cartPage.clickCheckout();
        test.info("Clicked checkout button");

        // 4. Verification/Assertion
        String expectedCheckoutUrl = "https://www.saucedemo.com/checkout-step-one.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedCheckoutUrl, "Failed to navigate to checkout page!");

        test.pass("Cart verification and navigation to checkout test passed successfully");
    }
}