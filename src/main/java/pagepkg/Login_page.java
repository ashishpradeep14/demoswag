package pagepkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_page {
    WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    public Login_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String user) {
        usernameField.clear();
        usernameField.sendKeys(user);
    }

    public void setPassword(String pwd) {
        passwordField.clear();
        passwordField.sendKeys(pwd);
    }

    public void clickLogin() {
        loginButton.click();
    }
}