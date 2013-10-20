package com.github.lyokofirelyte.WaterCloset;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.github.lyokofirelyte.WaterCloset.Alliances.WACommandEx;

public class WCWarps implements CommandExecutor {
	
	WCMain plugin;
	public WCWarps(WCMain instance){
	plugin = instance;
	}

	
	  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		  
		  if (cmd.getName().equalsIgnoreCase("remwarp") || cmd.getName().equalsIgnoreCase("delwarp")){
			  
			  Player p = ((Player)sender);
			  
			  if (args.length == 0){
				  WCMain.s(p, "Try /remwarp <name>.");
				  return true;
			  }
			  
			  File warpFile = new File(plugin.getDataFolder() + File.separator + "Warps", args[0].toLowerCase() + ".yml");
			  
			  if (!warpFile.exists()) {
				  WCMain.s(p, "That warp does not exist!");
				  return true;
			  }
			  
			  warpFile.delete();
			  
			  String path  = plugin.getDataFolder() + "/Warps/";
              File folder = new File(path);
              String[] fileNames = folder.list();
              
			  WCMain.s(p, "Warp removed. There are now &6" + fileNames.length + " &dwarps.");
		  }
		  
		  if (cmd.getName().equalsIgnoreCase("setwarp")){
			  
			  Player p = ((Player)sender);
			  
			  if (args.length == 0){
				  WCMain.s(p, "Try /setwarp <name>.");
				  return true;
			  }
			  
			  YamlConfiguration warpLoad = new YamlConfiguration();
			  File warpFile = new File(plugin.getDataFolder() + File.separator + "Warps", args[0].toLowerCase() + ".yml");
			  
			  if (!warpFile.exists()) {
				  warpFile.getParentFile().mkdirs();
			      plugin.copy(plugin.getResource(args[0].toLowerCase() + ".yml"), warpFile);
			  }
		      
		      try {
		    	  warpLoad.load(warpFile);
		      } catch (Exception e) {
				  e.printStackTrace();
				  }
		      
		      warpLoad.set("world", p.getWorld().getName());
		      warpLoad.set("x", p.getLocation().getX());
		      warpLoad.set("y", p.getLocation().getY());
		      warpLoad.set("z", p.getLocation().getZ());
		      warpLoad.set("yaw", p.getLocation().getYaw());
		      warpLoad.set("pitch", p.getLocation().getPitch());
		      
		      try {
				warpLoad.save(warpFile);
		      } catch (IOException e) {
				e.printStackTrace();
		      	}
		      
		      String path  = plugin.getDataFolder() + "/Warps/";
              File folder = new File(path);
              String[] fileNames = folder.list();
		      
		      WCMain.s(p, "Set warp &6" + args[0].toLowerCase() + "&d. There are now &6" + fileNames.length + " &dwarps.");

		  }
		  
		  if (cmd.getName().equalsIgnoreCase("warp") || cmd.getName().equalsIgnoreCase("w")){
			  
			  Player p = ((Player)sender);
			  
			  if (args.length == 0){
				    String path  = plugin.getDataFolder() + "/Warps/";
	                File folder = new File(path);
	                String[] fileNames = folder.list();
	                Arrays.sort(fileNames);
	      			SortedMap<Integer, String> map = new TreeMap<Integer, String>();
	                
	                for(int i = 0; i < fileNames.length; i++){
	    			  map.put(i, fileNames[i]);
	                }
	                
	                paginate(sender, map, 1, 20, fileNames.length);
	                return true;
			  }
			  
			  if (WACommandEx.isInteger(args[0])){
				    String path  = plugin.getDataFolder() + "/Warps/";
	                File folder = new File(path);
	                String[] fileNames = folder.list();
	                Arrays.sort(fileNames);
	      			SortedMap<Integer, String> map = new TreeMap<Integer, String>();
	                
	                for(int i = 0; i < fileNames.length; i++){
	    			  map.put(i, fileNames[i]);
	                }
	                
	                if (Integer.parseInt(args[0]) > Math.round((double) (fileNames.length / 20))){
	                	WCMain.s(p, "There aren't that many pages!");
	                	return true;
	                }
	                
	                paginate(sender, map, Integer.parseInt(args[0]), 20, fileNames.length);
	                return true;
			  }
			  
				YamlConfiguration warpLoad = new YamlConfiguration();
				File fileToCheck = new File(plugin.getDataFolder() + File.separator + "Warps", args[0] + ".yml");

				    if (!fileToCheck.exists()) {
				    	WCMain.s(p, "That warp does not exist!");
				    	return true;
				    }
				    
				    try {
				    	  warpLoad.load(fileToCheck);
				      } catch (Exception e) {
						  e.printStackTrace();
						  }
				    
			    World w = Bukkit.getWorld(warpLoad.getString("world"));
			    double x = warpLoad.getInt("x");
			    double y = warpLoad.getInt("y");
			    double z = warpLoad.getInt("z");
			    float yaw = warpLoad.getInt("yaw");
			    float pitch = warpLoad.getInt("pitch");
			    Location warpTo = new Location(w, x, y, z, yaw, pitch);
			    
			    double xP = p.getLocation().getX();
			    double yP = p.getLocation().getY();
			    double zP = p.getLocation().getZ();
			    double yawP = p.getLocation().getYaw();
			    double pitchP = p.getLocation().getPitch();
			    String warp = xP + "," + yP + "," + zP + "," + yawP + "," + pitchP;
			    String warpSimple = Math.round(xP) + "&f, &6" + Math.round(yP) + "&f, &6" + Math.round(zP) + "&d."; 
			    
			    List <String> history = plugin.userGrabSL(p.getName(), "LastLoc.History");
			    	if (history.size() >= 5){
			    		history.remove(history.get(0));
			    	}
			    history.add(warp);
			    plugin.userWriteSL(p.getName(), "LastLoc.History", history);
			    p.teleport(warpTo);
			    WCMain.s(p, "Warped to &6" + args[0] + " &dfrom &6" + warpSimple);
		  }
		  
	  return true;
	  
	  }
	  
	  public  void paginate(CommandSender sender, SortedMap<Integer, String> map,
			  int page, int pageLength, int warps) {
		  
		  	      StringBuilder sb = new StringBuilder();
		  	      
			      WCMain.s2((Player)sender, "&dWarps &f// &dPage &6" + String.valueOf(page) + " &dof &6" + (((map.size() % pageLength) == 0) ? map.size() / pageLength : (map.size() / pageLength) + 1) + "&d. &f(&6" + warps + "&f)&d.");
			      int i = 0, k = 0;
			      page--;
			      for (final Entry<Integer, String> e : map.entrySet()) {
			          k++;
			          if ((((page * pageLength) + i + 1) == k) && (k != ((page * pageLength) + pageLength + 1))) {
			              i++;
			              sb.append(e.getValue() + "&f, &d");
			          }
			      }
			      
			      String msg = sb.toString().trim();
			      msg = msg.substring(0, msg.length() - 6) + " ";
			      WCMain.s2((Player)sender, "&d" + msg.replace(".yml", ""));
			  }
}
