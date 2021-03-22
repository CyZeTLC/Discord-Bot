package de.mcpirat.client.utils.exe.cmd;

import de.mcpirat.client.utils.exe.Command;
import de.mcpirat.client.utils.exe.Sender;
import de.mcpirat.client.utils.manager.PermsManager;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class Cmd_Perms extends Command
{

	@Override
	public boolean execute(Sender sender, TextChannel tc, String[] args) 
	{
		User p = (User) sender.u();
		
		if (!PermsManager.hasPlayerPerm(p, "system.command.perms"))
		{
			tc.sendMessage("Dazu hast du keine Rechte!").queue();
			PermsManager.addPerm(p, "system.command.perms");
		}
		return false;
	}

}
