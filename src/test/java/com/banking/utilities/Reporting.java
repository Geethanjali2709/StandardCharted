package com.banking.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting  extends TestListenerAdapter {
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	public void onStart(ITestContext testContext)
	{
		String s = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+s+".html";
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extentReports = new ExtentReports();
		
		extentReports.attachReporter(htmlReporter);
		
		extentReports.setSystemInfo("Host name","localhost");
		extentReports.setSystemInfo("Env", "QA");
		extentReports.setSystemInfo("user", "Geetha");
		
		htmlReporter.config().setDocumentTitle("Banking Proj Test");
		htmlReporter.config().setReportName("Func Test Automation");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		}
	
	public void onTestSuccess(ITestResult tr)
	{
		extentTest = extentReports.createTest(tr.getName());
		extentTest.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr)
	{
		extentTest = extentReports.createTest(tr.getName());
		extentTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String path = System.getProperty("user.dir")+"\\Screenshot\\"+tr.getName()+".png";
		
		File f = new File(path);
		
		if(f.exists())
		{
			try {
				 extentTest.fail("Screenshot is below:"+extentTest.addScreenCaptureFromPath(path));
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void onTestSkipped(ITestResult tr)
	{
	
		extentTest = extentReports.createTest(tr.getName());
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}	
	
	public void onFinish(ITestContext testContext)
	{
		extentReports.flush();
	}

}
