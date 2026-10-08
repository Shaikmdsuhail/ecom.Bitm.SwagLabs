
package ecom.Bitm.SwagLabs.baseFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import ecom.Bitm.SwagLabs.genericUtility.PropertyFileUtility;


public class BaseClass01 {

    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {

        System.out.println("Execution Started");

    }
    
    @BeforeTest
    public void Bt() {
    	System.out.println("Pre-conditions");
    }
    
    @Parameters("browser")
    @BeforeClass(groups = {"smoke","integration","functional","system","pos","neg"})
    public void beforeClass() throws IOException {

        String browser = PropertyFileUtility.getData("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // Disable Chrome browser notifications
            options.addArguments("--disable-notifications");

            // Disable Save Password popup
            options.addArguments("--disable-save-password-bubble");

            // Disable Chrome Password Manager
            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);

            // Launch Chrome
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();

    }

    @BeforeMethod(groups = {"smoke","integration","functional","system","pos","neg"})
    public void beforeMethod() throws IOException {

        driver.get(PropertyFileUtility.getData("url"));

    }

    @AfterMethod
    public void afterMethod() {

        System.out.println("Test Method Completed");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {

        if (driver != null) {

            driver.quit();

        }

    }
    @AfterTest
    public void At() {
    	System.out.println("Post-condition");
    }

    @AfterSuite
    public void afterSuite() {

        System.out.println("Execution Completed");

    }

}
