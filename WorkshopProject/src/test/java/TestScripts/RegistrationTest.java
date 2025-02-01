package TestScripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import tools.DriverFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationTest extends BaseTest{

    @BeforeTest
    public void commonActions() {
        driver = DriverFactory.getDriver();
        driver.get(properties.getValue("url"));
    }

    @DataProvider(name = "registrationData")
    public Object[][] registrationData() throws IOException {
        String csvFile = "src/test/resources/registration_data.csv";
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        List<Object[]> data = new ArrayList<>();

        // Skip the header row
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            data.add(fields);
        }
        br.close();

        return data.toArray(new Object[0][]);
    }

    @Test(dataProvider = "registrationData")
    public void registrationTest(String firstname, String lastname, String dynamicEmail, String telephone, String password, String confirmPassword, String subscribe) {
        test = extent.createTest("Registration Test: " + firstname + " " + lastname);

        RegistrationPage registrationPage = new RegistrationPage();
        HomePage homePage = new HomePage(driver);

        registrationPage.registrationTest(firstname, lastname, dynamicEmail, telephone, password, confirmPassword, subscribe, test);

           // test.pass("Registration executed successfully.");
        homePage.logout();

    }


}
