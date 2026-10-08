package ecom.Bitm.SwagLabs.dropDown;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import ecom.Bitm.SwagLabs.baseFile.BaseClass01;
import ecom.Bitm.SwagLabs.dataProvider.TestDataProvider;
import ecom.Bitm.SwagLabs.genericUtility.PropertyFileUtility;
import ecom.Bitm.SwagLabs.genericUtility.WebDriverUtility;
import ecom.Bitm.SwagLabs.pomPages.DropDown_Page;
import ecom.Bitm.SwagLabs.pomPages.Login_Page;

public class DropDownPage_Test extends BaseClass01 {

	public void login() throws IOException {

		Login_Page loginPage = new Login_Page(driver);

		String username = PropertyFileUtility.getData("username");

		String password = PropertyFileUtility.getData("password");
		
		loginPage.loginToApp(username, password);
		
	}
	
	@Test(priority = 1, groups = {"smoke","pos"})
	public void DropdownVisible() throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.dropdownvisible();

		Reporter.log("TC_031 - Sort dropdown is visible and enabled", true);
	}

	@Test(priority = 2, groups = {"smoke","pos"})
	public void lowToHighSort() throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.lowToHighSort();

		Reporter.log("TC_032 - Price low-to-high sorting verified", true);
	}

	@Test(priority = 3, groups = {"system","pos"})
	public void verifyAllSortOptions() throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifyAllSortOptions();

		Reporter.log("TC_033 - All expected sorting options are available", true);
	}

	@Test(priority = 4, groups = {"system","pos"}, dataProvider = "sortingData", dataProviderClass = TestDataProvider.class)
	public void verifyEachSortOption(String sortOption) throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifyCorrectOrdering(sortOption);

		Reporter.log("TC_034 - Correct ordering verified: " + sortOption, true);
	}

	@Test(priority = 5, groups = {"integration","pos"}, dataProvider = "sortingData", dataProviderClass = TestDataProvider.class)
	public void verifySortingDoesNotLoseProducts(String sortOption) throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifyProductCountAfterSorting(sortOption);

		Reporter.log("TC_035 - Product count remains intact: " + sortOption, true);
	}

	@Test(priority = 6, groups = {"integration","pos","neg"}, dataProvider = "sortingData", dataProviderClass = TestDataProvider.class)
	public void verifySortingAfterAddingItem(String sortOption) throws Exception {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifySortingAfterAddingItem(sortOption);

		Reporter.log("TC_036 - Sorting works after adding item: " + sortOption, true);
		WebDriverUtility wUtil = new WebDriverUtility();
		wUtil.takingScreenshot(sortOption, driver);
	}

	@Test(priority = 7, groups = {"functional","pos"})
	public void verifyDefaultSortOrder() throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifyDefaultSortOrder();

		Reporter.log("TC_037 - Default sort order verified", true);
	}

	@Test(priority = 8, groups = {"functional","pos"})
	public void verifyNameAZ() throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifyNameAZ();

		Reporter.log("TC_038 - Name A-Z sorting verified", true);
	}

	@Test(priority = 9, groups = {"functional","pos"})
	public void verifyNameZA() throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifyNameZA();

		Reporter.log("TC_039 - Name Z-A sorting verified", true);
	}

	@Test(priority = 10, groups = {"functional","pos"})
	public void verifyHighToLowSort() throws IOException {

		login();

		DropDown_Page dropdown = new DropDown_Page(driver);

		dropdown.verifyHighToLowSort();

		Reporter.log("TC_040 - Price high-to-low sorting verified", true);
	}
}