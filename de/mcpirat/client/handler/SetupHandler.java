package de.mcpirat.client.handler;

import de.mcpirat.Main;
import de.mcpirat.client.log.Date;
import de.mcpirat.client.log.Logger;
import de.mcpirat.client.utils.manager.PermsManager;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class SetupHandler extends ListenerAdapter
{
	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
		if (!Main.isBotSetup)
		{
			Main.c = e.getTextChannel();
			Main.isBotSetup = true;
			Logger.info("Bot Channel Set!");
			return;
		}
		
        if (e.isFromType(ChannelType.PRIVATE)
        		&& !e.getAuthor().equals(e.getJDA().getSelfUser()))
        {
        	String str = "[PM] %s: %n\n".replace("%s", e.getAuthor().getName()).replace("%n", e.getMessage().getContentDisplay());
        	Logger.warn(str.replace(System.getProperty("line.separator"), ""));
        	
        	PermsManager.addPerm(e.getAuthor(), "system.pm.false");
        	
        	if (!Main.allowPrivate)
        	{
        		try { e.getChannel().sendMessage("Bitte sende mir keine Privaten Nachrichten!").queue(); } catch (Exception e1) {}
        	}
        }
        else
        {
        	String str = "[%s][%tc] %n: %m\n".replace("%s", e.getGuild().getName())
        			.replace("%tc", e.getTextChannel().getName())
        			.replace("%n", e.getMember().getEffectiveName())
        			.replace("%m", e.getMessage().getContentDisplay());
        	Logger.info(str.replace(System.getProperty("line.separator"), ""));
        }
	}
}
