package com.banking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadCommon {
	
	Properties pro;
	
	public ReadCommon()
	{
		File f = new File("./Configuration/config.properties");
		try
		{
		
			FileInputStream fi = new FileInputStream(f);
			pro = new Properties();
			pro.load(fi);
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e.getMessage());
		}
	}
	
	
	public String getURL()
	{
		String s = pro.getProperty("baseURL");
		return s;
	}
	
	public String getUser()
	{
		String s = pro.getProperty("username");
		return s;
	}

	public String getPwd()
	{
		String s = pro.getProperty("password");
		return s;
	}
	
	public String getChromePath()
	{
		String s = pro.getProperty("chromepath");
		return s;
	}
}
