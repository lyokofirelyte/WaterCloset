package com.github.lyokofirelyte.WaterCloset;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WCCommands implements CommandExecutor {
  WCMain plugin;
  String WC = "§dWC §5// §d";

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
           
      
        case "placeholders":
        	
        	sender.sendMessage(WC + "%t = town, %c = coords");
        	break;
        	
      	case "reload":
      		
      		plugin.loadYamls();
      		plugin.saveYamls();
      		sender.sendMessage(WC + "Reloaded WC config.");
      		break;
      		
      	case "save":
      		
      		plugin.saveYamls();
      		sender.sendMessage(WC + "Saved WC config.");
      		break;
      		
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
  