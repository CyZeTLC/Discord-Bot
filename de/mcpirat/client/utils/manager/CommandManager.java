package de.mcpirat.client.utils.manager;

import java.util.HashMap;
import java.util.Iterator;

import de.mcpirat.Main;
import de.mcpirat.client.utils.exe.Command;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class CommandManager 
{
	private HashMap<String, Command> all = new HashMap<>();
	public static String currentCmd = "send";
	
	public CommandManager getCommand(String cmd)
	{
		currentCmd = cmd;
		return this;
	}
	
	public void setExecuter(Command cmd, Main plugin)
	{
		if (all.containsValue(cmd))
		{
			unregisterCommand(currentCmd);
		}
		all.put(currentCmd, cmd);
	}
	
	public void unregisterCommand(String cmd)
	{
		if (all.containsKey(cmd))
		{
			all.remove(cmd);
		}
	}
	
	public boolean isCommandExists(String cmd)
	{
		return all.containsKey(cmd.split(" ")[0]);
	}
	
	public Command getCommandByCmd(String cmd)
	{
		return all.get(cmd);
	}
		
	public Iterator getCmdIterator()
	{
		return all.keySet().iterator();
	}
	
	public static CommandManager getCommandManager()
	{
		return Main.cmdManager;
	}
}
