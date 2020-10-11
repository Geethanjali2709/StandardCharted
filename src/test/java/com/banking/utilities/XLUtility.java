package com.banking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow rw;
	public static XSSFCell ce;
	
	
	public static int getrwcount(String file,String sheet) throws IOException
	{
		fi = new FileInputStream(file);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheet);
		
		int rwcount = sh.getLastRowNum();
		wb.close();
		fi.close();
		return rwcount;
		}
	
	public static int getcellcount(String file,String sheet,int rowno) throws IOException
	{
		fi = new FileInputStream(file);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheet);
		rw = sh.getRow(rowno);
		int cellcount = rw.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public static void setdata(String file,String sheet,int rowno,int cellno,String data) throws IOException
	{
		fi = new FileInputStream(file);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheet);
		rw = sh.getRow(rowno);
		ce=rw.createCell(cellno);
		ce.setCellValue(data);
		fo=new FileOutputStream(file);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public static String getdata(String file,String sheet,int rowno,int celno) throws IOException
	{
		fi = new FileInputStream(file);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheet);
		rw = sh.getRow(rowno);
		ce = rw.getCell(celno);
		String data;
		try 
		{
			DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(ce);
            return cellData;
		}
		catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	}
