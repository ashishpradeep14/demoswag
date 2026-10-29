package pagepkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout_overview_page {
    WebDriver driver;

    // Finish button on the overview page
    @FindBy(id = "finish")
    WebElement finishButton;

    // Cancel button
    @FindBy(id = "cancel")
    WebElement cancelButton;

    public Checkout_overview_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFinish() {
        finishButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }
}