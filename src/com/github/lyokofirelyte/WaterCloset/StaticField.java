package com.github.lyokofirelyte.WaterCloset;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class StaticField extends JavaPlugin
  implements CommandExecutor, Listener
{
  private int taskID;
  String prefix = "§aStaticField §f// §a";
  WCMain plugin;

  public StaticField(WCMain instance)
  {
    this.plugin = instance;
  }

  private int getTaskID()
  {
    return this.taskID;
  }

  public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args)
  {

    switch (cmd.getName()){
    case "ff": case "forcefield":

        List<String> forceUsers = this.plugin.datacore.getStringList("ForceField.Users");

        if (!forceUsers.contains(sender.getName()))
        {
          forceUsers.add(sender.getName());
          this.plugin.datacore.set("ForceField.Users", forceUsers);

          if (this.plugin.datacore.getBoolean("GUI")) {
            sender.sendMessage(this.prefix + "FORCEFIELD ON!");
          }

          this.taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new BukkitRunnable()
          {
            public void run()
            {
              World world = ((Player)sender).getWorld();
              Location loc = ((Player)sender).getLocation();
              Location l = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
              Location l2 = new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ());
              world.playEffect(l, Effect.ENDER_SIGNAL, 0);
              world.playEffect(l2, Effect.ENDER_SIGNAL, 0);
              int thisTask = StaticField.this.getTaskID();
              if (!StaticField.this.plugin.datacore.getBoolean("Users." + sender.getName() + ".HasTask")) {
                StaticField.this.plugin.datacore.set("Users." + sender.getName() + ".Task", thisTask);
                StaticField.this.plugin.datacore.set("Users." + sender.getName() + ".HasTask", true);
              }
            }
          }
          , 0L, 5L);
        }
        else
        {
          forceUsers.remove(sender.getName());
          Bukkit.getServer().getScheduler().cancelTask(this.plugin.datacore.getInt("Users." + sender.getName() + ".Task"));
          this.plugin.datacore.set("Users." + sender.getName() + ".HasTask", false);
          this.plugin.datacore.set("ForceField.Users", forceUsers);
          if (this.plugin.datacore.getBoolean("GUI")) {
            sender.sendMessage(this.prefix + "FORCEFIELD OFF!");
          }
          return true;
        }

        break;
      }

          

    return true;
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public void onPlayerMove(PlayerMoveEvent event)
  {
    List<String> forceUsers = this.plugin.datacore.getStringList("ForceField.Users");

    if ((forceUsers.size() >= 1) && (!forceUsers.contains(event.getPlayer().getName())))
    {
      for (String forcePlayer : forceUsers)
      {
        double xto = event.getTo().getBlockX();
        double yto = event.getTo().getBlockY();
        double zto = event.getTo().getBlockZ();

        double xfrom2 = Bukkit.getPlayer(forcePlayer).getLocation().getBlockX();
        double yfrom2 = Bukkit.getPlayer(forcePlayer).getLocation().getBlockY();
        double zfrom2 = Bukkit.getPlayer(forcePlayer).getLocation().getBlockZ();

        if ((xto == xfrom2) && (yto == yfrom2) && (zto == zfrom2)) {
          event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(-5));
          if (this.plugin.datacore.getBoolean("Message"))
            event.getPlayer().sendMessage(this.prefix + Bukkit.getPlayer(forcePlayer).getDisplayName() + " is currently using a forcefield!");
        }
      }
    }
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public void onEntityDamange(EntityDamageEvent event)
  {
    List<String> forceUsers = this.plugin.datacore.getStringList("ForceField.Users");

    if (forceUsers.size() >= 1)
    {
      Entity e = event.getEntity();

      if (e instanceof Player) {
        Player p = (Player)e;

        if (forceUsers.contains(p.getName()))
        {
          for (Entity e1 : event.getEntity().getNearbyEntities(5.0D, 5.0D, 5.0D))
          {
            e1.setVelocity(e1.getLocation().getDirection().multiply(-5));
          }

          event.setCancelled(true);
        }
      }
    }
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public boolean onPlayerQuit(PlayerQuitEvent event)
  {
    if (this.plugin.datacore.getBoolean("Users." + event.getPlayer().getName() + ".HasTask"))
    {
      List<String> forceUsers = this.plugin.datacore.getStringList("ForceField.Users");

      Bukkit.getServer().getScheduler().cancelTask(this.plugin.datacore.getInt("Users." + event.getPlayer().getName() + ".Task"));
      forceUsers.remove(event.getPlayer().getName());
      this.plugin.datacore.set("ForceField.Users", forceUsers);
      this.plugin.datacore.set("Users." + event.getPlayer().getName() + ".HasTask", false);
    }

    return true;
  }
}