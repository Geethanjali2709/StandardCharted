package com.banking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.banking.utilities.ReadCommon;

public class BaseClass {

	
	ReadCommon readCommon = new ReadCommon();
	
	public String url = readCommon.getURL();
	public String username = readCommon.getUser();
	public String pwd = readCommon.getPwd();
	
	public static WebDriver driver;
	public static Logger logger;
	
	
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		
		logger = Logger.getLogger("banking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",readCommon.getChromePath());

		driver = new ChromeDriver();
		}
		else
		{
			System.out.println("no driver instantiation");
		}
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.get(url);
		
		
	}
	
	public void screenCapture(WebDriver driver , String testName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshot/"+testName+".png");	
		FileUtils.copyFile(source, target);
		
	}

	
	@AfterClass
	public void tearDown()
	{
		
		driver.quit();
	}
}
