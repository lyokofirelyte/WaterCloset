package com.github.lyokofirelyte.WaterCloset;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WCReboot implements CommandExecutor {

	WCMain plugin;
	public WCReboot(WCMain instance){
	this.plugin = instance;
	}
	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		  
		  if (cmd.getName().equals("reboot")){
			  
			  	if (WCMain.help.getBoolean("REBOOTING")){
			  		sender.sendMessage(WCMail.WC + "We're already rebooting you twat.");
			  		return true;
			  	}
			  			
			  plugin.wcReboot();
		  }
		  
		  return true;
	  }

}
