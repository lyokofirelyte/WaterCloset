package com.github.lyokofirelyte.WaterCloset.Listener;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.lyokofirelyte.WaterCloset.WCMain;

public class WCQuit
  implements Listener
{
  WCMain plugin;

  public WCQuit(WCMain instance)
  {
    this.plugin = instance;
  }

  @EventHandler(priority=EventPriority.HIGH)
  public boolean onPlayerQuit(final PlayerQuitEvent event)
  {
	    
    List <String> quitMessages = this.plugin.config.getStringList("Core.QuitMessages");
    Random rand = new Random();
    int randomNumber = rand.nextInt(quitMessages.size());
    final String quitMessage = (String)quitMessages.get(randomNumber);
    
    File f = new File(plugin.getDataFolder() + File.separator + "Users", event.getPlayer().getName() + ".yml");
	
	try {
		if (plugin.loadedUsers.containsKey(event.getPlayer().getName())){
			plugin.loadedUsers.get(event.getPlayer().getName()).save(f);
		}
	} catch (IOException e) {
		e.printStackTrace();
	}

	plugin.loadedUsers.remove(event.getPlayer().getName());
	
    event.setQuitMessage(null);

    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
    {
      public void run()
      {
        Player p = event.getPlayer();
        Bukkit.broadcastMessage("§e" + quitMessage.replace("%p", new StringBuilder("§7").append(p.getDisplayName()).append("§e").toString()).replace(" ", " §e"));
      }
    }
    , 5L);

    return true;
  }

  @EventHandler(priority=EventPriority.HIGH)
  public void onPlayerKick(PlayerKickEvent event)
  {
    event.setLeaveMessage(null);
  }
  

}