package de.mcpirat.client.utils.exe.cmd;

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
public class Cmd_Send extends Command
{

	@Override
	public boolean execute(Sender sender, TextChannel arg2, String[] args) 
	{
		// 0 1 2
		if (args.length < 2)
		{
			Logger.warn("Usage: send <CHANNEL_ID> <MESSAGE>");
			return true;
		}
		
		String msg = "";
		
		for (int i = 1; i < args.length; i++)
		{
			msg += args[i] + " ";
		}
		
		TextChannel tc = null;

		try
		{
			tc = Main.jda.getTextChannelById(args[1]);
			tc.sendMessage(msg);
		}
		catch (Exception e)
		{
			Logger.err("Dieser Channel wurde nicht gefunden!");
			return true;
		}
		
		Logger.info("Messages send.");
		
		return false;
	}

}
