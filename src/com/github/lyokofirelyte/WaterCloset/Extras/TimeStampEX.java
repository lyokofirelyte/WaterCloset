package com.github.lyokofirelyte.WaterCloset.Extras;

import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.lyokofirelyte.WaterCloset.WCMain;

public class TimeStampEX
  implements CommandExecutor
{
  public WCMain plugin;
  private Long unixTime;

  public TimeStampEX(WCMain plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("timestamp"))
    {
      if ((sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "This is a console command.");
      } else {
        this.unixTime = (System.currentTimeMillis() / 1000L);
        Date time = new Date(this.unixTime * 1000L);
        String time2 = String.valueOf(time);
        Bukkit.dispatchCommand(sender, "vt setstr time current " + time2);
      }
    }

    if (cmd.getName().equalsIgnoreCase("getnick"))
    {
      if ((sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "This is a console command.");
      }
      else if (args.length > 0) {
        Player pcheck = Bukkit.getPlayerExact(args[0]);
        if (pcheck != null) {
          Bukkit.dispatchCommand(sender, "vtrigger setstr nick " + args[0] + " " + pcheck.getDisplayName());
        }
        if (pcheck == null)
          sender.sendMessage(ChatColor.RED + "That player is not online.");
      }
      else {
        sender.sendMessage(ChatColor.RED + "Usage is @CMDCON getnick <player>.");
      }
    }

    if (cmd.getName().equalsIgnoreCase("stringbuilder"))
    {
      if ((sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "This is a console command.");
      } else if (args.length > 0) {
        String lengthcheck = args[0];
        if (isInteger(lengthcheck)) {
          int lc2 = Integer.parseInt(lengthcheck) + 1;
          String message = createString(args, lc2);
          Bukkit.dispatchCommand(sender, "vtrigger setstr string current " + message);
        } else {
          sender.sendMessage(ChatColor.RED + "You were supposed to put a number there, not whatever it is that you put.");
        }
      }
      else {
        sender.sendMessage(ChatColor.RED + "Usage is @CMDCON stringbuilder <args> <message>");
      }

    }

    if ((cmd.getName().equalsIgnoreCase("itemname")) && (sender.hasPermission("vte.itemname")))
    {
      if (!(sender instanceof Player)) {
        sender.sendMessage(ChatColor.RED + "Use @CMDOP!");
      }
      else
      {
        Player player = (Player)sender;
        ItemStack item = player.getItemInHand();

        String name = item.getItemMeta().hasDisplayName() ? item.getItemMeta().getDisplayName() : item.getType().name().replace("_", " ").toLowerCase();
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "vtrigger setstr itemname current " + name);
      }

    }

    return true;
  }

  public static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  public String createString(String[] args, int x)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = x; i < args.length; i++)
    {
      if ((i != x) && (i != args.length))
      {
        sb.append(" ");
      }
      sb.append(args[i]);
    }
    return sb.toString();
  }
}