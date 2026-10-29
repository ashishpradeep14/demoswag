package pagepkg;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_page {
    WebDriver driver;
    WebDriverWait wait;

    public Login_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public void loginToApp(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
        
        passwordField.clear();
        passwordField.sendKeys(password);
        
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public boolean isLoginLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(loginLogo));
        return loginLogo.isDisplayed();
    }

    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}