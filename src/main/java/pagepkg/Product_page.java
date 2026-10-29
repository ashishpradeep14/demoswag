package pagepkg;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product_page {
    WebDriver driver;
    WebDriverWait wait;

    public Product_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "app_logo")
    private WebElement appLogo;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public void printAllProductDetails() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_name")));
        
        System.out.println("===== Swag Labs Product Catalog =====");
        for (int i = 0; i < productNames.size(); i++) {
            String name = productNames.get(i).getText();
            String price = productPrices.get(i).getText();
            System.out.println("Product " + (i + 1) + " -> Name: " + name + " | Price: " + price);
        }
    }

    public void addAllProductsToCart() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("btn_inventory")));
        for (WebElement button : addToCartButtons) {
            button.click();
        }
        System.out.println("Successfully added all products to the cart!");
    }

    public boolean isAppLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(appLogo));
        return appLogo.isDisplayed();
    }

    public String getProductPageTitle() {
        return driver.getTitle();
    }

    public void logoutFromApp() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenuButton));
        burgerMenuButton.click();
        
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logoutLink.click();
    }
}