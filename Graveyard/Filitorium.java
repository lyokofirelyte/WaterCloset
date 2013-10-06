package com.github.lyokofirelyte.WaterCloset.Extras;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.lyokofirelyte.WaterCloset.WCMail;
import com.github.lyokofirelyte.WaterCloset.WCMain;


public class Filitorium {

	static WCMain plugin;
	public Filitorium(WCMain instance){
	plugin = instance;
    }
	
	static FileConfiguration loadedFile = new YamlConfiguration();
	static File fileToCheck;
	
	public static void userCreate(String user) throws NullPointerException {
		
    	try {	
		fileToCheck = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}
		
	    try {
	
		    if (!fileToCheck.exists()) {
		    	fileToCheck.getParentFile().mkdirs();
		    	plugin.copy(plugin.getResource(user + ".yml"), fileToCheck);
		    	userLogUpdate(user);
		    	plugin.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&aNEW USER FILE CREATED FOR " + user + "&c!"));
		    	
		    }
		    
	    } catch (Exception e) {
	        e.printStackTrace();
	    	Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE CREATING FILE FOR " + user + "&c!"));
	    }

	}
	
  
	  public static void userWriteS(String user, String path, String data){
		  
		  File userFile = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
			
			try {
				  loadedFile.load(userFile);
			} catch (Exception e) {
				  e.printStackTrace();
				}
			
		  loadedFile.set(path, data);
		  
		  try {
			loadedFile.save(userFile);
		  } catch (IOException e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE WRITING FILE FOR " + user + "!"));
		      }
		  
	  }
	  

	  public void userWriteI(String user, String path, int data){
		  
		  File userFile = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
			
			try {
				  loadedFile.load(userFile);
			} catch (Exception e) {
				  e.printStackTrace();
				}
			
		  loadedFile.set(path, data);
		  
		  try {
			loadedFile.save(userFile);
		  } catch (IOException e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE WRITING FILE FOR " + user + "!"));
		      }
		  
	  }
	  
	  public void userWriteB(String user, String path, Boolean data){
		  
		  File userFile = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
			
			try {
				  loadedFile.load(userFile);
			} catch (Exception e) {
				  e.printStackTrace();
				}
			
		  loadedFile.set(path, data);
		  
		  try {
			loadedFile.save(userFile);
		  } catch (IOException e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE WRITING FILE FOR " + user + "!"));
		      }
		  
	  }
	  
	  public Boolean userGrabB(String user, String path){
		  
		  File userFile = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getBoolean(path);
	  }
	  
	  public static String userGrabS(String user, String path){
		  
		  File userFile = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getString(path);
	  }
	  
	  public int userGrabI(String user, String path){
		  
		  File userFile = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getInt(path);
	  }
	  
	  public List <String> userGrabSL(String user, String path){
		  
		  File userFile = new File(plugin.getDataFolder() + File.separator + "Users", user + ".yml");
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getStringList(path);
	  }
	  
	  public static void userLogUpdate(String user){
		  List <String> users = plugin.datacore.getStringList("FileSystem.Users");
		  users.add(user);
		  plugin.datacore.set("FileSystem.Users", users);
	  }
	
}
