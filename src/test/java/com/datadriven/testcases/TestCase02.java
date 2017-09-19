package com.datadriven.testcases;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.datadriven.Utility.Constant;
import com.datadriven.Utility.ExcelUtils;
import com.datadriven.components.SignIn_Action;
import com.datadriven.pageobjects.Home_Page;
import com.datadriven.property.propertyfile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCase02 {

	propertyfile prop = new propertyfile();
	private static WebDriver driver = null;
	ExtentReports report;
	ExtentTest logger; 
	String Testcasename = "TestCase02";
	@Test
	public void TestCase2() throws Exception
	{
		report=new ExtentReports(System.getProperty("user.dir")+"\\Reports\\datadriven_"+Testcasename+".html");
		 
		logger=report.startTest("VerfiyDataDrivenFramework");
		
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
			logger.log(LogStatus.INFO, "Browser started ");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constant.URL);
			

			SignIn_Action.Execute(driver,Testcasename);
			logger.log(LogStatus.INFO, "Clicked email input box ");
			logger.log(LogStatus.INFO, "Clicked password input box ");
			logger.log(LogStatus.INFO, "Clicked submit");
			Home_Page.getloginsuccessmsg(driver);
			prop.getData("message");
			Assert.assertEquals(Home_Page.getloginsuccessmsg(driver), prop.getData("message"));
			logger.log(LogStatus.PASS, "Login Successfully, now it is the time to Log Off buddy.");
			System.out.println("Login Successfully, now it is the time to Log Off buddy.");

			Home_Page.lnk_LogOut(driver).click(); 

			logger.log(LogStatus.INFO, "Successfully logged off.");

			//This is to send the PASS value to the Excel sheet in the result column.
			ExcelUtils.setCellData("Pass", 2, 3);
		
	}	
		@AfterMethod
		public void teardown(ITestResult result)
		{


			try{
				// Here will compare if test is failing then only it will enter into if condition
				
			
			if(result.getStatus()==ITestResult.FAILURE)
			{
				String screenshot_path =prop.getscreenshot(driver,result.getName());
				String image = logger.addScreenCapture(screenshot_path);
				logger.log(LogStatus.FAIL, "Login verification failed ", image);
				System.out.println("Login unsuccessful check username and password..."+result.getName());
				ExcelUtils.setCellData("Fail", 2, 3);
				logger.log(LogStatus.FAIL, "Login unsuccessful check username and password...");
				} 


	         report.endTest(logger);
	         report.flush();
			driver.quit();
			}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	}


