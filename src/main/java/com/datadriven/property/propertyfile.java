package com.datadriven.property;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
	
}
