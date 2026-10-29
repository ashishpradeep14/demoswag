package pagepkg;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout_overview_page {
    WebDriver driver;
    WebDriverWait wait;

    public Checkout_overview_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    public void clickFinish() {
       
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
        wait.until(ExpectedConditions.visibilityOf(completeHeader));
        return completeHeader.getText();
    }
}