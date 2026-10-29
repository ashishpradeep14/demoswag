package pagepkg;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Product_page {
    WebDriver driver;

   
    @FindBy(xpath = "//button[starts-with(@id, 'add-to-cart-')]")
    List<WebElement> addToCartButtons;

   
    @FindBy(className = "shopping_cart_link")
    WebElement cartLink;

    public Product_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   
    public void selectMultipleProducts(int count) {
        for (int i = 0; i < count && i < addToCartButtons.size(); i++) {
            addToCartButtons.get(i).click();
        }
    }

    
    public void clickCartButton() {
        cartLink.click();
    }
}