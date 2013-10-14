package com.github.lyokofirelyte.WaterCloset;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.github.lyokofirelyte.WaterCloset.WCCommands;
import static com.github.lyokofirelyte.WaterCloset.WCMail.*;

public class WCInteract implements Listener {
	
	WCMain plugin;
	WCCommands wc;
	
	public WCInteract(WCMain plugin){
		
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
			p.sendMessage(AS(wc.WC + "You have successfully rode a &6" + ent.getType().name() + "&d!"));
			
		}
		
	}
	
}
