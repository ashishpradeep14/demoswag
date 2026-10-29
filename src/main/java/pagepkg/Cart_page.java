package pagepkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart_page {
    WebDriver driver;

    
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    
    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    public Cart_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }
}