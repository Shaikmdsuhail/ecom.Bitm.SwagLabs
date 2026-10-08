package ecom.Bitm.SwagLabs.dataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class TestDataProvider {

	// =========================================================
	// LOGIN DATA
	// =========================================================

	@DataProvider(name = "loginDetails")
	public Object[][] loginDetails() throws EncryptedDocumentException, IOException {

		FileInputStream file = new FileInputStream("./src/test/resources/TestData.xlsx");

		Workbook wb = WorkbookFactory.create(file);

		Sheet sh = wb.getSheetAt(0);

		Assert.assertNotNull(sh, "Login Excel sheet is not available");

		int rowCount = sh.getLastRowNum();

		Object[][] obj = new Object[rowCount][2];

		for (int row = 1; row <= rowCount; row++) {

			obj[row - 1][0] = sh.getRow(row).getCell(0).getStringCellValue();

			obj[row - 1][1] = sh.getRow(row).getCell(1).getStringCellValue();
		}

		wb.close();
		file.close();

		return obj;
	}

	// =========================================================
	// PRODUCT DATA
	// =========================================================

	@DataProvider(name = "productData")
	public Object[][] productData() throws EncryptedDocumentException, IOException {

		FileInputStream file = new FileInputStream("./src/test/resources/demo3.xlsx");

		Workbook wb = WorkbookFactory.create(file);

		Sheet sh = wb.getSheetAt(0);

		Assert.assertNotNull(sh, "Product Excel sheet is not available");

		int rowCount = sh.getLastRowNum();

		Object[][] obj = new Object[rowCount][1];

		for (int row = 1; row <= rowCount; row++) {

			obj[row - 1][0] = sh.getRow(row).getCell(0).getStringCellValue();
		}

		wb.close();
		file.close();

		return obj;
	}

	// =========================================================
	// SORTING DATA
	// =========================================================

	@DataProvider(name = "sortingData")
	public Object[][] sortingData() throws EncryptedDocumentException, IOException {

		FileInputStream file = new FileInputStream("./src/test/resources/productSortingData.xlsx");

		Workbook wb = WorkbookFactory.create(file);

		// Get first sheet instead of depending on sheet name
		Sheet sh = wb.getSheetAt(0);

		Assert.assertNotNull(sh, "Sorting Excel sheet is not available");

		int rowCount = sh.getLastRowNum();

		Assert.assertTrue(rowCount > 0, "Sorting Excel does not contain sorting data");

		Object[][] obj = new Object[rowCount][1];

		for (int row = 1; row <= rowCount; row++) {

			String sortOption = sh.getRow(row).getCell(0).getStringCellValue().trim();

			Assert.assertFalse(sortOption.isEmpty(), "Sort option cannot be empty at Excel row " + (row + 1));

			obj[row - 1][0] = sortOption;
		}

		wb.close();
		file.close();

		return obj;
	}
}