package testpkg;

import org.testng.annotations.Test;
import basepkg.Base_class;
import pagepkg.Cart_page;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Cart_test extends Base_class {

    @Test
    public void testCartWorkflow() throws InterruptedException {
        test = extent.createTest("Cart Navigation Test");

        driver.get(url);
        Login_page login = new Login_page(driver);
        login.loginToApp("standard_user", "secret_sauce");

        Product_page product = new Product_page(driver);
        product.addAllProductsToCart();

        driver.get("https://www.saucedemo.com/cart.html");

        Cart_page cart = new Cart_page(driver);
        Thread.sleep(1000);
        cart.clickCheckout();
        test.pass("Successfully navigated from Cart to Checkout!");
    }
}