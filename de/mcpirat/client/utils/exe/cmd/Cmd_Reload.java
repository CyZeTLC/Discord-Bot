package de.mcpirat.client.utils.exe.cmd;

import java.io.IOException;

import de.mcpirat.Main;
import de.mcpirat.client.log.Logger;
import de.mcpirat.client.utils.exe.Command;
import de.mcpirat.client.utils.exe.Sender;
import net.dv8tion.jda.api.entities.TextChannel;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class Cmd_Reload extends Command
{

	@Override
	public boolean execute(Sender sender, TextChannel tc, String[] args) 
	{
		Logger.warn("[CONSOLE] Reloading Config..");
		try 
		{
			Main.loadConfig();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Logger.info("[CONSOLE] Config reloaded!");
		return false;
	}

}
