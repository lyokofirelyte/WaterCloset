package com.github.lyokofirelyte.WaterCloset;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WCHome implements CommandExecutor {
	
	WCMain plugin;
	public WCHome(WCMain instance){
	plugin = instance;
    }
	
	  public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		  
		  if (cmd.getName().equalsIgnoreCase("home") && sender instanceof Player){
			  home(sender, args);
			  return true;
		  }
		  
		  if (cmd.getName().equalsIgnoreCase("sethome") && sender instanceof Player){
			  sethome(sender, args);
			  return true;
		  }
		  
		  if ((cmd.getName().equalsIgnoreCase("remhome") || cmd.getName().equalsIgnoreCase("delhome")) && sender instanceof Player){
			  remhome(sender, args);
			  return true;
		  }
		  
		  return true;
	  }

	public void home(CommandSender sender, String[] args) {
		
		Player p = ((Player)sender);
		String pName = p.getName();
		int homeLimit = limitCheck(p);
		
		List <String> homes = plugin.userGrabSL(pName, "Homes.List");
		
		if (args.length == 0){
				if (homes.size() <= 0){
					WCMain.s(p, "You have no homes! Set one with /sethome <name>. Homes used: 0/" + homeLimit + "&d.");
				} else {
					viewHomes(pName, homes, homeLimit, p);
				}
			return;
		}
		
		if (!homes.contains(args[0])){
			viewHomes(pName, homes, homeLimit, p);
			return;
		}
		
		String loc = plugin.userGrabS(pName, "Homes." + args[0]);
		String[] locSplit = loc.split(",");
		
		double xF = p.getLocation().getX();
		double yF = p.getLocation().getY();
		double zF = p.getLocation().getZ();
		
		String xSimple = (xF + "").substring(0, 7);
		String ySimple = (yF + "").substring(0, 4);
		String zSimple = (zF + "").substring(0, 7);
		
		double x = Double.parseDouble(locSplit[0]);
		double y = Double.parseDouble(locSplit[1]);
		double z = Double.parseDouble(locSplit[2]);
		float yaw = Float.parseFloat(locSplit[3]);
		float pitch = Float.parseFloat(locSplit[4]);
		World world = Bukkit.getWorld(locSplit[5]);
		Location homeLanding = new Location(world, x, y, z, yaw, pitch);
		
		p.teleport(homeLanding);
		
		WCMain.s(p, "Teleported to &6" + args[0] + " &dfrom &6" + xSimple + "&f, &6" + ySimple + "&f, &6" + zSimple + "&d.");
		
	}

	private void viewHomes(String pName, List<String> homes, int homeLimit, Player p) {
		
		if (homes.size() >= homeLimit){
			WCMain.s(p, "Viewing homes for " + p.getDisplayName() + " &c(" + homes.size() + "&c/" + homeLimit + "&c)");
		} else {
			WCMain.s(p, "Viewing homes for " + p.getDisplayName() + " &a(" + homes.size() + "&a/" + homeLimit + "&a)");
		}
		
			for (String currentHome : homes){
				WCMain.s2(p, "&a| &6" + currentHome + " &f// &6" + plugin.userGrabS(pName, "Homes." + currentHome + "Simple"));
			}
	}

	private int limitCheck(Player p) {
		
		if (p.hasPermission("wa.admin")){
			return 1337;
		}
		
		if (p.hasPermission("wa.staff")){
			return 4;
		}
		
		if (p.hasPermission("wa.national")){
			return 3;
		}

		if (p.hasPermission("wa.shirian")){
			return 2;
		}
		
		if (p.hasPermission("wa.serf")){
			return 1;
		}
		
		return 0;
	}

	public void sethome(CommandSender sender, String[] args) {
		
		
		Player p = ((Player)sender);
		String pName = p.getName();
		int homeLimit = limitCheck(p);
		List <String> homes = plugin.userGrabSL(pName, "Homes.List");
		
		if (args.length == 0 && homes.size() == 0){
			WCMain.s(p, "Try /sethome <name>");
			return;
		} else if (args.length == 0){
			viewHomes(pName, homes, homeLimit, p);
			return;
		}

		if (homes.size() >= homeLimit && !homes.contains(args[0])){
			viewHomes(pName, homes, homeLimit, p);
			return;
		}
		
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		float yaw = p.getLocation().getYaw();
		float pitch = p.getLocation().getPitch();
		String world = p.getWorld().getName();
		String xyz = x + "," + y + "," + z + "," + yaw + "," + pitch + "," + world;

		String xSimple = (x + "").substring(0, 7);
		String ySimple = (y + "").substring(0, 4);
		String zSimple = (z + "").substring(0, 7);
		String xyzSimple = xSimple + "&f, &6" + ySimple + "&f, &6" + zSimple + " &6@ " + world;
		
		plugin.userWriteS(pName, "Homes." + args[0], xyz);
		plugin.userWriteS(pName, "Homes." + args[0] + "Simple", xyzSimple);
			if (homes.contains(args[0])){
				homes.remove(args[0]);
			}
		homes.add(args[0]);
		plugin.userWriteSL(pName, "Homes.List", homes);
		WCMain.s(p, "Set home &6" + args[0] + " &dat &6" + xSimple + "&f,&6 " + ySimple + "&f,&6 " + zSimple + "&d.");
			if (homes.size() >= homeLimit){
				WCMain.s2(p, "&cHomes remaining&f: &c0/" + homeLimit);
			} else {
				WCMain.s2(p, "&aHomes remaining&f: &a" + (homeLimit - homes.size()) + "/" + homeLimit);
			}
	} 
	
	public void remhome(CommandSender sender, String[] args) {
		
		Player p = ((Player)sender);
		String pName = p.getName();
		int homeLimit = limitCheck(p);
		List <String> homes = plugin.userGrabSL(pName, "Homes.List");
		
		if (args.length == 0 && homes.size() == 0){
			WCMain.s((Player)sender, "Try /remhome or /delhome <name>");
			return;
		} else if (args.length == 0){
			viewHomes(pName, homes, homeLimit, p);
			return;
		}

		if (!homes.contains(args[0])){
			viewHomes(pName, homes, homeLimit, p);
			return;
		}

		homes.remove(args[0]);
		plugin.userWriteSL(pName, "Homes.List", homes);
		WCMain.s(p, "Removal successful.");
		WCMain.s2(p, "&aHomes remaining&f: &a" + (homeLimit - homes.size()) + "/" + homeLimit);
		
	} 
	
	
}
