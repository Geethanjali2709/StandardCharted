package com.banking.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.model.ScreenCapture;
import com.banking.pageObjects.LoginPage;

import junit.framework.Assert;

public class LoginTest extends BaseClass {
	
	@Test
	public void test_login() throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(username);
		logger.info("username entered");
		lp.setPassword(pwd);
		logger.info("pwd entered");
		lp.clickSubmit();
		
		if(driver.getTitle().contains("Guru99 Bank ManagerHomePage"))
		{
			//screenCapture(driver,"login");
			Assert.assertTrue(true);
			logger.info("usecased is executed");
		}
		else
		{
			screenCapture(driver,"test_login");
			Assert.assertTrue(false);
			logger.info("usecase failed");
		}
		
	}

}
