package com.github.lyokofirelyte.WaterCloset;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WCHelp implements CommandExecutor {
	
	String WC = "§dWC §5// §d";
	
	WCMain plugin;
	public WCHelp(WCMain instance){
	plugin = instance;
	}

// ADMINISTRATUM HELP
static List <String> AdministratumHelpGlobal = WCMain.help.getStringList("Help.Administratum.Global");
static List <String> AdministratumHelpSelection = WCMain.help.getStringList("Help.Administratum.Selection");
static List <String> AdministratumHelpActions = WCMain.help.getStringList("Help.Administratum.Actions");
static List <String> AdministratumHelpFilters = WCMain.help.getStringList("Help.Administratum.Filters");
static List <String> AdministratumHelpChannels = WCMain.help.getStringList("Help.Administratum.Channels");
static List <String> AdministratumHelpWatchList = WCMain.help.getStringList("Help.Administratum.WatchList");
static List <String> AdministratumHelpEvents = WCMain.help.getStringList("Help.Administratum.Events");
static List <String> AdministratumHelpPlugin = WCMain.help.getStringList("Help.Administratum.Plugin");

// WC HELP
static List <String> WCHelpMail = WCMain.help.getStringList("Help.WC.Mail");



	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		if (cmd.getName().equalsIgnoreCase("search")){
			
			if (args.length == 0){
				sender.sendMessage(WC + "Type /search <query>.");
				return true;
			}
			
			int x = 0;
			
			for (String search : AdministratumHelpGlobal){
				if (search.toLowerCase().contains(args[0].toLowerCase())){
					sender.sendMessage(search.replaceAll(args[0], "&6" + args[0]));
					x++;
				}
			}
			
			sender.sendMessage("§7§o" + x + " §7§oresults found.");
		}
	

		return true;
	}
}
