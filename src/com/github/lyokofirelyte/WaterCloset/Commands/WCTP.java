package com.github.lyokofirelyte.WaterCloset.Commands;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.github.lyokofirelyte.WaterCloset.WCMain;

public class WCTP implements Listener {
	
	WCMain plugin;
	public WCTP(WCMain instance){
	plugin = instance;
    }
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onTP(PlayerTeleportEvent e){
		
		Player p = e.getPlayer();
		double xP = p.getLocation().getX();
	    double yP = p.getLocation().getY();
	    double zP = p.getLocation().getZ();
	    double yawP = p.getLocation().getYaw();
	    double pitchP = p.getLocation().getPitch();
	    String world = p.getLocation().getWorld().getName();
	    String warp = xP + "," + yP+1 + "," + zP + "," + yawP + "," + pitchP + "," + world;
	    
	    List <String> history = plugin.userGrabSL(p.getName(), "LastLoc.History");
	    	if (history.size() >= 7){
	    		history.remove(history.get(0));
	    	}
	    history.add(warp);
	    plugin.userWriteSL(p.getName(), "LastLoc.History", history);
	}

}
