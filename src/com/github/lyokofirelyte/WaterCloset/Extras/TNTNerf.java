package com.github.lyokofirelyte.WaterCloset.Extras;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.github.lyokofirelyte.WaterCloset.WCMain;

public class TNTNerf implements Listener {
	
	WCMain plugin;
	
	public TNTNerf(WCMain instance){
	   plugin = instance;
	}

	  @EventHandler(priority = EventPriority.LOW)
	  public void onEntityExplode(EntityExplodeEvent event)
	  {
	    Entity e = event.getEntity();

	    List <Entity> entity_list = e.getNearbyEntities(7.0D, 7.0D, 7.0D);
	    List <Block> block_list = event.blockList();

	    for (Entity entity : entity_list)
	    {
	      if ((entity.getType().equals(EntityType.MINECART_TNT)) || (entity.getType().equals(EntityType.PRIMED_TNT)))
	      {
	        event.setCancelled(true);
	        break;
	      }
	    }

	    for (Block block : block_list)
	    {
	      if (block.getType().equals(Material.TNT))
	      {
	        event.setCancelled(true);
	        break;
	      }
	    }
	 }
}

