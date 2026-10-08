package ecom.Bitm.SwagLabs.genericUtility;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class WebDriverUtility {
	public void takingScreenshot(String fileName,WebDriver driver) throws Exception {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File per = new File("./screenshots/"+fileName+".png");
		FileHandler.copy(temp, per);
	}
}
