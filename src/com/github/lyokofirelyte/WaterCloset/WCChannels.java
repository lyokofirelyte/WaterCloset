package com.github.lyokofirelyte.WaterCloset;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class WCChannels implements CommandExecutor, Listener {
	
  WCMain plugin;
  String waaprefix = ChatColor.WHITE + "waOS " + ChatColor.BLUE + "// " + ChatColor.AQUA;
  String waheader = ChatColor.GREEN + "| " + ChatColor.AQUA;
  String WC = "§dWC §5// §d";

  public WCChannels(WCMain instance)
  {
    this.plugin = instance;
  }
  
  
  @EventHandler(priority=EventPriority.HIGH)
  public void onPlayerChat(AsyncPlayerChatEvent event){
	  
	  if (event.isCancelled()){
		  return;
	  }
	  
	  if (event.getMessage().contains("%c")) {
		  Location loc = event.getPlayer().getLocation();
		  int x = loc.getBlockX();
		  int y = loc.getBlockY(); 
		  int z = loc.getBlockZ(); 
		  event.setMessage(event.getMessage().replaceAll("%c", x + "," + y + "," + z)); 
		  } 
	  
	  if (event.getMessage().contains("%p")){
		  event.setMessage(event.getMessage().replaceAll("%p", "ohsototes.com/?p=paragon"));
	  }
	  
	  if (event.getMessage().contains("%t")) { 
		  String town = this.plugin.WAAlliancesconfig.getString("Users." + event.getPlayer().getName() + ".Alliance"); 
		  
		  if (town == null) {
			  event.setMessage(event.getMessage().replaceAll("%t", "§7Forever§8Alone§r")); 
		  }
		  
	      if (town != null) { 
			
	    	  String c1 = "§" + this.plugin.WAAlliancesconfig.getString(new StringBuilder("Alliances.").append(town).append(".Color1").toString());
	    	  String c2 = "§" + this.plugin.WAAlliancesconfig.getString(new StringBuilder("Alliances.").append(town).append(".Color2").toString()); 
	    	  int midpoint = town.length() / 2; 
	    	  String firstHalf = town.substring(0, midpoint); 
	    	  String secondHalf = town.substring(midpoint); 
	    	  event.setMessage(event.getMessage().replaceAll("%t", c1 + firstHalf + c2 + secondHalf + "§r"));
	      }  
	      
	      } 
	  
	  Player p = event.getPlayer(); 
	  String message = event.getMessage(); 
	  event.setCancelled(true); 

	  Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', WCVault.chat.getPlayerPrefix(p)) + ChatColor.translateAlternateColorCodes('&', WCVault.chat.getPlayerSuffix(p)) + p.getDisplayName() + "§f: " + ChatColor.translateAlternateColorCodes('&', message));
	  	
	  	
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
 
	  

    switch (cmd.getName()) { 
    

 

    	
    	
    case "msg": case "tell": case "t":
     
        if ((args.length == 0) || (args.length == 1))
        {
          sender.sendMessage(this.WC + "It seems you were trying to message someone. Try /msg <player> <message> instead...");
          return true;
        }

        String message2 = createString2(args, 1);

        if (sender.getName().toLowerCase().contains(args[0].toLowerCase()))
        {
          sender.sendMessage(this.WC + "Welp, you're forever alone. #sendingmessagestomyself");
          return true;
        }

        Player p = (Player)sender;

        for (Player currentPlayer : Bukkit.getServer().getOnlinePlayers())
        {
          if (currentPlayer.getName().toLowerCase().contains(args[0].toLowerCase()))
          {
            this.plugin.datacore.set("Users." + Bukkit.getPlayer(args[0]).getName() + ".LastMessage", sender.getName());
            currentPlayer.sendMessage("§d<- " + p.getDisplayName() + " §f// §d" + ChatColor.translateAlternateColorCodes('&', message2));
            sender.sendMessage("§d-> " + currentPlayer.getDisplayName() + " §f// §d" + ChatColor.translateAlternateColorCodes('&', message2));
            break;
          }

        }

    break;
        
    case "r":

      Player p2 = (Player)sender;
      String message3 = createString(args, 0);
      String recent = this.plugin.datacore.getString("Users." + sender.getName() + ".LastMessage");

        for (Player currentPlayer : Bukkit.getServer().getOnlinePlayers())
        {
          if (currentPlayer.getName().toLowerCase().contains(((String)recent).toLowerCase()))
          {
            this.plugin.datacore.set("Users." + currentPlayer.getName() + ".LastMessage", sender.getName());
            currentPlayer.sendMessage("§d<- " + p2.getDisplayName() + " §f// §d" + ChatColor.translateAlternateColorCodes('&', (String)message3));
            sender.sendMessage("§d-> " + currentPlayer.getDisplayName() + " §f// §d" + ChatColor.translateAlternateColorCodes('&', (String)message3));
            break;
          }
        }
        
      break;
      
      }
      
  
    

  
    if ((cmd.getName().equalsIgnoreCase("d")) && ((sender instanceof Player)))
    {
      if (args.length <= 0)
      {
        sender.sendMessage(this.waaprefix + "Try /d -add <player>, /d -rem <player>, /d -list, or /d <message>.");
        return true;
      }

      if (args[0].equalsIgnoreCase("-list"))
      {
        if (!sender.hasPermission("wa.staff"))
        {
          sender.sendMessage(this.waaprefix + "You are not staff!");
          return true;
        }

        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.DiscussionChat.chatUsers");

        sender.sendMessage(this.waaprefix + "Chat Users:");

        for (String p : chatUsers)
        {
          if (Bukkit.getOfflinePlayer(p).isOnline())
          {
            sender.sendMessage(ChatColor.AQUA + p);
          }

        }

        return true;
      }

      if ((args[0].equalsIgnoreCase("-add")) && (args.length == 2))
      {
        if (!sender.hasPermission("wa.staff"))
        {
          sender.sendMessage(this.waaprefix + "You are not staff!");
          return true;
        }

        if (this.plugin.WAAlliancesconfig.getBoolean("Users." + args[1] + ".inDChat"))
        {
          sender.sendMessage(this.waaprefix + "They are already in discussion chat!");
          return true;
        }

        Player p2 = Bukkit.getPlayer(args[1]);

        if (p2 == null)
        {
          sender.sendMessage(this.waaprefix + "That user is not online.");
          return true;
        }

        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.DisussionChat.chatUsers");
        chatUsers.add(p2.getName());
        this.plugin.WAAlliancesconfig.set("Alliances.DiscussionChat.chatUsers", chatUsers);
        this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".inDChat", Boolean.valueOf(true));
        p2.sendMessage(this.waaprefix + "You can talk in discussion chat with /d <message> now!");

        for (String currentChat : chatUsers){

          if (Bukkit.getOfflinePlayer(currentChat).isOnline())
          {
            Bukkit.getPlayer(currentChat).sendMessage("§a§oDiscussion §2// " + p2.getDisplayName() + " has joined discussion chat!");
          }

        }

        return true;
      }

      if ((args[0].equalsIgnoreCase("-rem")) && (args.length == 2))
      {
        if (!sender.hasPermission("wa.staff"))
        {
          sender.sendMessage(this.waaprefix + "You are not staff!");
          return true;
        }

        if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + args[1] + ".inDChat"))
        {
          sender.sendMessage(this.waaprefix + "They have already left o chat!");
          return true;
        }

        Player p2 = Bukkit.getPlayer(args[1]);

        if (p2 == null)
        {
          sender.sendMessage(this.waaprefix + "That user is not online.");
          return true;
        }

        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.DiscussionChat.chatUsers");
        chatUsers.remove(p2.getName());
        this.plugin.WAAlliancesconfig.set("Alliances.DiscussionChat.chatUsers", chatUsers);
        this.plugin.WAAlliancesconfig.set("Users." + p2.getName() + ".inDChat", Boolean.valueOf(false));
        p2.sendMessage(this.waaprefix + "You've left discussion chat.");

        for (String currentChat : chatUsers){

          if (Bukkit.getOfflinePlayer(currentChat).isOnline())
          {
            Bukkit.getPlayer(currentChat).sendMessage("§a§oDiscussion §2// " + p2.getDisplayName() + " has left discussion chat!");
          }

        }

        return true;
      }

      if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".inDChat"))
      {
        Player p2 = (Player)sender;
        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.DiscussionChat.chatUsers");
        String message = createString(args, 1);

        for (String recent : chatUsers){

          if (Bukkit.getOfflinePlayer(recent).isOnline())
          {
            Bukkit.getPlayer(recent).sendMessage("§a§oDiscussion §2// " + p2.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.translateAlternateColorCodes('&', new StringBuilder().append(ChatColor.GREEN).append(message).toString()));
          }
        }

      }
      else
      {
        sender.sendMessage(this.waaprefix + "You are not in discusison chat.");
      }

    }

    if ((cmd.getName().equalsIgnoreCase("o")) && ((sender instanceof Player)))
    {
      if (args.length <= 0)
      {
        sender.sendMessage(this.waaprefix + "Try /o -join, /o -leave, or /o -list");
        return true;
      }

      if (args[0].equalsIgnoreCase("-list"))
      {
        if (!sender.hasPermission("wa.staff"))
        {
          sender.sendMessage(this.waaprefix + "You are not staff!");
          return true;
        }

        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.StaffChat.chatUsers");

        sender.sendMessage(this.waaprefix + "Chat Users:");

        for (String p : chatUsers)
        {
          if (Bukkit.getOfflinePlayer(p).isOnline())
          {
            sender.sendMessage(ChatColor.AQUA + p);
          }

        }

        return true;
      }

      if (args[0].equalsIgnoreCase("-join"))
      {
        if (!sender.hasPermission("wa.staff"))
        {
          sender.sendMessage(this.waaprefix + "You are not staff!");
          return true;
        }

        if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".inOChat"))
        {
          sender.sendMessage(this.waaprefix + "You are already in o chat!");
          return true;
        }

        Player p2 = (Player)sender;
        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.StaffChat.chatUsers");
        chatUsers.add(p2.getName());
        this.plugin.WAAlliancesconfig.set("Alliances.StaffChat.chatUsers", chatUsers);
        this.plugin.WAAlliancesconfig.set("Users." + p2.getName() + ".inOChat", Boolean.valueOf(true));
        sender.sendMessage(this.waaprefix + "You can talk in o chat with /o <message> now!");

        for (String p : chatUsers){

          if (Bukkit.getOfflinePlayer(p).isOnline())
          {
            Bukkit.getPlayer(p).sendMessage("§c§oOh! §4// " + p2.getDisplayName() + " has joined o chat!");
          }

        }

        return true;
      }

      if (args[0].equalsIgnoreCase("-leave"))
      {
        if (!sender.hasPermission("wa.staff"))
        {
          sender.sendMessage(this.waaprefix + "You are not staff!");
          return true;
        }

        if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".inOChat"))
        {
          sender.sendMessage(this.waaprefix + "You have already left o chat!");
          return true;
        }

        Player p2 = (Player)sender;
        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.StaffChat.chatUsers");
        chatUsers.remove(p2.getName());
        this.plugin.WAAlliancesconfig.set("Alliances.StaffChat.chatUsers", chatUsers);
        this.plugin.WAAlliancesconfig.set("Users." + p2.getName() + ".inOChat", Boolean.valueOf(false));
        sender.sendMessage(this.waaprefix + "You've left o chat.");

        for (String p : chatUsers){

          if (Bukkit.getOfflinePlayer(p).isOnline())
          {
            Bukkit.getPlayer(p).sendMessage("§c§oOh! §4// " + p2.getDisplayName() + " has left o chat!");
          }

        }

        return true;
      }

      if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".inOChat"))
      {
        Player p2 = (Player)sender;
        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances.StaffChat.chatUsers");
        String message = createString(args, 0);

        for (String p : chatUsers){

          if (Bukkit.getOfflinePlayer(p).isOnline())
          {
            Bukkit.getPlayer(p).sendMessage("§c§oOh! §4// " + p2.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.translateAlternateColorCodes('&', new StringBuilder().append(ChatColor.RED).append(message).toString()));
          }
        }

      }
      else
      {
        sender.sendMessage(this.waaprefix + "You are not in o chat. Try /o -join.");
      }

    }

    if ((cmd.getName().equalsIgnoreCase("l")) && ((sender instanceof Player)))
    {
      if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".inChat"))
      {
        Player p2 = (Player)sender;
        String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + sender.getName() + ".currentChat");
        List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + currentChat + ".chatUsers");
        String color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + currentChat + ".Color1");
        String color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + currentChat + ".Color2");
        int midpoint = currentChat.length() / 2;
        String firstHalf = currentChat.substring(0, midpoint);
        String secondHalf = currentChat.substring(midpoint);
        String firstHalfColors = "&" + color1 + firstHalf;
        String secondHalfColors = "&" + color2 + secondHalf;
        String allianceRank2 = this.plugin.WAAlliancesconfig.getString("Users." + sender.getName() + ".AllianceRank2");
        String message = createString(args, 0);

        for (String p : chatUsers)
        {
          if (Bukkit.getOfflinePlayer(p).isOnline())
          {
            Bukkit.getPlayer(p).sendMessage(ChatColor.translateAlternateColorCodes('&', firstHalfColors) + 
              ChatColor.translateAlternateColorCodes('&', secondHalfColors) + ChatColor.GRAY + " // " + ChatColor.DARK_GRAY + "*" + ChatColor.GRAY + 
              allianceRank2 + ChatColor.DARK_GRAY + "* " + p2.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.translateAlternateColorCodes('&', new StringBuilder().append(ChatColor.AQUA).append(message).toString()));
          }
        }

      }
      else
      {
        sender.sendMessage(this.waaprefix + "You are not in a channel. Check out /waa help");
      }

    }

    return true;
  }

public static String createString(String[] args, int i)
  {
    StringBuilder sb = new StringBuilder();
    for (i = 0; i < args.length; i++)
    {
      if ((i != args.length) && (i != 0))
      {
        sb.append(" ");
      }
      sb.append(args[i]);
    }
    String message = sb.toString();
    return message;
  }

  private String createString2(String[] args, int i)
  {
    StringBuilder sb = new StringBuilder();
    for (i = 1; i < args.length; i++)
    {
      if ((i != args.length) && (i != 1))
      {
        sb.append(" ");
      }
      sb.append(args[i]);
    }
    String message = sb.toString();
    return message;
  }
}