package testpkg;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Cart_page;
import pagepkg.Checkout_page;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Checkout_test extends Base_class {

    @Test
    public void verifyCheckoutInformation() {
        test = extent.createTest("verifyCheckoutInformation");

        // 1. Login
        Login_page loginPage = new Login_page(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        // 2. Select products and go to cart
        Product_page productPage = new Product_page(driver);
        productPage.selectMultipleProducts(2);
        productPage.clickCartButton();

        // 3. Click Checkout
        Cart_page cartPage = new Cart_page(driver);
        cartPage.clickCheckout();
        test.info("Navigated to Checkout Information page");

        // 4. Fill in Information Form
        Checkout_page checkoutPage = new Checkout_page(driver);
        checkoutPage.setFirstName("Ashish");
        checkoutPage.setLastName("Kumar");
        checkoutPage.setPostalCode("123456");
        test.info("Entered first name, last name, and postal code");

        // 5. Click Continue
        checkoutPage.clickContinue();

        // 6. Verification
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Failed to proceed to overview page!");

        test.pass("Checkout information test passed successfully");
    }
}