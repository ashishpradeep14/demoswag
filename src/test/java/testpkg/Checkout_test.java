package testpkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Cart_page;
import pagepkg.Checkout_page;
import pagepkg.Login_page;
import pagepkg.Product_page;

public class Checkout_test extends Base_class {

    @Test
    public void testCheckoutWithExcelData() throws IOException, InterruptedException {
        test = extent.createTest("Checkout Data-Driven Test");

       
        driver.get(url);
        Login_page login = new Login_page(driver);
        login.loginToApp("standard_user", "secret_sauce");

       
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

       
        Product_page product = new Product_page(driver);
        product.addAllProductsToCart();

      
        driver.get("https://www.saucedemo.com/cart.html");
        Cart_page cart = new Cart_page(driver);
        cart.clickCheckout();

       
        String excelPath = "C:\\Users\\ASHISH\\Downloads\\passwords.xlsx";
        FileInputStream file = new FileInputStream(excelPath);
        XSSFWorkbook work = new XSSFWorkbook(file);
        XSSFSheet sheet = work.getSheet("Sheet1");

        Checkout_page checkout = new Checkout_page(driver);
        DataFormatter formatter = new DataFormatter();

        if (sheet.getLastRowNum() >= 0) {
            XSSFRow row = sheet.getRow(0); 
            if (row != null) {
                Cell cell1 = row.getCell(0);
                Cell cell2 = row.getCell(1);
                Cell cell3 = row.getCell(2);

                String firstName = (cell1 != null) ? formatter.formatCellValue(cell1).trim() : "Ashish";
                String lastName = (cell2 != null) ? formatter.formatCellValue(cell2).trim() : "Pradeep";
                String postalCode = (cell3 != null) ? formatter.formatCellValue(cell3).trim() : "695001";

                test.info("Filling checkout form -> First: " + firstName + ", Last: " + lastName + ", Zip: " + postalCode);
                checkout.fillCheckoutDetails(firstName, lastName, postalCode);
                
                Thread.sleep(1000);
                checkout.clickContinue();
                
                test.pass("Checkout details filled and submitted successfully.");
                System.out.println("Checkout information submitted successfully using formatted Excel data.");
            }
        }

        work.close();
        file.close();
    }
}