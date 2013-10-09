package com.github.lyokofirelyte.WaterCloset.Games.HungerGames;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.lyokofirelyte.WaterCloset.WCMail;
import com.github.lyokofirelyte.WaterCloset.WCMain;

public class CGMain implements CommandExecutor {

	  WCMain plugin;
	  public CGMain(WCMain instance){
	  this.plugin = instance;
	  }
	  
	  List <String> hgHelp = Arrays.asList("&5Collision Games", 
			    "&5| &3/cg &f// Root command & help menu",
				"&5| &3arena <arena> &f// &3Select the arena to use.",
				"&5| &3mode <mode> &f// &3Select the game mode to use.",
				"&5| &3join &f// &3Join the active game.",
				"&5| &3quit &f// &3Resign from the game.",
				"&5| &3start &f// &3Begin the game.",
				"&5| &3stop &f// &3Force-stop the game.",
				"&5| &3arenaAdd <arena> &f// Arena set-up wizard.",
				"&5| &3top &f// &3View highest scores.");
	  
	  List <String> hgArenas = Arrays.asList("&5Arena List",
			  "&5| &3TestArena");
	
	  public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		  
		  if (cmd.getName().equalsIgnoreCase("cg")){
			  
			  Player p = ((Player)sender);
			  
			  if (args.length == 0){		  
				  for (String a : hgHelp){
					  s(p,a);
				  }	  
				  return true;
			  }
			  
			  switch (args[0]){
			  
			  default: 
				  
				  for (String a : hgHelp){
					  s(p,a);
				  }

				  break;
			
			  case "arena":
				  
				  if (args.length == 1){
					  for (String a : hgArenas){
						  s(p,a);
					  }	  
					  break;
				  }
				  
				  
			  }
		  }
		  
		  return true;
	  }
	  
	  public void s(Player p, String s){
		  p.sendMessage(WCMail.AS(WCMail.WC + s));
	  }
	  
	  public void s2(Player p, String s){
		  p.sendMessage(WCMail.AS(s));
	  }
	
}
