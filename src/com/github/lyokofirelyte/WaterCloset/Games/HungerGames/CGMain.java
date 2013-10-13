package com.github.lyokofirelyte.WaterCloset.Games.HungerGames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
//				"&5| &3mode <mode> &f// &3Select the game mode to use.",
				"&5| &3join &f// &3Join the active game.",
				"&5| &3quit &f// &3Resign from the game.",
				"&5| &3start &f// &3Begin the game.",
				"&5| &3stop &f// &3Force-stop the game.",
				"&5| &3add <arena> &f// &3Arena set-up wizard.",
				"&5| &3top &f// &3View highest scores.");
	  

	
	  public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		  
		  if (cmd.getName().equalsIgnoreCase("cg")){
			  
			  List <String> hgArenas = plugin.WAGamesdatacore.getStringList("HG.Arenas");
			  
			  Player p = ((Player)sender);
			  
			  if (args.length == 0){		  
				  for (String a : hgHelp){
					  s2(p,a);
				  }	  
				  return true;
			  }
			  
			  switch (args[0].toLowerCase()){
			  
			  default: 
				  
				  for (String a : hgHelp){
					  s2(p,a);
				  }

				  break;
			
			  case "arena":
				  
				  if (!p.hasPermission("wa.staff")){
					  s(p, "You can't use that!");
					  break;
				  }
				  
				  hgArenas = plugin.WAGamesdatacore.getStringList("HG.Arenas");
				  
				  if (args.length == 1 || !hgArenas.contains(args[1])){
					  for (String a : hgArenas){
						  s2(p,a);
					  }	  
					  break;
				  }
				  
				  if (args.length == 2){
					  Boolean inProgress = plugin.WAGamesdatacore.getBoolean("HG.Started");
					  if (!inProgress){
						  plugin.WAGamesdatacore.set("HG.ActiveArena", args[1]);
						  s(p, "Arena changed.");
						  break;
					  } else {
						  s(p, "A game is currently in progress!");
						  break;
					  }
				  } else {
					  s(p, "/cg arena <arena>");
					  break;
				  }
				  
			  case "add":
				  
				  if (!p.hasPermission("wa.staff")){
					  s(p, "You can't use that!");
					  break;
				  }
				  
				  if (args.length != 2){
					  s(p, "/cg <add> arenaName");
					  break;
				  }
				  
				  hgArenas = plugin.WAGamesdatacore.getStringList("HG.Arenas");
				  
				  if (hgArenas.contains(args[1])){
					  s(p, "That arena already exists.");
					  break;
				  }
				  
				  if (!p.getItemInHand().getType().equals(Material.AIR)){
					  s(p, "You should have your hands free for this.");
					  break;
				  }
				  
				  plugin.WAGamesdatacore.set("HG.CurrentArena", args[1]);
				  hgArenas.add(args[1]);
				  plugin.WAGamesdatacore.set("HG.Arenas", hgArenas);
				  plugin.userWriteB(p.getName(), "HG.AddingPoints", true);
				  s(p, "Arena Wizard Activated. Stand on each warp-in point facing the direction you want them to.");
				  s2(p, "Right click in the air with your tool to set that point.");
				  s2(p, "&dWhen you're done, type /cg #done. If you want to reset the points, type /cg #reset.");
				  s2(p, "&dIf you want to just forget everything, type /cg #quit.");
				  giveArenaTool(p);	  
				  break;
				  
			  case "#reset": case "#reset.":

				  if (!p.hasPermission("wa.staff")){
					  s(p, "You can't use that!");
					  break;
				  }
				  
				  if (!plugin.userGrabB(p.getName(), "HG.AddingPoints")){
					  s(p, "You're not ready to use this command yet.");
					  break;
				  }
				  
				  plugin.WAGamesconfig.set("HG." + plugin.WAGamesdatacore.getString("HG.CurrentArena") + ".Locations", null);
				  s(p, "&cReset all locations!");
				  break;
				  
			  case "#quit": case "#quit.":

				  if (!p.hasPermission("wa.staff")){
					  s(p, "You can't use that!");
					  break;
				  }
				  
				  if (!plugin.userGrabB(p.getName(), "HG.AddingPoints") && !plugin.userGrabB(p.getName(), "HG.ChoosingArena")){
					  s(p, "You're not ready to use this command yet.");
					  break;
				  }
				  
				  if (!p.getItemInHand().getType().equals(Material.COMMAND) && !p.getItemInHand().hasItemMeta()){
					  s(p, "You should hold your tool in your hand first.");
					  break;
				  }
				  
			  	  p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));			  
			  	  hgArenas = plugin.WAGamesdatacore.getStringList("HG.Arenas");
			  	  hgArenas.remove(plugin.WAGamesdatacore.getString("HG.CurrentArena"));
			  	  plugin.WAGamesdatacore.set("HG.Arenas", hgArenas);
				  plugin.WAGamesconfig.set("HG." + plugin.WAGamesdatacore.getString("HG.CurrentArena"), null);
				  plugin.userWriteB(p.getName(), "HG.ChoosingArena", null);
				  plugin.userWriteB(p.getName(), "HG.AddingPoints", null);
				  s(p, "&cAborted.");
				  break;
				  
			  case "#done": case "#done.":
				  
				  if (!p.hasPermission("wa.staff")){
					  s(p, "You can't use that!");
					  break;
				  }
				  
				  if (!plugin.userGrabB(p.getName(), "HG.AddingPoints") && !plugin.userGrabB(p.getName(), "HG.ChoosingArena")){
					  s(p, "You're not ready to use this command yet.");
					  break;
				  }
				  
				  if (plugin.userGrabB(p.getName(), "HG.AddingPoints")){
					  plugin.userWriteB(p.getName(), "HG.AddingPoints", null);
					  plugin.userWriteB(p.getName(), "HG.ChoosingArena", true);
					  int points = plugin.WAGamesconfig.getInt("HG." + plugin.WAGamesdatacore.getString("HG.CurrentArena") + ".Points");
					  s(p, "Stopped with " + points + " &dpoints.");
					  s2(p, "&dWhat type of arena is this? Right click in the air with your tool.");
					  s2(p, "&dType /cg #done when it lands on the arena type you want.");
					  break;
				  }
				  
				  
				  if (plugin.userGrabB(p.getName(), "HG.ChoosingArena")){
					  if (!p.getItemInHand().getType().equals(Material.COMMAND) && !p.getItemInHand().hasItemMeta()){
						  s(p, "You should hold your tool in your hand first.");
						  break;
					  }
				  	  s(p, "We're all done!");
				  	  p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
				  	  plugin.userWriteB(p.getName(), "HG.ChoosingArena", null);
				  	  break;
				  }
			  }

			  
			  
		  }
		  
		  return true;
	  }
	  
	  public static void s(Player p, String s){
		  p.sendMessage(WCMail.AS(WCMail.WC + s));
	  }
	  
	  public static void s2(Player p, String s){
		  p.sendMessage(WCMail.AS(s));
	  }
	  
	@SuppressWarnings("deprecation")
	public void giveArenaTool(Player p){
		    ArrayList<String> lore;
		    ItemStack token = new ItemStack(Material.COMMAND, 1);
	        ItemMeta name = token.getItemMeta();
	        lore = new ArrayList<String>();
	        name.addEnchant(Enchantment.DURABILITY, 10, true);
	        name.setDisplayName("§b§o§lARENA WIZARD TOOL");
	        lore.add("§7§oCG @ WaterCloset");
	        name.setLore(lore);
	        token.setItemMeta((ItemMeta)name);
	        p.getInventory().setItemInHand(token);
	        p.updateInventory();
	  }
	
}
