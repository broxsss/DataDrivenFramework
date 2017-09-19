package com.datadriven.pageobjects;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

public class Home_Page {

	private static WebElement element = null;

	public static WebElement lnk_MyAccount(WebDriver driver){

		element = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a"));

		return element;

	}

	public static WebElement lnk_LogOut(WebDriver driver){

		element = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[2]/a"));

		return element;

	}

	public static String getloginsuccessmsg(WebDriver driver) {
		String loginmsg = driver.findElement(By.xpath("//*[@id='center_column']/p")).getText();
        return loginmsg;

		
	}

}