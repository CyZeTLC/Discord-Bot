package de.mcpirat.client.log;

import java.text.SimpleDateFormat;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class Date 
{
	
	public static String getDateFormat(long timeMillis) 
	{
		
		java.util.Date date = new java.util.Date(timeMillis);
		String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
		String hour_min = new SimpleDateFormat("HH:mm:ss").format(date);
		String datum = mm_dd_yyyy +  " " + hour_min;
		
		return datum;
	}
	
	public static String getInfoFormat() 
	{
		
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
		String hour_min = new SimpleDateFormat("HH:mm:ss").format(date);
		String datum = mm_dd_yyyy +  " " + hour_min;
		
		return "[" + hour_min + " INFO]: ";
	}
	
	public static String getErroFormat() 
	{
		
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
		String hour_min = new SimpleDateFormat("HH:mm:ss").format(date);
		String datum = mm_dd_yyyy +  " " + hour_min;
		
		return "[" + hour_min + " ERROR]: ";
	}
	
	public static String getWarningFormat() 
	{
		
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
		String hour_min = new SimpleDateFormat("HH:mm:ss").format(date);
		String datum = mm_dd_yyyy +  " " + hour_min;
		
		return "[" + hour_min + " WARNING]: ";
	}
	
	public static String getLogDate() 
	{
		
		java.util.Date date = new java.util.Date(System.currentTimeMillis());
		String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
		String hour_min = new SimpleDateFormat("HH").format(date);
		String datum = mm_dd_yyyy +  " " + hour_min;
		
		return "" + datum + "";
	}

}
