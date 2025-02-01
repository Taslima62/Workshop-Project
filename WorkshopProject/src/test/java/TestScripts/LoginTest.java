package TestScripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ModulePage;
import tools.DriverFactory;

public class LoginTest extends BaseTest{
        //private WebDriver driver;

        @BeforeTest
        public void commonActions(){
               // init_Driver();
                driver = DriverFactory.getDriver();
                driver.get(properties.getValue("url"));

        }

        @Test
        public void loginTest() throws InterruptedException {
                test = extent.createTest("Login Test");
                try {
                        LoginPage loginPage;
                        loginPage = new LoginPage();

                        //Wait for fully loaded login page
                        loginPage.waitForBodyContent();
                        loginPage.login(properties.getValue("username"), properties.getValue("password"));

                        //wait for fully loaded page
                        Thread.sleep(1000);
                        test.pass("Login successfully.");
                }catch (Exception e) {
                        test.fail("Login Test failed due to exception: " + e.getMessage());
                        throw new RuntimeException("Test failed due to exception", e);  // **Fail test if an exception occurs**
                }


        }

        @Test
        public void logoutTest(){
                test = extent.createTest("Logout Test");
                try {
                        HomePage homePage = new HomePage(driver);
                        homePage.logout();
                        test.pass("Logged out successfully.");
                }catch (Exception e) {
                                test.fail("Logout Test failed due to exception: " + e.getMessage());
                                throw new RuntimeException("Test failed due to exception", e);  // **Fail test if an exception occurs**
                }
        }

}
