package com.github.lyokofirelyte.WaterCloset.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.lyokofirelyte.WaterCloset.WCMain;
import com.github.lyokofirelyte.WaterCloset.Alliances.WACommandEx;

public class WCBack implements CommandExecutor {
	
	WCMain plugin;
	public WCBack(WCMain instance){
	plugin = instance;
	}
	
	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		  
		  if (cmd.getName().equalsIgnoreCase("back") || cmd.getName().equalsIgnoreCase("bk")){
			  
			  Player p = ((Player)sender);
			  
			  if (args.length == 0 || args[0].equals("0")){
				  backTp(p, 0);
				  return true;
			  }
			  
			  if (!WACommandEx.isInteger(args[0]) || Integer.parseInt(args[0]) > 6 || Integer.parseInt(args[0]) < 0) {
				  WCMain.s(p, "Invaild history. Type /back [1-6]");
				  return true;
			  }
			  
			  backTp(p, Integer.parseInt(args[0]));  
		  }
		  
		return true;
	}

	private void backTp(Player p, int a) {
		
		  List <String> history = plugin.userGrabSL(p.getName(), "LastLoc.History");
		  	if (a+1 > history.size()){
		  		WCMain.s(p, "You don't have that many points in history!");
		  		return;
		  	}
		  String back = history.get(a);
		  String[] splitB = back.split(",");
		  
		  double x = Double.parseDouble(splitB[0]);
		  double y = Double.parseDouble(splitB[1]);
		  double z = Double.parseDouble(splitB[2]);
		  float yaw = Float.parseFloat(splitB[3]);
		  float pitch = Float.parseFloat(splitB[4]);
		  World world = Bukkit.getWorld(splitB[5]);
		  
		  Location l = new Location(world, x, y, z, yaw, pitch);
		  p.teleport(l);
		  WCMain.s(p, "Arrived at history location!");
		
	}
}
