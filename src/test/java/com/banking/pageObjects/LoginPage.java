package com.banking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver rdriver;
	
	public LoginPage(WebDriver ldriver)
	{
		rdriver = ldriver;
		PageFactory.initElements(ldriver,this);
	}
	
	/*
	 * private By txtUserName = By.id("uid"); private By txtPassword =
	 * By.name("password"); private By btnLogin = By.name("btnLogin");
	 */
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement btnLogout;
	
	public void setUserName(String uname)
	{
		//rdriver.findElement(txtUserName).sendKeys(uname);
		txtUserName.sendKeys(uname);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	
	public void clickSubmit()
	{
		btnLogin.click();
	}	
	
	public void clickLogout()
	{
		btnLogout.click();
	}
}
