package com.github.lyokofirelyte.WaterCloset.Extras;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.github.lyokofirelyte.WaterCloset.WCMail;
import com.github.lyokofirelyte.WaterCloset.WCMain;

public class Overwatch implements Listener, CommandExecutor {
	
	  WCMain plugin;
	  public Overwatch(WCMain instance){
	  this.plugin = instance;
	  }
	  
	  String ovN = "&7ov.notify &f// &7";
	  String ovM = "&7ov.menu &f// &7";
	  
	  @EventHandler (priority = EventPriority.MONITOR)
	  public void onBlockpLace(BlockPlaceEvent e){
		  
		  if (e.isCancelled()){
			  return;
		  }
		  
		  Player p = e.getPlayer();
		  String n = p.getName();
		  Boolean onProtect = plugin.datacore.getBoolean("Users." + n + ".Overwatch.ProtectMode");
		  
		  if (onProtect && e.getBlock().getWorld().getName().equals("world")){
			  double x = e.getBlock().getX();
			  double y = e.getBlock().getY();
			  double z = e.getBlock().getZ();
			  String block = "ovbl " + x + "," + y + "," + z;
			  plugin.datacore.set("Overwatch.Blocks." + block + ".Owner", n);
		  }
	  }
	  
	  @EventHandler (priority = EventPriority.HIGHEST)
	  public void onBlockBreak(BlockBreakEvent e){
		  
		  if (e.isCancelled()){
			  return;
		  }
		  
		  double x = e.getBlock().getX();
		  double y = e.getBlock().getY();
		  double z = e.getBlock().getZ();
		  String block = "ovbl " + x + "," + y + "," + z;
		  String owner = plugin.datacore.getString("Overwatch.Blocks." + block + ".Owner");
		  
		  	if (owner != null && e.getPlayer().hasPermission("wa.staff") == false && !owner.equals(e.getPlayer().getName())){
		  		
		  		List <String> allowedUsers = plugin.datacore.getStringList("Users." + owner + ".Overwatch.AllowedUsers");
		  		Boolean mode = plugin.datacore.getBoolean("Users." + owner + ".Overwatch.Alliance");
		  		
		  			if (mode){
		  				String alliance = plugin.WAAlliancesconfig.getString("Users." + owner + ".Alliance");
		  				String myAlliance = plugin.WAAlliancesconfig.getString("Users." + e.getPlayer().getName() + ".Alliance");
		  					if (alliance.equals(myAlliance)){
		  						return;
		  					}
		  			}
		  		
		  		if (allowedUsers.contains(e.getPlayer().getName()) == false){
		  			e.setCancelled(true);
		  			e.getPlayer().sendMessage(WCMail.AS(ovN + "Action cancelled. This block is owned by " + owner + "&7."));
		  			return;
		  		}
		  		
		  	} 
		  	
		  	if (owner != null && owner.equals(e.getPlayer().getName())){
		  		plugin.datacore.set("Overwatch.Blocks." + block + ".Owner", null);
		  	}
		  
	  }
	  
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		  if (cmd.getName().equals("ov")){
			  
			  if (args.length <= 0){
				  sender.sendMessage(new String[]{
				  WCMail.AS(ovM + "&lOVERWATCH HELP VIEW COMPLEX ლ(ಠ益ಠლ)"),
				  WCMail.AS("&7/ov + <player> &f// &7Add someone to your block-break list."),
				  WCMail.AS("&7/ov - <player> &f// &7Delete someone from your block-break list."),
				  WCMail.AS("&7/ov v &f// &7View allowed players that you've added."),
				  WCMail.AS("&7/ov m &f// &7Toggle your place mode to and from protection / free."),
				  WCMail.AS("&7/ov a &f// &7Allow your alliance to break your blocks. (Toggle)"),
				  WCMail.AS("&7&oOverwatch v3.0. Please report any bugs to my experimental system by mailing Hugs!")
				  });
				  
				  return true;
			  }
			  
			  switch (args[0]){
			  
			  default: 

			      sender.sendMessage(new String[]{
				  WCMail.AS(ovM + "&lOVERWATCH HELP VIEW COMPLEX ლ(ಠ益ಠლ)"),
				  WCMail.AS("&7/ov + <player> &f// &7Add someone to your block-break list."),
				  WCMail.AS("&7/ov - <player> &f// &7Delete someone from your block-break list."),
				  WCMail.AS("&7/ov v &f// &7View allowed players that you've added."),
				  WCMail.AS("&7/ov m &f// &7Toggle your place mode to and from protection / free."),
				  WCMail.AS("&7/ov a &f// &7Allow your alliance to break your blocks. (Toggle)"),
				  WCMail.AS("&7&oOverwatch v3.0. Please report any bugs to my experimental system by mailing Hugs!")
						  });
				  break;
				  
			  case "v":
				  
				  List <String> allowedUsers = plugin.datacore.getStringList("Users." + sender.getName() + ".Overwatch.AllowedUsers");
				  Boolean mode1 = plugin.datacore.getBoolean("Users." + sender.getName() + ".Overwatch.ProtectMode");
				  Boolean mode2 = plugin.datacore.getBoolean("Users." + sender.getName() + ".Overwatch.Alliance");
				  
				  sender.sendMessage(WCMail.AS(ovM + "Overwatch Status"));
				  sender.sendMessage(WCMail.AS("&7&oProtect Mode: ") + mode1);
				  sender.sendMessage(WCMail.AS("&7&oAlliance Mode: ") + mode2);
				  
				  if (allowedUsers.size() == 0){
					  sender.sendMessage(WCMail.AS(ovN + "No users found."));
					  break;
				  }
				  
				  sender.sendMessage(WCMail.AS("&7&oAllowed Users:"));
				  sender.sendMessage(WCMail.AS("&a" + allowedUsers));
				  break;
				  
			  case "m":
				  
				  Boolean mode = plugin.datacore.getBoolean("Users." + sender.getName() + ".Overwatch.ProtectMode");
				  	if (mode){
				  		plugin.datacore.set("Users." + sender.getName() + ".Overwatch.ProtectMode", false);
				  		sender.sendMessage(WCMail.AS(ovN + "Protect mode disabled. All blocks you place will now be unprotected."));
				  	} else {
				  		plugin.datacore.set("Users." + sender.getName() + ".Overwatch.ProtectMode", true);
				  		sender.sendMessage(WCMail.AS(ovN + "Protect mode enabled. All blocks you place will now be protected."));
				  	}
				  break;
				  
			  case "a":
				  
				  Boolean inAlliance = plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".InAlliance");
				  	if (inAlliance == false){
				  		sender.sendMessage(WCMail.AS(ovN + "You're not in an alliance!"));
				  		break;
				  	}
				  mode = plugin.datacore.getBoolean("Users." + sender.getName() + ".Overwatch.Alliance");
				  	if (mode){
				  		plugin.datacore.set("Users." + sender.getName() + ".Overwatch.Alliance", false);
				  		sender.sendMessage(WCMail.AS(ovN + "Alliance mode disabled."));
				  	} else {
				  		plugin.datacore.set("Users." + sender.getName() + ".Overwatch.Alliance", true);
				  		sender.sendMessage(WCMail.AS(ovN + "Alliance mode enabled."));
				  	}
				  break;
				  
			  case "+":
				  	
				  if (args.length != 2){
					  sender.sendMessage(WCMail.AS(ovN + "/ov + <player>."));
					  break;
				  }
				  
				  if (Bukkit.getOfflinePlayer(args[1]).hasPlayedBefore() == false){
					  sender.sendMessage(WCMail.AS(ovN + "That player was not found in the database. Try their full name or another person."));
					  break;
				  }
				  
				  allowedUsers = plugin.datacore.getStringList("Users." + sender.getName() + ".Overwatch.AllowedUsers");
				  
				  if (allowedUsers.contains(args[1])){
					  sender.sendMessage(WCMail.AS(ovN + "You've already added that person."));
					  break;
				  }
				  
				  allowedUsers.add(args[1]);
				  plugin.datacore.set("Users." + sender.getName() + ".Overwatch.AllowedUsers", allowedUsers);
				  sender.sendMessage(WCMail.AS(ovN + "Successfully added " + args[1] + " &7to your block-place allow list."));
				  break;
				  
			  case "-":
				  	
				  if (args.length != 2){
					  sender.sendMessage(WCMail.AS(ovN + "/ov - <player>."));
					  break;
				  }
			  
				  allowedUsers = plugin.datacore.getStringList("Users." + sender.getName() + ".Overwatch.AllowedUsers");
				  
				  if (allowedUsers.contains(args[1]) == false){
					  sender.sendMessage(WCMail.AS(ovN + "That person isn't on your list."));
					  break;
				  }
				  
				  allowedUsers.remove(args[1]);
				  plugin.datacore.set("Users." + sender.getName() + ".Overwatch.AllowedUsers", allowedUsers);
				  sender.sendMessage(WCMail.AS(ovN + "Successfully remove " + args[1] + " &7from your block-place allow list."));
				  break;
			  }
			  
		  }
		  
		  
		  return true;
		  
		  
	  }
}
