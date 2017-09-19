package com.datadriven.testcases;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.datadriven.Utility.Constant;
import com.datadriven.Utility.ExcelUtils;
import com.datadriven.components.SignIn_Action;
import com.datadriven.pageobjects.Home_Page;
import com.datadriven.property.propertyfile;


public class TestCase01 {
    static propertyfile prop = new propertyfile();
	private static WebDriver driver = null;

	@Test
	public void TestCase1() throws Exception {

		try
		{
		//This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
        ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Login");
		
        System.out.println(Constant.Path_TestData);
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//driver//chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-infobars");
        options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options); 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constant.URL);
        String Testcasename = "TestCase01";
         
		SignIn_Action.Execute(driver,Testcasename);
		Home_Page.getloginsuccessmsg(driver);
		prop.getData("message");
		Assert.assertEquals(Home_Page.getloginsuccessmsg(driver), prop.getData("message"));

		System.out.println("Login Successfully, now it is the time to Log Off buddy.");

		Home_Page.lnk_LogOut(driver).click(); 

		driver.quit();

		//This is to send the PASS value to the Excel sheet in the result column.
          ExcelUtils.setCellData("Pass", 1, 3);
		}
		catch(Exception e)
		{
			System.out.println("Login unsuccessful check username and password...");
			driver.quit();
			ExcelUtils.setCellData("Fail", 1, 3);
			e.printStackTrace();
			
		}
	}

}