package ecom.Bitm.SwagLabs.pomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Login_Page {

	WebDriver driver;

	public Login_Page(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement userNameTF;

	@FindBy(id = "password")
	private WebElement passwordTF;

	@FindBy(id = "login-button")
	private WebElement Loginbtn;

	@FindBy(xpath = "//h3[@data-test='error']")
	private WebElement error;

	public void getUserNameTF(String username) {

		userNameTF.clear();

		userNameTF.sendKeys(username);
	}

	public void loginToApp(String username,String password) {
		userNameTF.clear();
		userNameTF.sendKeys(username);
		passwordTF.clear();
		passwordTF.sendKeys(password);
		Loginbtn.click();;
	}

	public boolean getError(String i) {

		boolean r;

		if (i.equals("Epic sadface: Username and password do not match any user in this service")) {

			r = true;
		}

		else if (i.equals("Epic sadface: Username is required")) {

			r = true;
		}

		else if (i.equals("Epic sadface: Password is required")) {

			r = true;
		}

		else {

			r = false;
		}

		return r;
	}

	public void ErrorDisplay() {

		String i = error.getText();

		Assert.assertTrue(getError(i), "Unexpected login error message: " + i);

		System.out.println("Login Error: " + i);
	}

	public void displayloginpage() {

		boolean u = userNameTF.isDisplayed();

		boolean p = passwordTF.isDisplayed();

		boolean l = Loginbtn.isDisplayed();

		Assert.assertTrue(u && p && l, "Login page controls are not displayed");

		System.out.println("Login Page Displayed");
	}
}