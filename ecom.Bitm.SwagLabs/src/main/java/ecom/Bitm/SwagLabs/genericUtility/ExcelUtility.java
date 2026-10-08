package ecom.Bitm.SwagLabs.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
    public static String getData(String sheetName, int row, int cell)
            throws IOException {

        FileInputStream fis =new FileInputStream("./src/test/resources/TestData.xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        Row r = sheet.getRow(row);
        Cell c = r.getCell(cell);
        String data = "";
        if (c.getCellType() == CellType.STRING) {
            data = c.getStringCellValue();
        } else if (c.getCellType() == CellType.NUMERIC) {
            data = String.valueOf((long) c.getNumericCellValue());
        }
        workbook.close();
        return data;
    }

}
