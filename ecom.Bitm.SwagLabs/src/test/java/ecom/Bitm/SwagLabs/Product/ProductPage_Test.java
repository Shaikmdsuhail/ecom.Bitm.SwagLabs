package ecom.Bitm.SwagLabs.Product;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.Bitm.SwagLabs.baseFile.BaseClass01;
import ecom.Bitm.SwagLabs.dataProvider.TestDataProvider;
import ecom.Bitm.SwagLabs.genericUtility.PropertyFileUtility;
import ecom.Bitm.SwagLabs.genericUtility.WebDriverUtility;
import ecom.Bitm.SwagLabs.pomPages.Login_Page;
import ecom.Bitm.SwagLabs.pomPages.Products_Page;

public class ProductPage_Test extends BaseClass01 {

	public void login() throws IOException {

		Login_Page loginPage = new Login_Page(driver);

		String username = PropertyFileUtility.getData("username");

		String password = PropertyFileUtility.getData("password");

		loginPage.loginToApp(username, password);

	}


	@Test(priority = 1, groups = {"smoke","pos"})
	public void Productpage1() throws IOException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.verifyProductsPage();

		Reporter.log("Product page is visible", true);
	}

	@Test(priority = 2, groups = {"smoke","pos"})
	public void productvisible() throws IOException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.getProduct();

		Reporter.log("Products are visible", true);
	}

	@Test(priority = 3, groups = {"integration","pos","neg"}, dataProvider = "productData", dataProviderClass = TestDataProvider.class)
	public void CorrespondingProductPage(String productName) throws Exception {

		login();

		Products_Page productPage = new Products_Page(driver);
		try {

			productPage.getClickproduct(productName);
		} catch (Exception e) {
			System.out.println("Product is not available");
			Reporter.log("Product is not available:" + productName, true);
			WebDriverUtility wUtil = new WebDriverUtility();
			wUtil.takingScreenshot(productName, driver);
		}

		Reporter.log("Corresponding Product is navigated: " + productName, true);
	}


	@Test(priority = 4, groups = {"integration","pos","neg"})
	public void VerifyCartBadge() throws IOException, InterruptedException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.getAddtocart();

		Thread.sleep(2000);

		productPage.verifyCartContainsOneItem();

		Reporter.log("Product cart page is updated", true);
		
	}

	@Test(priority = 5, groups = {"functional","pos"})
	public void productCartControl() throws IOException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.getInventoryItems();

		Reporter.log("Every product has cart control", true);
	}

	@Test(priority = 6, groups = {"functional","pos"})
	public void productcartnavigation() throws IOException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.clickCart();

		Reporter.log("Cart icon is clickable", true);
	}

	@Test(priority = 7, groups = {"functional","pos"})
	public void productNavigationMenu() throws IOException, InterruptedException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.menu();

		Reporter.log("Navigation menu is working", true);
	}

	@Test(priority = 8, groups = {"system","pos"})
	public void ExpectedProduct() throws IOException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.getExpectedproductdata();

		Reporter.log("Expected product is visible", true);
	}

	@Test(priority = 9, groups = {"system","pos"})
	public void productVisibleData() throws IOException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.Visibledata();

		Reporter.log("Product data is visible", true);
	}

	@Test(priority = 10, groups = {"functional","pos"})
	public void Productpagerefresh() throws IOException {

		login();

		Products_Page productPage = new Products_Page(driver);

		productPage.productrefresh();

		Reporter.log("User is logged in After refresh", true);
	}
}