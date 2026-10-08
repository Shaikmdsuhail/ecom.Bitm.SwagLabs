package ecom.Bitm.SwagLabs.login;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.Bitm.SwagLabs.baseFile.BaseClass01;
import ecom.Bitm.SwagLabs.dataProvider.TestDataProvider;
import ecom.Bitm.SwagLabs.genericUtility.PropertyFileUtility;
import ecom.Bitm.SwagLabs.genericUtility.WebDriverUtility;
import ecom.Bitm.SwagLabs.pomPages.Login_Page;

public class Loginpage_Test extends BaseClass01 {

	@Test(priority = 1, groups = {"smoke","pos"})
	public void loginverify() {

		Login_Page loginpage = new Login_Page(driver);

		loginpage.displayloginpage();

		Reporter.log("Login page is displayed with username, password and Login controls", true);
	}


	@Test(priority = 2, groups = {"functional","neg"}, dataProvider = "loginDetails", dataProviderClass = TestDataProvider.class)
	public void loginInvalid(String username, String password) throws Exception {

		Login_Page loginPage = new Login_Page(driver);

		loginPage.loginToApp(username, password);


		loginPage.ErrorDisplay();

		Reporter.log("Login rejected for Username: " + username, true);
		WebDriverUtility wUtil = new WebDriverUtility();
		wUtil.takingScreenshot(username, driver);
	}


	@Test(priority = 3, groups = { "smoke", "functional","pos" })
	public void loginvalid() throws IOException {

		Login_Page loginPage = new Login_Page(driver);


		String username = PropertyFileUtility.getData("username");

		String password = PropertyFileUtility.getData("password");


		loginPage.loginToApp(username, password);


		Reporter.log("Valid login executed successfully", true);
	}
}