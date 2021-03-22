package de.mcpirat.client.utils.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

import net.dv8tion.jda.api.entities.User;

/* MCPirat Discord Bot
 * Develped by CyZe TLC
 * 
 * v0.1 BETA
 */
public class PermsManager 
{
	private static File f = new File("perms.json");
	
	public static void init() throws IOException
	{
		if (!f.exists())
		{
			f.createNewFile();
			FileWriter def = new FileWriter(f, true);
			def.write("{");
			def.write(System.getProperty("line.separator"));
			def.write("   \"Perms\": [");
			def.write(System.getProperty("line.separator"));
			def.write("      {");
			def.write(System.getProperty("line.separator"));
			def.write("         \"USER_ID\":\"516929484469829632\"");
			def.write(System.getProperty("line.separator"));
			def.write("      }");
			def.write(System.getProperty("line.separator"));
			def.write("    ]  ");
			def.write(System.getProperty("line.separator"));
			def.write("}");
			def.flush();
			def.close();
		}
	}
	
	public static int getUser(String id)
	{
		try 
		{
			String content = new String(Files.readAllBytes(Paths.get(f.toURI())), "UTF-8");
			JSONObject obj = new JSONObject(content);
			JSONArray all = obj.getJSONArray("Perms");
			
			int i = -1;
			
			for (int c = 0; c < all.length(); c++)
			{
				if (all.getJSONObject(c).getString("USER_ID").equals(id))
				{
					i = c;
					break;
				}
			}
			return i;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return -1;
	}
	
	public static boolean isPlayerExists(String id)
	{
		return getUser(id) != -1;
	}
	
	public static boolean hasPlayerPerm(User p, String str)
	{
		boolean v = false;
		try 
		{
			String content = new String(Files.readAllBytes(Paths.get(f.toURI())), "UTF-8");
			JSONObject obj = new JSONObject(content);
			JSONArray all = obj.getJSONArray("Perms");
			
			if (!isPlayerExists(p.getId()))
			{
				v = false;
			}
			else
			{
				v = all.getJSONObject(getUser(p.getId())).getBoolean(str);
			}
		}
		catch (Exception e) 
		{
			v = hasPlayerPerm(p, str);
		}
		return v;
	}
	
	public static int getNewPlayerID()
	{
		try 
		{
			String content = new String(Files.readAllBytes(Paths.get(f.toURI())), "UTF-8");
			JSONObject obj = new JSONObject(content);
			JSONArray all = obj.getJSONArray("Perms");

			return all.length()+1;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void addPerm(User p, String str)
	{
		try
		{
			String content = new String(Files.readAllBytes(Paths.get(f.toURI())), "UTF-8");
			JSONObject obj = new JSONObject(content);
			JSONArray all = obj.getJSONArray("Perms");
			if (isPlayerExists(p.getId()))
			{
				JSONObject u = all.getJSONObject(getUser(p.getId()));
				if (!hasPlayerPerm(p, str))
				{
					u.put(str, true);
					
					f.delete();
					f.createNewFile();
					
					FileWriter def = new FileWriter(f, true);
					def.write(obj.toString());
					def.flush();
					def.close();
				}
			}
			else
			{
				createPlayer(p);
				addPerm(p, str);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void removePerm(User p, String str)
	{
		try
		{
			String content = new String(Files.readAllBytes(Paths.get(f.toURI())), "UTF-8");
			JSONObject obj = new JSONObject(content);
			JSONArray all = obj.getJSONArray("Perms");
			if (isPlayerExists(p.getId()))
			{
				JSONObject u = all.getJSONObject(getUser(p.getId()));
				if (hasPlayerPerm(p, str))
				{
					u.put(str, false);
					
					f.delete();
					f.createNewFile();
					
					FileWriter def = new FileWriter(f, true);
					def.write(obj.toString());
					def.flush();
					def.close();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static void createPlayer(User p)
	{
		try
		{
			String content = new String(Files.readAllBytes(Paths.get(f.toURI())), "UTF-8");
			JSONObject obj = new JSONObject(content);
			JSONArray all = obj.getJSONArray("Perms");
			if (!isPlayerExists(p.getId()))
			{
				JSONObject player = new JSONObject();
				player.put("USER_ID", String.valueOf(p.getId()));

				all.put(getNewPlayerID(), player.toString());
				
				f.delete();
				f.createNewFile();
				
				FileWriter def = new FileWriter(f, true);
				def.write(obj.toString());
				def.flush();
				def.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
