package de.mcpirat.client.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.mcpirat.Main;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class Logger 
{
	public static void info(Object msg)
	{
	  System.out.println(Date.getInfoFormat() + msg);
	  addToLog(Date.getInfoFormat() + msg);	  
	}
	
	public static void err(Object msg)
	{
		System.out.print(Date.getErroFormat());
		if (msg == null)
		{
			System.err.print("");
		}
		else
		{
			System.err.print(msg + "\n");
		}
		addToLog(Date.getErroFormat() + msg);
	}
	
	public static void warn(Object msg)
	{
		System.out.println(Date.getWarningFormat() + msg);
		addToLog(Date.getWarningFormat() + msg);
	}
	
	public static void addToLog(Object msg)
	{
		if (!Main.saveLogs) return;
		
	    try 
	    {
	    	File f = new File("./logs/latest.txt");
			FileWriter log = new FileWriter(f, true);
			log.write(String.valueOf(msg));
			log.write(System.getProperty("line.separator"));
			log.flush();
			log.close();
		}
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	    try 
	    {
	    	if (!new File("./logs/" + Date.getLogDate() + ".txt").exists())
	    	{
	    		new File("./logs/" + Date.getLogDate() + ".txt").createNewFile();
	    	}
	    	
			FileWriter log = new FileWriter(new File("./logs/" + Date.getLogDate() + ".txt"), true);
			log.write(String.valueOf(msg));
			log.write(System.getProperty("line.separator"));
			log.flush();
			log.close();
		}
	    catch (IOException e) 
	    {
			e.printStackTrace();
		}
	}
	
	public static void loop()
	{
		try
		{
			Main.loopBot();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
