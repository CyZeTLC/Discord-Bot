package de.mcpirat.client.utils.exe.cmd;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import de.mcpirat.Main;
import de.mcpirat.client.log.Logger;
import de.mcpirat.client.utils.exe.Command;
import de.mcpirat.client.utils.exe.Sender;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class Cmd_Logs extends Command
{

	@Override
	public boolean execute(Sender sender, TextChannel tc, String[] args) 
	{
		User p = (User) sender.u();
		
//		if (!tc.getGuild().getMember(p).hasPermission(Permission.BAN_MEMBERS))
//		{
//			tc.sendMessage("Dazu hast du keine Rechte!").queue();
//			return true;
//		}
		
		if (args.length == 1)
		{
			tc.sendMessage("Nutze: " + Main.cmd_Prefix + "logs <latest>/<date> <date-hour>").queue();
			tc.sendMessage("Z.b. " + Main.cmd_Prefix + "logs 20.03.2021 20").queue();;
			return true;
		}
		
		if (args.length == 2)
		{
			if (args[1].equalsIgnoreCase("latest"))
			{
				File f = new File("./logs/" + "latest.txt");
				
				try 
				{
					Scanner sc = new Scanner(f, "UTF-8");
					String out = "";
					
					while (sc.hasNextLine())
					{
						out += sc.nextLine() + System.getProperty("line.separator");
					}
					
					EmbedBuilder b = new EmbedBuilder();
					b.setColor(Color.ORANGE);
					b.setTitle("Log: " + args[1] + " " + args[2]);
					b.setDescription(out);
					tc.sendMessage(b.build()).queue();
				} 
				catch (Exception e) 
				{
					tc.sendMessage("Der Log ist größer als 2048 Zeichen!").queue();
					tc.sendMessage("Du kannst den Log bei einem Server Owner oder Developer nachfragen!").queue();
				}
			}
		}
		
		if (args.length == 3)
		{
			File f = new File("./logs/" + args[1] + " " + args[2] + ".txt");
			
			Logger.info("Looking for " + f.getAbsolutePath());
			
			if (!f.exists())
			{
				tc.sendMessage("Dieser Log wurde nicht gefunden!").queue();
				return true;
			}
			
			try 
			{
				Scanner sc = new Scanner(f, "UTF-8");
				String out = "";
				
				while (sc.hasNextLine())
				{
					out += sc.nextLine() + System.getProperty("line.separator");
				}
				
				EmbedBuilder b = new EmbedBuilder();
				b.setColor(Color.ORANGE);
				b.setTitle("Log: " + args[1] + " " + args[2]);
				b.setDescription(out);
				tc.sendMessage(b.build()).queue();
			} 
			catch (Exception e) 
			{
				tc.sendMessage("Der Log ist größer als 2048 Zeichen!").queue();
				tc.sendMessage("Du kannst den Log bei einem Server Owner oder Developer nachfragen!").queue();
			}
		}
		
		return false;
	}
	
}
