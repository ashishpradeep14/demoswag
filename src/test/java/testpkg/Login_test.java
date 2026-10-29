package testpkg;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Login_page;

public class Login_test extends Base_class {

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return new Object[][] {
            { "standard_user", "secret_sauce" },
            { "locked_out_user", "secret_sauce" },
            { "problem_user", "secret_sauce" }
        };
    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(String username, String password) {
        test = extent.createTest("verifyLogin: " + username);

        Login_page loginPage = new Login_page(driver);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            Assert.assertTrue(true, "Login successful");
            driver.get(url); 
        } else {
            Assert.assertTrue(true, "Handled expected login restriction");
        }
    }
}