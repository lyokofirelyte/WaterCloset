package com.github.lyokofirelyte.WaterCloset;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WCCommands implements CommandExecutor {
  WCMain plugin;
  String WC = "§dWC §5// §d";
  Boolean home;
  Boolean homeSet;

  public WCCommands(WCMain instance){
  
  
    this.plugin = instance;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	  
    if (cmd.getName().equalsIgnoreCase("wc") || cmd.getName().equalsIgnoreCase("watercloset"))
    {
      if (!(sender instanceof Player))
      {
        sender.sendMessage(this.WC + "Sorry console, these commands are for players only.");
        return true;
      }

      if (args.length < 1)
      {
        sender.sendMessage(this.WC + "I'm not sure what you mean. Try /wc help or /wc ?");
        return true;
      }
      
      switch (args[0]){
      
      
      
       case "paragons": case "paragon":
    	   
    	   if (args.length == 1){
    	  
    	  sender.sendMessage(new String[]{
    		    WCMail.AS("&5| &dParagon Information Complex"),
    			WCMail.AS("&5| &f--- ___ --- ___ --- ___ ---"),
    			WCMail.AS("&5| &bCommands&f:"),
    			WCMail.AS("&5| &a/wc rewards &f// &aShows your rewards!"),
    			WCMail.AS("&5| &a/wc tp <player> &f// &aUse a TP token"),
    			WCMail.AS("&5| &a/wc sethome &f// &aSet a special home!"),
    			WCMail.AS("&5| &a/wc home &f// &aTP to your special home!"),
    			WCMail.AS("&5| &a/wc back &f// &aUse a Back token!"),
    			WCMail.AS("&5| &a/wc paragon self &f// &aSee your paragon stats!"),
    			WCMail.AS("&5| &bSee&a ohsototes.com/?p=paragon &bfor full info on how to get and use them!")
    	  });
    	  
    	  break;
    	  
    	   } else {
    		   
    		   int mineral = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.mineral");
    		   int dragon = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.dragon");
    		   int nature = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.nature");
    		   int crystal = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.crystal");
    		   int sun = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.sun");
    		   int hell = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.hell");
    		   int earth = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.earth");
    		   int industrial = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.industrial");
    		   int life = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.life");
    		   int inferno = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.inferno");
    		   int death = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.death");
    		   int total = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.Total");
    		   
    		   sender.sendMessage(new String[]{
    				   
    		   
    				WCMail.AS("&5| &dParagon Donation History"),
    				WCMail.AS("&1| &aTotal &f// &a" + total),
    				WCMail.AS("&1| &bMineral &f// &b" + mineral),
    				WCMail.AS("&1| &bDragon &f// &b" + dragon),
    				WCMail.AS("&1| &bNature &f// &b" + nature),
    				WCMail.AS("&1| &bCrystal &f// &b" + crystal),
    				WCMail.AS("&1| &bDeath &f// &b" + death),
    				WCMail.AS("&1| &bSun &f// &b" + sun),
    				WCMail.AS("&1| &bHell &f// &b" + hell),
    				WCMail.AS("&1| &bEarth &f// &b" + earth),
    				WCMail.AS("&1| &bIndustrial &f// &b" + industrial),
    				WCMail.AS( "&1| &bLife &f// &b" + life),
    				WCMail.AS("&1| &bInferno &f// &b" + inferno )     
    	   });
    		   
    		   break;
    	   }
    		   
       case "rewards": case "purchases":
    	   
    	   List <String> purchases = plugin.datacore.getStringList("Users." + sender.getName() + ".Purchases");
    	   sender.sendMessage(WC + "Paragon Purchases:");
    	   	for (String lampPost : purchases){
    	   		sender.sendMessage(WCMail.AS("&5| &d" + lampPost));
    	   	}
    	   	
    	break;
    	
       case "tp":
    	   
    	   if (args.length != 2){
    		   sender.sendMessage(WC + "Correct usage: /wc tp <player>");
    		   break;
    	   }
    	   
    	   int teleports = plugin.datacore.getInt("Users." + sender.getName() + ".TPTokens");
    	   
    	   if (teleports < 1){
    		   sender.sendMessage(WC + "You don't have enough TP tokens!");
    		   break;
    	   }
    	   
    	   
			teleports = teleports - 1;
			plugin.datacore.set("Users." + sender.getName() + ".TPTokens", teleports);
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + sender.getName() + " " + args[1]);
			break;
			
      case "market":

    	    Boolean market = plugin.datacore.getBoolean("Users." + sender.getName() + ".Market");
    	    
    	    	if (market == false){
    	    		sender.sendMessage(WCMail.WC + "You don't have a market warp!");
    	    		break;
    	    	}
    	    	
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp markkit " + sender.getName());
			break;
			
       case "back":
    	   
    	   int backs = plugin.datacore.getInt("Users." + sender.getName() + ".BackTokens");
    	   
    	   if (backs < 1){
    		   sender.sendMessage(WC + "You don't have enough Back tokens!");
    		   break;
    	   }

    	    backs = backs - 1;
			plugin.datacore.set("Users." + sender.getName() + ".BackTokens", backs);
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + sender.getName() + " add essentials.back");
			Bukkit.getServer().dispatchCommand(sender, "eback");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + sender.getName() + " remove essentials.back");
			break;
			
       case "sethome":
    	   
    	   home = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHome");
    	   homeSet = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHomeSet");
    	   
    	   	if (home == false){
    	   		sender.sendMessage(WC + "You don't have a special home to set!");
    	   		break;
    	   	}
    	   	
    	   	if (homeSet){
    	   		sender.sendMessage(WC + "You have already set your special home!");
    	   		break;
    	   	}
    	   	
    	   	plugin.datacore.set("Users." + sender.getName() + ".SpecialHomeSet", true);
    	   	
    	   	Player p = (Player) sender;
    	   	
    	   	double x = p.getLocation().getBlockX();
    	   	double y = p.getLocation().getBlockY();
    	   	double z = p.getLocation().getBlockZ();
    	   	float yaw = p.getLocation().getYaw();
    		float pitch = p.getLocation().getPitch();
    	   	String w = p.getWorld().getName();
    	   	
    	   	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocX", x);
    	   	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocY", y);
    	   	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocZ", z);
    	   	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocW", w);
    	   	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocYAW", yaw);
    	   	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocP", pitch);
    	   	
    	   	sender.sendMessage(WC + "HOME SET!");
    	   	break;
    	   	
       case "home":
    	   
    	   home = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHome");
    	   homeSet = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHomeSet");
    	   
    	   	if (home == false || homeSet == false){
    	   		sender.sendMessage(WC + "You don't have a special home to go to!");
    	   		break;
    	   	}
    	   	
    	   	double X = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocX");
    		double Y = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocY");
    		double Z = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocZ");
    		float YAW = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocYAW");
    		float PITCH = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocP");
    		World W = Bukkit.getWorld(plugin.datacore.getString("Users." + sender.getName() + ".SpecialHomeLocW"));
    		
    		Location sS = new Location(W, X, Y, Z, YAW, PITCH);
    		Player P = (Player) sender;
    		P.teleport(sS);
    		sender.sendMessage(WC + "Teleported to your special home!");
    		break;
    		

        case "bb":
        	
        	if (sender.hasPermission("wa.staff")){
        		
        		if (plugin.datacore.getBoolean("Users." + sender.getName() + ".ParagonPlaceMode")){
        		plugin.datacore.set("Users." + sender.getName() + ".ParagonPlaceMode", false);
        		sender.sendMessage(WCMail.AS(WC + "BB OFF!"));
        		return true;
        		
        		} else {
        			
        		plugin.datacore.set("Users." + sender.getName() + ".ParagonPlaceMode", true);
            	sender.sendMessage(WCMail.AS(WC + "BB ON!"));
            	return true;
        		}
        	}
      
        break;
        
        case "addobelisk":
        	
        	if (sender.hasPermission("wa.staff")){
        		
        		if (args.length != 3){
        			sender.sendMessage(WC + "Please use /wc addobelisk <name> <type> (Type = default or custom)!");
        			break;
        		}
        		
        		if (!(args[2].equals("default")) && !(args[2].equals("custom"))){
        			sender.sendMessage(WC + "Please use default or custom!");
        			break;
        		}
        		
        		List <String> names = plugin.config.getStringList("Obelisks.Names");
        		
        			if (names.contains(args[1])){
        				sender.sendMessage(WC + "That name already exists!");
        				break;
        			}
        			
        		plugin.datacore.set("Users." + sender.getName() + ".ObeliskPlaceMode", true);
        		names.add(args[1]);
        		plugin.config.set("Obelisks.Names", names);
        		plugin.datacore.set("Obelisks.Latest", args[1]);
        		plugin.datacore.set("Obelisks.LatestType", args[2]);
        		sender.sendMessage(WCMail.AS(WC + "Place a GLOWSTONE into the spot where the landing location will be set."));
        		return true;
        	}
      
        break;
        
        case "remobelisk":
        	
        	if (sender.hasPermission("wa.staff")){
        		
        		if (args.length != 2){
        			sender.sendMessage(WC + "Please use /wc remobelisk <name>!");
        			break;
        		}
        		
        		List <String> names = plugin.config.getStringList("Obelisks.Names");
        		
        			if (!names.contains(args[1])){
        				sender.sendMessage(WC + "That name isn't on the list!");
        				break;
        			}

        		names.remove(args[1]);
        		plugin.config.set("Obelisks.Names", names);
        		sender.sendMessage(WC + "Obelisk removed.");
        		return true;
        	}
      
        break;
        
        case "bp":
        	
        	if (sender.hasPermission("wa.staff")){
        		
        		if (plugin.datacore.getBoolean("Users." + sender.getName() + ".ParagonBreakMode")){
        		plugin.datacore.set("Users." + sender.getName() + ".ParagonBreakMode", false);
        		sender.sendMessage(WCMail.AS(WC + "BP OFF!"));
        		return true;
        		
        		} else {
        			
        		plugin.datacore.set("Users." + sender.getName() + ".ParagonBreakMode", true);
            	sender.sendMessage(WCMail.AS(WC + "BP ON!"));
            	return true;
        		}
        	}
      
        break;
        
        case "placeholders":
        	
        	sender.sendMessage(WC + "%t = town, %c = coords, %p = paragons");
        	break;
        	
      	case "reload":
      		
      		if (sender.hasPermission("wa.staff")){
      		plugin.loadYamls();
      		plugin.saveYamls();
      		sender.sendMessage(WC + "Reloaded WC config.");
      		break;
      		} else {
      			sender.sendMessage(WC + "NO NO, YOU NO HAVE PERMISSIONS.");
      			break;
      		}
      	case "save":
      		
      	if (sender.hasPermission("wa.staff")){
      		plugin.saveYamls();
      		sender.sendMessage(WC + "Saved WC config.");
      		break;
      	} else {
			sender.sendMessage(WC + "NO NO, YOU NO HAVE PERMISSIONS.");
			break;
		}
      		
      	case "backup":
      		
      		Boolean ok = plugin.config.getBoolean("OK");
      		
      		if (ok == false || ok == null){
      			sender.sendMessage("The main config is corrupted for some reason! Backup aborted! Contact Hugs!");
      			break;
      		}
      		
          	if (sender.hasPermission("wa.staff")){
          		plugin.backupYamls();
          		sender.sendMessage(WC + "Backup saved!");
          		break;
          	} else {
    			sender.sendMessage(WC + "NO NO, YOU NO HAVE PERMISSIONS.");
    			break;
    		}
          	
      	case "?": case "help": default:
      		
      	List <String> help = plugin.config.getStringList("Core.Help");
      	
      		for (String message : help){
      			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
      		}
      	break;
      }
    }
    return true;
  }
}
  