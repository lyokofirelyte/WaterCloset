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
static List <String> AdministratumHelpGlobal = WCMain.help.getStringList("Help.Administratum");

// WC HELP
static List <String> WCHelpMail = WCMain.help.getStringList("WC.Mail");



	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		if (cmd.getName().equalsIgnoreCase("search")){
			
			if (args.length == 0){
				sender.sendMessage(WC + "Type /search <query>.");
				return true;
			}
			
			int x = 0;
			
			for (String search : AdministratumHelpGlobal){
				if (search.toLowerCase().contains(args[0].toLowerCase())){
					sender.sendMessage(WCMail.AS(search.replaceAll(args[0], "&6" + args[0] + "&3")));
					x++;
				}
			}
			
			for (String search : WCHelpMail){
				if (search.toLowerCase().contains(args[0].toLowerCase())){
					sender.sendMessage(WCMail.AS(search.replaceAll(args[0], "&6" + args[0] + "&3")));
					x++;
				}
			}
			
			
			sender.sendMessage("§7§o" + x + " §7§oresult(s) found.");
		}
	

		return true;
	}
}
