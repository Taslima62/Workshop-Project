package TestScripts;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import enums.FilePaths;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import tools.DriverFactory;
import tools.PropertiesUtil;
import tools.ReportManager;


public class BaseTest {


    public PropertiesUtil properties = new PropertiesUtil(FilePaths.CONFIG_FILE.getPath());  // Initialize directly

    protected static ExtentReports extent; // Make it static to share across all test classes
    protected ExtentTest test;
    public WebDriver driver;

    @BeforeSuite
    public void init_Driver(){
        //driver.get(propertiesUtil.getValue("url"));
        //properties = new PropertiesUtil(FilePaths.CONFIG_FILE.getPath());
        new DriverFactory().init_driver(properties.getValue("browser"));
        extent = ReportManager.getInstance();
    }

    @BeforeTest
    public void commonActions() throws InterruptedException {
        driver = DriverFactory.getDriver();
        driver.get(properties.getValue("url"));

    }

    @AfterSuite
    public void tear_Down(){
       //DriverFactory.getDriver().quit();
        // Quit the driver after each test
        if (driver != null) {
            driver.quit();
        }

        if (extent != null) {
            extent.flush();
        }
    }
}

