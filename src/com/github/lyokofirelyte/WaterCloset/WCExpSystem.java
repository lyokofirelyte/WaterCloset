package com.github.lyokofirelyte.WaterCloset;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import com.github.lyokofirelyte.WaterCloset.Commands.WCMail;

public class WCExpSystem implements Listener {
	
      WCMain plugin;
	  public WCExpSystem(WCMain instance){
	  this.plugin = instance;
	  }
	  
	  @EventHandler (priority = EventPriority.NORMAL)
	  public void onExpGain(final PlayerExpChangeEvent e){
		  
		  if (plugin.datacore.getBoolean("Users." + e.getPlayer().getName() + ".expDeposit") == false){
		  
		  Player p = e.getPlayer();
		  int expAmount = e.getAmount();
		  int storedAmount = plugin.datacore.getInt("Users." + p.getName() + ".MasterExp");
		  plugin.datacore.set("Users." + p.getName() + ".MasterExp", (expAmount + storedAmount));
		  e.setAmount(0);
		  
		  
		  	if (plugin.datacore.getBoolean("Users." + e.getPlayer().getName() + ".expWarn") == false){
		  		p.sendMessage(WCMail.WC + "Your xp has been stored in /wc xp. (Toggle this warning with /wc xp toggle)");
		  	}
	  }
	  	}
	  
	  
}
