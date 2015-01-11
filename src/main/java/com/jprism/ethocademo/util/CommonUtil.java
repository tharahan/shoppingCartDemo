package com.jprism.ethocademo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtil {
	
	public static java.sql.Date convertToMySqlDate(Date date) 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = dateFormat.format(date);
		
		System.out.println(strDate);
		
		java.sql.Date sqlDate;
		try {
			sqlDate = new java.sql.Date(dateFormat.parse(strDate).getTime());
			System.out.println(strDate);
			
			return sqlDate;
		} catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public static java.sql.Timestamp convertToMySqlTimestamp(Date date) 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = dateFormat.format(date);
		
		System.out.println(strDate);
		
		java.sql.Timestamp sqlDate;
		try {
			sqlDate = new java.sql.Timestamp(dateFormat.parse(strDate).getTime());
			System.out.println(strDate);
			
			return sqlDate;
		} catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
}
