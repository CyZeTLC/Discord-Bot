package de.mcpirat.client.utils.exe;

import java.io.Console;

import net.dv8tion.jda.api.entities.TextChannel;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public abstract class Command 
{
	public abstract boolean execute(Sender sender, TextChannel tc, String[] args);
}
