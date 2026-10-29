package pagepkg;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout_page {
    WebDriver driver;
    WebDriverWait wait;

    public Checkout_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public void fillCheckoutDetails(String firstName, String lastName, String postalCode) {
        
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }
}