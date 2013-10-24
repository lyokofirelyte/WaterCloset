package com.github.lyokofirelyte.WaterCloset;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.github.lyokofirelyte.WaterCloset.WCCommands;

import static com.github.lyokofirelyte.WaterCloset.Commands.WCMail.*;

public class WCMiscEvents implements Listener {
	
	WCMain plugin;
	WCCommands wc;
	
	public WCMiscEvents(WCMain plugin){
		
		this.plugin = plugin;
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerTouchMob(PlayerInteractEntityEvent e){
		
		Player p = e.getPlayer();
		Entity ent = e.getRightClicked();
		boolean ifCommand = plugin.datacore.getBoolean("Users." + p.getName() + ".commandUsed");
		
		if (ifCommand){
			
			ent.setPassenger(p);
			plugin.datacore.set("Users." + p.getName() + ".commandUsed", false);
			
		}
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEnderDragonSpawnPORTALZ(EntityCreatePortalEvent e){
		
		if (e.isCancelled()){
			
			return;
			
		}
		
		EntityType ent = e.getEntity().getType();
		
		if (ent == EntityType.ENDER_DRAGON){
			
			e.setCancelled(true);
			
		}
		
	}
	
}
