package com.banking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.ScreenCapture;
import com.banking.pageObjects.LoginPage;
import com.banking.utilities.XLUtility;

import junit.framework.Assert;

public class DDTTest extends BaseClass{
	
	@Test(dataProvider = "LoginData")
	public void loginDDt(String user,String pwd) throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("username entered");
		lp.setPassword(pwd);
		logger.info("pwd entered");
		lp.clickSubmit();
		
		if(isalertPresent() == true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		
		else
		{
			//screenCapture(driver,"loginnew");
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
		}
		
		
	}
	
	public boolean isalertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\banking\\testData\\LoginData.xlsx";
		
		int rwcount = XLUtility.getrwcount(path, "sheet1");
		int cellcount = XLUtility.getcellcount(path, "sheet1", 1);
		
		String [][] logindata = new String[rwcount][cellcount];
		
		for(int i=1;i<=rwcount;i++)
		{
			for(int j=0;j<cellcount;j++)
			{
				logindata[i-1][j] = XLUtility.getdata(path, "sheet1", i,j);
			}
			}
		return logindata;
}
	
}
