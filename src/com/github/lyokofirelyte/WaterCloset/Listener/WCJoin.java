package com.github.lyokofirelyte.WaterCloset.Listener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.github.lyokofirelyte.WaterCloset.WCMain;
import com.github.lyokofirelyte.WaterCloset.Commands.WCMail;
import com.github.lyokofirelyte.WaterCloset.Util.Utils;
import com.github.lyokofirelyte.WaterCloset.Util.WCVault;

public class WCJoin implements Listener {
  
WCMain plugin;

public WCJoin(WCMain instance){
plugin = instance;
}

@SuppressWarnings("deprecation")
@EventHandler(priority=EventPriority.HIGH)
  public boolean onPlayerJoin(final PlayerJoinEvent event) throws Exception
  {
	  try
	    {
		  plugin.userCreate(event.getPlayer().getName());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	  setBoard(event.getPlayer());
	  
	  File f = new File(plugin.getDataFolder() + File.separator + "Users", event.getPlayer().getName() + ".yml");
	  YamlConfiguration loadedFile = YamlConfiguration.loadConfiguration(f);
	  plugin.loadedUsers.put(event.getPlayer().getName(), loadedFile);
	  
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
        this.plugin.saveYamls();
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
      this.plugin.saveYamls();
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


	public void setBoard(Player p){
		
		Objective localObjective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		
		if (localObjective == null){
			
			Scoreboard board = manager.getNewScoreboard();
			
			Objective o1 = board.registerNewObjective("wa", "dummy");
			o1.setDisplaySlot(DisplaySlot.SIDEBAR);
			o1.setDisplayName("§2Worlds Apart");
			
			Score balance = o1.getScore(Bukkit.getOfflinePlayer("§3Balance:"));
			Score paragons = o1.getScore(Bukkit.getOfflinePlayer("§3Paragon Lvl:"));
			Score online = o1.getScore(Bukkit.getOfflinePlayer("§9Online:"));
			Score rank = o1.getScore(Bukkit.getOfflinePlayer("§3Rank: " + Utils.AS(WCVault.chat.getPlayerPrefix(p))));
			Score options = o1.getScore(Bukkit.getOfflinePlayer("§5/options"));
			
			Boolean inAlliance = plugin.WAAlliancesconfig.getBoolean("Users." + p.getName() + ".InAlliance");
			
			if (inAlliance == null || inAlliance == false){
				Score alliance = o1.getScore(Bukkit.getOfflinePlayer("§7Forever§8Alone"));
				alliance.setScore(1);
			} else {
				String alliance = plugin.WAAlliancesconfig.getString("Users." + p.getName() + ".Alliance");
				String color1 = plugin.WAAlliancesconfig.getString("Alliances." + alliance + ".Color1");
				String color2 = plugin.WAAlliancesconfig.getString("Alliances." + alliance + ".Color2");
				int midpoint = alliance.length() / 2;
				String firstHalf = alliance.substring(0, midpoint);
				String secondHalf = alliance.substring(midpoint);
				String completed = "§" + color1 + firstHalf + "§" + color2 + secondHalf;
				Integer members = Integer.valueOf(plugin.WAAlliancesconfig.getInt("Alliances." + alliance + ".MemberCount"));
				if (completed.length() >= 16){
					Score alliance2 = o1.getScore(Bukkit.getOfflinePlayer(completed.substring(0, 16)));
					alliance2.setScore(members);
				} else {
					Score alliance2 = o1.getScore(Bukkit.getOfflinePlayer(completed));
					alliance2.setScore(members);
				}
			}
			
			paragons.setScore(plugin.datacore.getInt("Users." + p.getName() + ".ParagonLevel"));
			balance.setScore((int) WCVault.econ.getBalance(p.getName()));
			rank.setScore(1);
			online.setScore(Bukkit.getOnlinePlayers().length);
			options.setScore(0);
			p.setScoreboard(board);
		} else {
			
			p.setScoreboard(manager.getNewScoreboard());
			
		}
	}
	
}