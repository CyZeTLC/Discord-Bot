package de.mcpirat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.security.auth.login.LoginException;

import org.json.JSONObject;

import de.mcpirat.client.handler.CommandHandler;
import de.mcpirat.client.handler.SetupHandler;
import de.mcpirat.client.log.Logger;
import de.mcpirat.client.utils.exe.Sender;
import de.mcpirat.client.utils.exe.cmd.Cmd_Logs;
import de.mcpirat.client.utils.exe.cmd.Cmd_Perms;
import de.mcpirat.client.utils.exe.cmd.Cmd_Reload;
import de.mcpirat.client.utils.exe.cmd.Cmd_Send;
import de.mcpirat.client.utils.manager.CommandManager;
import de.mcpirat.client.utils.manager.PermsManager;
import de.mcpirat.client.utils.threads.NewsCheck;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class Main 
{
	/*
	 * Discord Bot Setup (JDA)
	 */
	public static JDABuilder b;
    public static JDA jda;
    
    /*
     * Bot Setups
     */
    public static TextChannel c;
    public static CommandManager cmdManager;
    
    public static boolean isBotSetup = false;
    public static boolean saveLogs = true;
    public static boolean useConsole = false;
    public static boolean allowPrivate = false;
    public static boolean use = false;
    
    public static String cmd_Prefix = "?";
    
    /*
     * From News Check
     */
    public static long lastCheck = 0;
    public static String lastContent = null;
    public static String currentContent = null;
    
    /*
     * Start Method
     * this starts up the Bot
     */
	public static void main(String[] args) throws LoginException, IllegalArgumentException, IOException
	{
		if (!new File("./logs/latest.txt").exists())
		{
			new File("./logs/").mkdir();
			new File("./logs/latest.txt").createNewFile();
		}
		else
		{
			new File("./logs/latest.txt").delete();
			new File("./logs/latest.txt").createNewFile();
		}
		
		Logger.info("Starting MCPirat Discord Bot..");
		Logger.info("Getting Setup Values..");
		
	    boolean isAllAccept = true;
		
		if (args == null
				|| args.length != 4)
		{
			isAllAccept = false;
		}
		
		if (args != null
				&& args.length == 4)
		{
			if (!args[0].equals("-t")
					|| !args[2].equals("-b"))
			{
				isAllAccept = false;
			}
		}
						
		if (!(isAllAccept))
		{
			Logger.err("Usage: java -jar <FILENAME> -t <GEN> -b <VALUE>");
			System.exit(0);
			return;
		}
		
		Logger.info("Setting up Bot..");
		
		PermsManager.init();
		
		b = JDABuilder.createDefault(args[1]);
		
        b.setActivity(Activity.watching("Spielt auf MCPirat.de"));
        b.addEventListeners(new SetupHandler());
        b.addEventListeners(new CommandHandler());
        
        Logger.info("Building Bot..");
        
		jda = b.build();
		c = jda.getTextChannelById(args[3]);
		isBotSetup = true;
		cmdManager = new CommandManager();
		
		new Thread(new NewsCheck()).start();
		
		Logger.info("Register Commands..");
		CommandManager.getCommandManager().getCommand("reload").setExecuter(new Cmd_Reload(), new Main());
		CommandManager.getCommandManager().getCommand("rl").setExecuter(new Cmd_Reload(), new Main());
		CommandManager.getCommandManager().getCommand("send").setExecuter(new Cmd_Send(), new Main());
		CommandManager.getCommandManager().getCommand("perms").setExecuter(new Cmd_Perms(), new Main());
		CommandManager.getCommandManager().getCommand("logs").setExecuter(new Cmd_Logs(), new Main());
		
		Logger.info("Loading config.json");
		loadConfig();
		
		Logger.info("Bot startet as " + jda.getSelfUser().getName() + "#" + jda.getSelfUser().getAsTag() + " [" + args[1] + "]");
		
		use = true;
		loopBot();
	}
	
	public static void loadConfig() throws IOException
	{
		File f = new File("./config.json");
		if (!f.exists())
		{
			f.createNewFile();
			FileWriter def = new FileWriter(f, true);
			def.write("{");
			def.write(System.getProperty("line.separator"));
			def.write("\"PM\":\"false\",");
			def.write(System.getProperty("line.separator"));
			def.write("\"CONSOLE\":\"false\",");
			def.write(System.getProperty("line.separator"));
			def.write("\"SAVE_LOGS\":\"true\",");
			def.write(System.getProperty("line.separator"));
			def.write("\"MAX_LOG_SIZE\":\"120MB\",");
			def.write(System.getProperty("line.separator"));
			def.write("\"COMMAND_PREFIX\":\"?\"");
			def.write(System.getProperty("line.separator"));
			def.write("}");
			def.flush();
			def.close();
		}
		
		String content = new String(Files.readAllBytes(Paths.get(f.toURI())), "UTF-8");

		saveLogs = new JSONObject(content).getBoolean("SAVE_LOGS");
		useConsole = new JSONObject(content).getBoolean("CONSOLE");
		allowPrivate = new JSONObject(content).getBoolean("PM");
		cmd_Prefix = new JSONObject(content).getString("COMMAND_PREFIX");
	}
	
	public static void loopBot() throws IOException
	{
		String replace = System.getProperty("user.name")+"@jda-bot.1.0.1 => ";
		String line = System.console().readLine(replace).replace(replace, "");
		
		if (!useConsole)
		{
			Logger.warn("Console is not allowed!");
			loopBot();
			return;
		}
		
		Logger.info("[CONSOLE] Cmd excute command: /" + line);
		
		if (!CommandManager.getCommandManager().isCommandExists(line))
		{
			Logger.warn("Dieser Command wurde nicht gefunden!");
		}
		else
		{
			CommandManager.getCommandManager().getCommandByCmd(line.split(" ")[0]).execute(Sender.castConsole(System.console()), c, line.split(" "));
		}
		
		loopBot();
	}
}
