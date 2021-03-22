package de.mcpirat.client.utils.exe;

import java.io.Console;

import net.dv8tion.jda.api.entities.User;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class Sender 
{
	private User u;
	
	public Sender(Console c)
	{
		
	}
	
	public Sender(User u)
	{
		this.u = u;
	}
	
	public User u()
	{
		return this.u;
	}
	
	public static Sender castUser(User u)
	{
		return new Sender(u);
	}
	
	public static Sender castConsole(Console c)
	{
		return new Sender(c);
	}
}
