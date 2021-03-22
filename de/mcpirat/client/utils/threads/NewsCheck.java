package de.mcpirat.client.utils.threads;

import java.awt.Color;
import java.net.URL;
import java.util.Scanner;

import de.mcpirat.Main;
import de.mcpirat.client.log.Logger;
import de.mcpirat.client.utils.ints.IntegerUtil;
import net.dv8tion.jda.api.EmbedBuilder;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class NewsCheck implements Runnable
{
	
	@Override
	public void run() 
	{
		try
		{			
			while (true)
			{
				if (IntegerUtil.distance(System.currentTimeMillis(), Main.lastCheck) > 1000
						|| Main.lastCheck == 0)
				{
					Main.lastCheck = System.currentTimeMillis();
					
					Scanner sc = new Scanner(new URL("https://forum.lostify.net").openStream(), "UTF-8");
					String content = "";
									
					while (sc.hasNext())
					{
						content += sc.next();
					}
									
					if (!content.equals(Main.currentContent))
					{
						Main.currentContent = content;
						Logger.info("New Post in Forum");
						
						if (Main.c != null)
						{
							Logger.info("Sending Message");
							EmbedBuilder eb = new EmbedBuilder();
							eb.setColor(Color.ORANGE);
							eb.setTitle("Neuer Forum Post", "https://forum.lostify.net/index.php");
							eb.setDescription("Du kannst den Post unter `https://forum.lostify.net` anschauen!");
							Main.c.sendMessage(eb.build()).queue();
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
