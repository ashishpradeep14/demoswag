package testpkg;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Cart_page;
import pagepkg.Checkout_overview_page;
import pagepkg.Checkout_page;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Checkout_overview_test extends Base_class {

    @Test
    public void verifyCheckoutOverviewAndFinish() {
        test = extent.createTest("verifyCheckoutOverviewAndFinish");

        // 1. Login
        Login_page loginPage = new Login_page(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();

        // 2. Select products and go to cart
        Product_page productPage = new Product_page(driver);
        productPage.selectMultipleProducts(3);
        productPage.clickCartButton();

        // 3. Click Checkout
        Cart_page cartPage = new Cart_page(driver);
        cartPage.clickCheckout();

        // 4. Fill in customer information and continue
        Checkout_page checkoutPage = new Checkout_page(driver);
        checkoutPage.setFirstName("Ashish");
        checkoutPage.setLastName("Kumar");
        checkoutPage.setPostalCode("123456");
        checkoutPage.clickContinue();
        test.info("Completed information step and reached Checkout Overview page");

        // 5. Click Finish on the Overview page
        Checkout_overview_page overviewPage = new Checkout_overview_page(driver);
        overviewPage.clickFinish();
        test.info("Clicked finish button");

        // 6. Verification/Assertion (Checks if it navigates to the checkout-complete page)
        String expectedCompleteUrl = "https://www.saucedemo.com/checkout-complete.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedCompleteUrl, "Failed to complete checkout!");

        test.pass("Checkout overview and finish test passed successfully");
    }
}