package com.github.lyokofirelyte.WaterCloset;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WCJoin
  implements Listener
{
  WCMain plugin;

  public WCJoin(WCMain instance)
  {
    this.plugin = instance;
  }

  @EventHandler(priority=EventPriority.HIGH)
  public boolean onPlayerJoin(final PlayerJoinEvent event)
  {
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