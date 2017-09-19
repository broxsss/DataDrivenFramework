package com.datadriven.components;
import org.openqa.selenium.WebDriver;
import com.datadriven.Utility.ExcelUtils;
import com.datadriven.pageobjects.Home_Page;
import com.datadriven.pageobjects.LogIn_Page;

// Now this method does not need any arguments

public class SignIn_Action {

	public static void Execute(WebDriver driver,String TestCasename) throws Exception{

		
		String sUserName = null;
		String  sPassword =null;
		
		//This is to get the values from Excel sheet, passing parameters (Row num &amp; Col num)to getCellData method
       if(TestCasename.equals("TestCase01"))
       {
    	   
		sUserName = ExcelUtils.getCellData(1, 1);

		sPassword = ExcelUtils.getCellData(1, 2);
		
       }
       else if(TestCasename.equals("TestCase02"))
       {
    	   
   		sUserName = ExcelUtils.getCellData(2, 1);

   	    sPassword = ExcelUtils.getCellData(2, 2);
    	   
       }
        driver.manage().window().maximize();
		Home_Page.lnk_MyAccount(driver).click();

		LogIn_Page.txtbx_UserName(driver).sendKeys(sUserName);

		LogIn_Page.txtbx_Password(driver).sendKeys(sPassword);

		LogIn_Page.btn_LogIn(driver).click();
		

	}

}