package com.github.lyokofirelyte.WaterCloset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WCMobDrops implements Listener {

	WCMain plugin;
	
	public WCMobDrops(WCMain instance){
	   plugin = instance;
	  }
	

	 @EventHandler(priority = EventPriority.NORMAL)
	  public void onPlayerBadTouch(PlayerInteractEvent event){
		 		 
	      if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)){
	    	  
	    	if (event.getPlayer().getInventory().getItemInHand().getTypeId() == 371) {

	 		  Player player = event.getPlayer();
	 		  
	 		  if (player.getInventory().getItemInHand().getItemMeta().hasDisplayName()){
	 		  
	          if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().contains("Shinies")) {
	        	  
	        	  int goldInHand = player.getInventory().getItemInHand().getAmount();
	        	  if (event.getPlayer().hasPermission("wa.statesman")){
	        	  Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + player.getName() + " " + (goldInHand * 40));
	        	  } else {
	        	  Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + player.getName() + " " + (goldInHand * 20));
	        	  }
	        	  player.setItemInHand(new ItemStack(Material.AIR)); 
	        	  return;
	       
	          }
	        }
	      }
	    }
	  }
	    
	 
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityKersplode(EntityDeathEvent event){
		
	
	if ((event.getEntity().getKiller() instanceof Player)) {
		
		    EntityType type = event.getEntityType();
		    List <String> invalidMobs = plugin.config.getStringList("Mobs.NoShinyDrops");

		    	if (invalidMobs.contains(type.toString())){
		    		return;
		    	}
		
			ArrayList<ItemStack> drops;
			ArrayList<String> lore;
			Random rand = new Random();
			Random moneyAmount = new Random();
			int randomMoneyAmount = moneyAmount.nextInt(140) + 1;
		    int randomNumber = rand.nextInt(4) + 1;
		
		
		if (randomNumber == 4) {
			
			ItemStack shard = new ItemStack(Material.GOLD_NUGGET, (randomMoneyAmount / 20));
            ItemMeta name = shard.getItemMeta();
            drops = new ArrayList<ItemStack>();
            lore = new ArrayList<String>();

            name.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10, true);
            	if (event.getEntity().getKiller().hasPermission("wa.statesman")){
            		name.setDisplayName("§6§oFourty Shinies!");
            	} else {
            		name.setDisplayName("§6§oTwenty Shinies!");
            	}
            lore.add("§7§oIt's so shiny...I should right click with it in my hand.");
            name.setLore(lore);
            shard.setItemMeta((ItemMeta)name);
            drops.add(shard);
            event.getDrops().addAll(drops);
            return;
          }
		
	}
	
	
	
	}
}
