package com.github.lyokofirelyte.WaterCloset;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class WCDeath implements Listener{

	WCMain plugin;
	public WCDeath(WCMain instance){
	plugin = instance;
	}
	
	  @EventHandler(priority=EventPriority.NORMAL)
	  public void EDBEE(EntityDamageByEntityEvent e) {
		  
		  if (e.getDamager() instanceof Player){
			  
			  Player p = (Player) e.getDamager();
			  
			  if (p.getItemInHand().hasItemMeta()){
					if (p.getItemInHand().getItemMeta().hasLore() && p.getItemInHand().getItemMeta().hasDisplayName()){
						if (p.getItemInHand().getItemMeta().getDisplayName().toString().contains("HAMDRAX")){
							short dur = p.getItemInHand().getDurability();
							if (!p.getItemInHand().getType().equals(Material.DIAMOND_SWORD)){
								WCMobDrops.swapDrax(Material.DIAMOND_SWORD, p, dur, "Sword");
							}
						}
					}
			  }
		  }
	  
	  }
	  
	@EventHandler (priority = EventPriority.NORMAL)
	public void onKerSmashSplode(EntityDeathEvent e){
	
		Entity ent = e.getEntity();
		EntityDamageEvent ede = ent.getLastDamageCause();
		DamageCause dc = ede.getCause();
	
		if (ent instanceof Player){
			
			Player p = (Player)ent;
			int deaths = plugin.datacore.getInt("Users." + p.getName() + ".DeathCount");
			deaths++;
			plugin.datacore.set("Users." + p.getName() + ".DeathCount", deaths);
			((PlayerDeathEvent) e).setDeathMessage(null);

			switch (dc){
			
			default:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&odied. Why? I don't know or care. They're dead. DEAD. &7(" + deaths + "&7)"));
				break;

			case BLOCK_EXPLOSION:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&oexploded. Like that block of TNT they were standing on. &7(" + deaths + "&7)"));
				break;
				
			case FALL:
				Bukkit.broadcastMessage(WCMail.AS("&f&oTurns out that " + p.getDisplayName() + " &f&ocan't actually fly. Looks like that hurt. &7(" + deaths + "&7)"));
				break;
				
			case DROWNING:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&othought they were a fish. Now they're dead. &7(" + deaths + "&7)"));
				break;
				
			case ENTITY_ATTACK:
				Entity attacker = ((EntityDamageByEntityEvent) ede).getDamager();
				
					if (attacker instanceof Player){
						
						String attackerFinal = ((Player) attacker).getDisplayName();
						ItemStack weapon = ((HumanEntity) attacker).getItemInHand();
						if (weapon.hasItemMeta() && weapon.getItemMeta().hasDisplayName()){
							String weaponName = weapon.getItemMeta().getDisplayName();
							Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&otook on one too many " + attackerFinal + "&f&os, who used &b&o&l" + weaponName + "&f&o. &7(" + deaths + "&7)"));
						} else {
							String weaponName = weapon.getType().name().toLowerCase().replaceAll("_", " ");
							Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&otook on one too many " + attackerFinal + "&f&os, who used &6" + weaponName + "&f&o. &7(" + deaths + "&7)"));
						}
						break;
					} else {
						EntityType attackerFinal = ((EntityDamageByEntityEvent) ede).getDamager().getType();
						Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&otook on one too many " + attackerFinal + "&f&os, who used THUNDER SMASH!. &7(" + deaths + "&7)"));
						break;
					}
				
			case FALLING_BLOCK:
				Bukkit.broadcastMessage(WCMail.AS("&f&oThe sky is falling! The sk- wait, nope, was just a falling block. Poor " + p.getDisplayName() + "&f&o. &7(" + deaths + "&7)"));
				break;
			
			case FIRE: case FIRE_TICK:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " is on fire! Kidding, they're dead. &7(" + deaths + "&7)"));
				break;
				
			case LAVA:
				Bukkit.broadcastMessage(WCMail.AS("&f&oJump in the lava, they said. It'll be fine, they said. (" + p.getDisplayName() + "&f&o) &7(" + deaths + "&7)"));
				break;
				
			case LIGHTNING:
				Bukkit.broadcastMessage(WCMail.AS("&f&oSo, " + p.getDisplayName() + "&f&o's lightning rod works. Really Well. &7(" + deaths + "&7)"));
				break;
				
			case MAGIC:
				Bukkit.broadcastMessage(WCMail.AS("&f&oMajyyk. (" + p.getDisplayName() + "&f&o) &7(" + deaths + "&7)"));
				break;
				
			case MELTING:
				Bukkit.broadcastMessage(WCMail.AS("&f&oMelting is a death event? (" + p.getDisplayName() + "&f&o) &7(" + deaths + "&7)"));
				break;
				
			case PROJECTILE:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&o was impailed by an arrow and didn't survive. Luckily, their knee is intact. &7(" + deaths + "&7)"));
				break;
				
			case SUFFOCATION:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&osuffocated in a long, drawn-out process that was very painful. &7(" + deaths + "&7)"));
				break;
				
			case SUICIDE:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&owasted tons of cash on those suicide-prevention meetings. &7(" + deaths + "&7)"));
				break;
				
			case WITHER:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&owithered away. Get it? Withered? HAHAHAH.. ha..haa... a...just /suicide. &7(" + deaths + "&7)"));
				break;
				
			case VOID:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&ofell out of the world. That's a thing. &7(" + deaths + "&7)"));
				break;
				
			case CONTACT:
				Bukkit.broadcastMessage(WCMail.AS(p.getDisplayName() + " &f&ojust wanted to give that cactus a hug! &7(" + deaths + "&7)"));
				break;
			}
		}
		  
		
	}
		
		
	
}
