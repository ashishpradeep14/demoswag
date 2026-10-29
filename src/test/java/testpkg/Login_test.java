package testpkg;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.Base_class;
import pagepkg.Login_page;

public class Login_test extends Base_class {

    @Test(priority = 1)
    public void testLoginTitleAndLogo() {
        test = extent.createTest("Login Page Title & Logo Verification");

        driver.get(url);
        test.info("Navigated to Swag Labs login page");

        Login_page loginPage = new Login_page(driver);

        String actualTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(actualTitle, "Swag Labs", "Login page title mismatch!");
        test.pass("Login page title verified successfully: " + actualTitle);

        boolean isLogoPresent = loginPage.isLoginLogoDisplayed();
        Assert.assertTrue(isLogoPresent, "Login page logo is missing!");
        test.pass("Login page logo is displayed correctly.");
    }

    @Test(priority = 2)
    public void testLoginWithExcelData() throws IOException, InterruptedException {
        test = extent.createTest("Login Data-Driven Test");

        String excelPath = "C:\\Users\\ASHISH\\Downloads\\passwords.xlsx";
        FileInputStream file = new FileInputStream(excelPath);
        XSSFWorkbook work = new XSSFWorkbook(file);
        XSSFSheet sheet = work.getSheet("Sheet1");
        
        int rowCount = sheet.getLastRowNum();
        Login_page loginPage = new Login_page(driver);
        DataFormatter formatter = new DataFormatter();

        for (int i = 0; i <= rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) continue;

            Cell emailCell = row.getCell(0);
            Cell passCell = row.getCell(1);
            
            String email = (emailCell != null) ? formatter.formatCellValue(emailCell).trim() : "";
            String pass = (passCell != null) ? formatter.formatCellValue(passCell).trim() : "";

            if (!email.isEmpty()) {
                driver.get(url);
                test.info("Attempting login with Username: " + email);
                
                loginPage.loginToApp(email, pass);

                System.out.println("Tested Username: " + email);
                test.pass("Successfully tested login for: " + email);

                Thread.sleep(2000);
            }
        }

        work.close();
        file.close();
    }
}