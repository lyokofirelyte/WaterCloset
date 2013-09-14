package com.github.lyokofirelyte.WaterCloset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WCJoin
  implements Listener
{
  WCMain plugin;

  public WCJoin(WCMain instance)
  {
    this.plugin = instance;
  }

  @SuppressWarnings("deprecation")
@EventHandler(priority=EventPriority.HIGH)
  public boolean onPlayerJoin(final PlayerJoinEvent event)
  {
	  
		if (plugin.datacore.getBoolean("Users." + event.getPlayer().getName() + ".needsExp")){
			float xp = plugin.datacore.getInt("Users." + event.getPlayer().getName() + ".exp");
			event.getPlayer().setExp(xp);
			plugin.datacore.set("Users." + event.getPlayer().getName() + ".needsExp", false);
		}
	  
	  if (event.getPlayer().hasPlayedBefore() == false){
		  plugin.datacore.set("Users." + event.getPlayer().getName() + ".Comp", true);
	  }
	  
	 if (plugin.datacore.getBoolean("Users." + event.getPlayer().getName() + ".Comp") == false && event.getPlayer().hasPlayedBefore()){
	     ArrayList<String> lore;
		 plugin.datacore.set("Users." + event.getPlayer().getName() + ".Comp", true);
		 Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + event.getPlayer().getName() + " 40000");
		 
		    ItemStack paragon = new ItemStack(Material.STAINED_CLAY, 15, (short) 2);
	        ItemMeta paragonName = paragon.getItemMeta();
	        lore = new ArrayList<String>();
	        
	        lore.add("§7§oSorry about the trouble!");
	        lore.add("§7§oThis will help a bit.");
	        paragonName.setLore(lore);
	        
	        paragonName.setDisplayName("§4§lINFERNO PARAGON");
	        paragonName.addEnchant(Enchantment.DURABILITY, 10, true);
	        paragon.setItemMeta(paragonName);
	        event.getPlayer().getInventory().addItem(paragon);
	        event.getPlayer().updateInventory();
	        event.getPlayer().sendMessage(WCMail.WC + "We're working on getting the alliances back in order. The system is fixed, but for the troubles here's a kit.");
	 }
	 
	WCMail.mailLogin(event.getPlayer());
    List <String> joinMessages = this.plugin.config.getStringList("Core.JoinMessages");
    Random rand = new Random();
    int randomNumber = rand.nextInt(joinMessages.size());
    final String joinMessage = (String)joinMessages.get(randomNumber);

    event.setJoinMessage(null);

    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
    {
      public void run()
      {
        Player p = event.getPlayer();
        Bukkit.broadcastMessage("§e" + joinMessage.replace("%p", new StringBuilder("§7").append(p.getDisplayName()).append("§e").toString()).replace(" ", " §e"));
      }
    }
    , 5L);

    if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + event.getPlayer().getName() + ".InAlliance"))
    {
      this.plugin.WAAlliancesconfig.set("Users." + event.getPlayer().getName() + ".AllianceRank", "Guest");
    }

    Player p = event.getPlayer();
    String pl = p.getName();
    String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
    Boolean Colorized = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + Alliance + ".Colorized"));
    Boolean hasNick = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".hasNick"));
    String Nick = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Nick");
    Boolean dis = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Disbanded." + Alliance));
    Boolean disHandeled = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Users." + p + ".disHandeled"));

    if (Colorized.booleanValue())
    {
      if (this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".Staff"))
      {
        int midpoint = Nick.length() / 2;
        String firstHalf = Nick.substring(0, midpoint);
        String secondHalf = Nick.substring(midpoint);
        String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
        String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");
        String firstHalfColors = "&" + Color1 + "&o" + firstHalf;
        String secondHalfColors = "&" + Color2 + "&o" + secondHalf;
        String completed = firstHalfColors + secondHalfColors;

        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + completed);
        this.plugin.saveWAAlliances();
        return true;
      }

      int midpoint = Nick.length() / 2;
      String firstHalf = Nick.substring(0, midpoint);
      String secondHalf = Nick.substring(midpoint);
      String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
      String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");
      String firstHalfColors = "&" + Color1 + firstHalf;
      String secondHalfColors = "&" + Color2 + secondHalf;
      String completed = firstHalfColors + secondHalfColors;

      Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + completed);
      this.plugin.saveWAAlliances();
    }

    if (!hasNick.booleanValue())
    {
      this.plugin.WAAlliancesconfig.set("Users." + pl + ".Nick", pl);
      this.plugin.WAAlliancesconfig.set("Users." + pl + ".hasNick", Boolean.valueOf(true));
      Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + "&7" + pl);
    }

    if ((dis.booleanValue()) && (!disHandeled.booleanValue()))
    {
      this.plugin.WAAlliancesconfig.set("Users." + pl + ".Alliance", null);
      this.plugin.WAAlliancesconfig.set("Users." + pl + ".InAlliance", null);
      this.plugin.WAAlliancesconfig.set("Users." + pl + ".AllianceRank", null);
      this.plugin.WAAlliancesconfig.set("Users." + pl + ".disHandeled", Boolean.valueOf(true));

      if (this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".Staff"))
      {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + "&7&o" + Nick);
        return true;
      }

      Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + "&7" + Nick);
      return true;
    }

    return true;
  }
}