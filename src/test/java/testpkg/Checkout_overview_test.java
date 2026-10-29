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
    public void testCompleteOrderPlacement() throws InterruptedException {
        test = extent.createTest("End-to-End Order Placement Test");

        driver.get(url);
        Login_page login = new Login_page(driver);
        login.loginToApp("standard_user", "secret_sauce");

        Product_page product = new Product_page(driver);
        product.addAllProductsToCart();
        
        driver.get("https://www.saucedemo.com/cart.html");
        Cart_page cart = new Cart_page(driver);
        cart.clickCheckout();

        Checkout_page checkout = new Checkout_page(driver);
        checkout.fillCheckoutDetails("Ashish", "Pradeep", "695001");
        checkout.clickContinue();

        Checkout_overview_page overview = new Checkout_overview_page(driver);
        overview.clickFinish();

        String confirmation = overview.getConfirmationMessage();
        Assert.assertEquals(confirmation, "Thank you for your order!", "Order confirmation text mismatch!");
        
        test.pass("Order completed successfully with confirmation message: " + confirmation);
        
        // Console print statement for Order Placed message
        System.out.println("Order placed successfully! Confirmation message: " + confirmation);
    }
}