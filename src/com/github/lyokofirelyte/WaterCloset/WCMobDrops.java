package com.github.lyokofirelyte.WaterCloset;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WCMobDrops implements Listener {

	WCMain plugin;
	
	public WCMobDrops(WCMain instance){
	   plugin = instance;
    } 
	
	Inventory chest;
	HashMap<String, Inventory> tempStorage = new HashMap<String, Inventory>();
	
	 @EventHandler(priority = EventPriority.NORMAL)
	  public void onPlayerBadTouch(PlayerInteractEvent event){
		 
		 if (event.getAction() == Action.LEFT_CLICK_AIR && event.getPlayer().isSneaking()){
			 event.getPlayer().sendMessage(WCMail.WC + "This inventory gets wiped every 24 hours or so.");
				

			 Player player = (Player) event.getPlayer();
	         chest = tempStorage.get(player.getName());
	         if(chest == null){
	             Inventory newChest = Bukkit.getServer().createInventory(null, 9, "24 HOUR STORAGE");
	             tempStorage.put(player.getName(), newChest);
	         }
	        
	         chest = tempStorage.get(player.getName());
	         player.openInventory(chest);
	         return;
			}
		 		 
	      if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)){
	    	  
	    	  	if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.WALL_SIGN){
	    	  		paragonSign(event, event.getPlayer());
	    	  	}
	    	  	if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.STONE_BUTTON){
	    	  		paragonCheckout(event, event.getPlayer());
	    	  	}
	    	  
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
	    
	 
	
	
	private void paragonCheckout(PlayerInteractEvent e, Player p) {
		
		
		
		double x = e.getClickedBlock().getX();
		double y = e.getClickedBlock().getY();
		double z = e.getClickedBlock().getZ();
		String xyz = x + "," + y + "," + z;
		
		String rewardInfo = plugin.config.getString("Paragons.Rewards." + xyz + ".Info");
		
			if (rewardInfo == null){
				return;
				
			} else {
				
				int action = plugin.datacore.getInt("Users." + p.getName() + ".ParagonAction");
				
					switch (action){
					
					case 1:
						p.sendMessage(WCMail.WC + "That updates automatically now! Buy something else!");
						break;
					
					case 2: 
						paragonAmountCheck(15, p, action);
						break;
						
					case 3: 
						paragonAmountCheck(10, p, action);
						break;
						
					case 4: 
						paragonAmountCheck(10, p, action);
						break;
						
					case 5: 
						paragonAmountCheck(10, p, action);
						break;
						
					case 6: 
						paragonAmountCheck(5, p, action);
						break;
						
					case 7: 
						paragonAmountCheck(20, p, action);
						break;
						
					case 8: 
						paragonAmountCheck(20, p, action);
						break;
						
					default:
						p.sendMessage(WCMail.WC + "You've not selected anything to purchase!");
						break;
					}
			}
		
	}


	private void paragonAmountCheck(int cost, Player p, int action) {
		
		if (p.getInventory().getItemInHand().hasItemMeta()){
			
			if (p.getInventory().getItemInHand().getItemMeta().hasDisplayName()){

				if (p.getInventory().getItemInHand().getItemMeta().getDisplayName().toString().contains("TOKEN")){
				
					int amount = p.getInventory().getItemInHand().getAmount();
				
						if (amount < cost){
							p.sendMessage(WCMail.WC + "You know very well you don't have enough to afford that.");
							return;
						} else {
							paragonRewardFinal(cost, p, action, amount);
							return;
						}
				
				} else {
					p.sendMessage(WCMail.WC + "Please hold your tokens in your hand while making a purchase.");
					return;
				}
			} else {
				p.sendMessage(WCMail.WC + "Please hold your tokens in your hand while making a purchase.");
				return;
			}
		} else {
			p.sendMessage(WCMail.WC + "Please hold your tokens in your hand while making a purchase.");
			return;
		}
	}


	private void paragonRewardFinal(int cost, Player p, int action, int amount) {
		
		switch (action){
		
		case 1:
			
			p.sendMessage(WCMail.WC + "That updates automatically now, buy something else!");
			break;
		
		case 2:
			
			int teleports = plugin.datacore.getInt("Users." + p.getName() + ".TPTokens");
			teleports = teleports + 3;
			plugin.datacore.set("Users." + p.getName() + ".TPTokens", teleports);
			p.sendMessage(WCMail.WC + "Added 3 TP tokens to your account! Total: " + teleports);
			p.sendMessage(WCMail.WC + "Use them with /wc tp <player>");
			p.getInventory().getItemInHand().setAmount((amount - (cost-1)));
			List <String> purchases1 = plugin.datacore.getStringList("Users." + p.getName() + ".Purchases");
			purchases1.add("/wc tp <player> - Remaining Tokens: " + teleports);
			plugin.datacore.set("Users." + p.getName() + ".Purchases", purchases1);
			break;
			
		case 3:
			
			Boolean home = plugin.datacore.getBoolean("Users." + p.getName() + ".SpecialHome");
			
				if (home){
					p.sendMessage(WCMail.WC + "Hey, if you want to waste your tokens, Hugs could use some extra ones.");
					break;
				} else {
					plugin.datacore.set("Users." + p.getName() + ".SpecialHome", true);
					p.sendMessage(WCMail.WC + "You can now set your special home with /wc sethome. Use it with /wc home.");
					List <String> purchases2 = plugin.datacore.getStringList("Users." + p.getName() + ".Purchases");
					purchases2.add("/wc sethome, /wc home");
					plugin.datacore.set("Users." + p.getName() + ".Purchases", purchases2);
					p.getInventory().getItemInHand().setAmount((amount - (cost-1)));
				} 
				
			break;
			
		case 4:
			
			int backs = plugin.datacore.getInt("Users." + p.getName() + ".BackTokens");
			backs = backs + 1;
			plugin.datacore.set("Users." + p.getName() + ".BackTokens", backs);
			p.sendMessage(WCMail.WC + "Added 1 back tokens to your account! Total: " + backs);
			p.sendMessage(WCMail.WC + "Use them with /wc back");
			p.getInventory().getItemInHand().setAmount((amount - (cost-1)));
			List <String> purchases3 = plugin.datacore.getStringList("Users." + p.getName() + ".Purchases");
			purchases3.add("/wc back - Remaining Tokens: " + backs);
			plugin.datacore.set("Users." + p.getName() + ".Purchases", purchases3);
			break;
			
		case 5:
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 999999999, 0));
			p.sendMessage(WCMail.WC + "Enjoy!");
			p.getInventory().getItemInHand().setAmount((amount - (cost-1)));
			break;
			
		case 6:
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0));
			p.sendMessage(WCMail.WC + "Enjoy!");
			p.getInventory().getItemInHand().setAmount((amount - (cost-1)));
			break;
			
		case 7:
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1));
			p.sendMessage(WCMail.WC + "Enjoy!");
			p.getInventory().getItemInHand().setAmount((amount - (cost-1)));
			break;
			
		case 8:
			
			Boolean market = plugin.datacore.getBoolean("Users." + p.getName() + ".Market");
			
			if (market){
				p.sendMessage(WCMail.WC + "Hey, if you want to waste your tokens, Hugs could use some extra ones.");
				break;
			} else {
				plugin.datacore.set("Users." + p.getName() + ".Market", true);
				p.sendMessage(WCMail.WC + "You can now warp to the market with /wc market.");
				List <String> purchases4 = plugin.datacore.getStringList("Users." + p.getName() + ".Purchases");
				purchases4.add("/wc market");
				plugin.datacore.set("Users." + p.getName() + ".Purchases", purchases4);
				p.getInventory().getItemInHand().setAmount((amount - (cost-1)));
			} 
			
		break;
		}
		
		
	}


	private void paragonSign(PlayerInteractEvent e, Player p) {
		
		double x = e.getClickedBlock().getX();
		double y = e.getClickedBlock().getY();
		double z = e.getClickedBlock().getZ();
		String xyz = x + "," + y + "," + z;
		
		String rewardInfo = plugin.config.getString("Paragons.Rewards." + xyz + ".Info");
		
			if (rewardInfo == null){
				return;
				
			} else {
				
				int act = plugin.config.getInt("Paragons.Rewards." + xyz + ".Action");
				p.sendMessage(WCMail.AS(WCMail.WC + rewardInfo));
				p.sendMessage(WCMail.WC + "Purchase this by pressing the checkout button.");
				plugin.datacore.set("Users." + p.getName() + ".ParagonAction", act);
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
			Random paragonRand = new Random();
			int randomMoneyAmount = moneyAmount.nextInt(140) + 1;
		    int randomNumber = rand.nextInt(4) + 1;
		    int paragon = paragonRand.nextInt(1000) + 1;
		
		
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
          }
		
		if (paragon == 500 && event.getEntity().getKiller().getWorld().getName().equals("world")){
			
			ItemStack paragonDrop = new ItemStack(Material.STAINED_CLAY, 1, (short) 15);
            ItemMeta paragonName = paragonDrop.getItemMeta();
            drops = new ArrayList<ItemStack>();
            lore = new ArrayList<String>();

            paragonName.addEnchant(Enchantment.DURABILITY, 10, true);
            paragonName.setDisplayName("§0§lDEATH PARAGON");
            lore.add("§7§oI should return this to the paragon station near spawn...");
            paragonName.setLore(lore);
            paragonDrop.setItemMeta((ItemMeta)paragonName);
            drops.add(paragonDrop);
            event.getDrops().addAll(drops);
            Bukkit.broadcastMessage(WCMail.AS(WCMail.WC + event.getEntity().getKiller().getDisplayName() + " &dhas found a &0death &dparagon from a " + event.getEntityType().toString().toLowerCase() + "&d."));
		}
		
	}
	
	
	
	}
}
