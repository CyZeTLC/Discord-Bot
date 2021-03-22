package de.mcpirat.client.handler;

import de.mcpirat.Main;
import de.mcpirat.client.log.Logger;
import de.mcpirat.client.utils.exe.Sender;
import de.mcpirat.client.utils.manager.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class CommandHandler  extends ListenerAdapter
{
	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
		String msg = e.getMessage().getContentDisplay();
		
		if (msg.startsWith(Main.cmd_Prefix))
		{
			Logger.info(e.getAuthor().getName() + " execute command: " + msg);
			
			if (!CommandManager.getCommandManager().isCommandExists(msg.replace(Main.cmd_Prefix, "")))
			{
				e.getChannel().sendMessage("Dieser Command wurde nicht gefunden!").queue();
				return;
			}
			
			CommandManager.getCommandManager().getCommandByCmd(msg.split(" ")[0].replace(Main.cmd_Prefix, "")).execute(Sender.castUser(e.getAuthor()), e.getTextChannel(), msg.split(" "));
		}
	}

}
