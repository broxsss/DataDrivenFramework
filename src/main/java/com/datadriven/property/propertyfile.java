package com.datadriven.property;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

public class propertyfile {
	
    Properties  prop ;
	public void loaddata() throws IOException
	{
		prop = new Properties();
		File file = new File(System.getProperty("user.dir")+"\\configuration.properties");
		FileReader fr = new FileReader(file);
		prop.load(fr);			
	}
	
	public String getData(String Data) throws IOException
	{
		loaddata();
		String data = prop.getProperty(Data);
		return data;
		
	}
	
	 public String getscreenshot(WebDriver driver,String Testcasename) 
     {
             File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
          //The below method will save the screen shot in d drive with name "screenshot.png"
             try {
            	  // now copy the  screenshot to desired location using copyFile method
            	 
            	 FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+Testcasename+"_"+System.currentTimeMillis()+".png"));
            	       }
            	 
            	catch (IOException e)
            	 
            	{
            	 
            	System.out.println(e.getMessage());
            	 
            	    }
             
         return  System.getProperty("user.dir")+"\\screenshots\\"+Testcasename+"_"+System.currentTimeMillis()+".png";   
     }

	
	
}
