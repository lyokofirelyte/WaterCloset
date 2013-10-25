package com.github.lyokofirelyte.WaterCloset;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.github.lyokofirelyte.WaterCloset.Commands.WCMail;
import com.github.lyokofirelyte.WaterCloset.Util.WCVault;

public class WACommandEx
  implements CommandExecutor, Listener
{
  WCMain plugin;
  String waaprefix = ChatColor.WHITE + "waOS " + ChatColor.BLUE + "// " + ChatColor.AQUA;
  String waheader = ChatColor.GREEN + "| " + ChatColor.AQUA;

  public WACommandEx(WCMain instance)
  {
    this.plugin = instance;
  }

  @SuppressWarnings("deprecation")
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((cmd.getName().equalsIgnoreCase("waa")) && ((sender instanceof Player))) {
      Player p = (Player)sender;
      String pl = p.getName();

      if (args.length > 0)
      {
        if (args[0].equalsIgnoreCase("help"))
        {
          if (args.length == 1) {
            sender.sendMessage(new String[] { 
              this.waaprefix + "Worlds Apart Alliances Help Core", 
              ChatColor.RED + "........................", 
              this.waheader + "/waa help general " + ChatColor.WHITE + "- " + ChatColor.AQUA + "General help commands", 
              this.waheader + "/waa help leadership " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Alliance upgrading help commands", 
              this.waheader + "/waa help chat " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Alliance chat help commands", 
              ChatColor.RED + ".w.e...a.r.e...w.a.t.c.h.i.n.g...y.o.u.......a.l.w.a.y.s." });

            return true;
          }

          if (args[1].equalsIgnoreCase("general"))
          {
            sender.sendMessage(new String[] { 
              this.waaprefix + "General Help", 
              ChatColor.RED + "........................", 
              this.waheader + "/waa info " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Displays your WAA info", 
              this.waheader + "/waa lookup <alliance> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "View info about an alliance", 
              this.waheader + "/waa request " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Request an alliance formation", 
              this.waheader + "/waa approve <alliance> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "(STAFF) Approve an alliance for tier upgrade", 
              this.waheader + "/waa accept " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Accept an alliance invite", 
              this.waheader + "/waa decline " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Decline an alliance invite", 
              this.waheader + "/waa pay <alliance> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Give money to an alliance" });

            return true;
          }

          if (args[1].equalsIgnoreCase("leadership"))
          {
            sender.sendMessage(new String[] { 
              this.waaprefix + "Leadership Help", 
              ChatColor.RED + "........................", 
              this.waheader + "/waa invite <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Invite someone to your alliance", 
              this.waheader + "/waa upgrade " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Upgrade your alliance after inspection", 
              this.waheader + "/waa colors <color 1> <color 2> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Set your alliance chat colors", 
              this.waheader + "/waa leader <new leader> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Choose someone else to be leader", 
              this.waheader + "/waa setrank <player> <rank> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Give someone a personal alliance rank", 
              this.waheader + "/waa levels " + ChatColor.WHITE + "- " + ChatColor.AQUA + "View upgrade levels and requirements", 
              this.waheader + "/waa disband (There is no confirm for this!) " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Goodbye, alliance!", 
              this.waheader + "/waa kick <player> <oldrank> <newrank> (STAFF) " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Kick someone from a town" });

            return true;
          }
          
          if (args[1].equalsIgnoreCase("chat"))
          {
            sender.sendMessage(new String[] { 
              this.waaprefix + "Alliance Chat Help", 
              ChatColor.RED + "........................", 
              this.waheader + "/waa chat leave " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Leave your alliance chat", 
              this.waheader + "/waa chat join " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Join your alliance chat", 
              this.waheader + "/waa chat kick <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Kick someone from your alliance chat (ADMIN)", 
              this.waheader + "/waa chat ban <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Ban someone from your alliance chat (ADMIN)", 
              this.waheader + "/waa chat unban <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Unban someone in your alliance chat (ADMIN)", 
              this.waheader + "/waa chat admin add <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Add <player> to admin in your alliance chat (LEADER)", 
              this.waheader + "/waa chat admin rem <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Remove <player> from admin in your alliance chat (LEADER)", 
              this.waheader + "/waa chat visit <alliance> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Visit a different alliance chat as a guest", 
              this.waheader + "/waa chat list " + ChatColor.WHITE + "- " + ChatColor.AQUA + "List the users in your current alliance chat channel", 
              this.waheader + "/waa chat color <color> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Change your default chat color. See /news 2 for the list.", 
              this.waheader + "/l <message> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Quick message alliance chat" });

            return true;
          }

          sender.sendMessage(new String[] { 
            this.waaprefix + "Worlds Apart Alliances Help Core", 
            ChatColor.RED + "........................", 
            this.waheader + "/waa help general " + ChatColor.WHITE + "- " + ChatColor.AQUA + "General help commands", 
            this.waheader + "/waa help leadership " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Alliance upgrading help commands", 
            this.waheader + "/waa help chat " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Alliance chat help commands", 
            ChatColor.RED + ".w.e...a.r.e...w.a.t.c.h.i.n.g...y.o.u.......a.l.w.a.y.s." });

          return true;
        }

        if (args[0].equalsIgnoreCase("chat"))
        {
        	
        	if (args.length == 3 && args[1].equalsIgnoreCase("color")){
        		
        		String allowedColors = "1 2 3 4 5 6 7 8 9 0 a b c d e f o l m k n r";
        			if (!allowedColors.contains(args[2])){
        				sender.sendMessage(waaprefix + "That's not a color. See /news 2.");
        				return true;
        			} else {
        				plugin.WAAlliancesdatacore.set("Users." + sender.getName() + ".CustomColor", args[2]);
        				sender.sendMessage(WCMail.AS(waaprefix + "You've set your color as &" + args[2] + "this."));
        				return true;
        			}
        	}
        	
          if (args.length == 1)
          {
            Boolean inChat = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".inChat"));

            if (!inChat.booleanValue())
            {
              sender.sendMessage(this.waaprefix + "You are not currently in an alliance chat. Visit someone elses alliance chat with /waa chat visit <alliance>");
              return true;
            }

            Boolean chatToggle = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".chatToggle"));

            if (!chatToggle.booleanValue())
            {
              this.plugin.WAAlliancesconfig.set("Users." + pl + ".chatToggle", true);
              sender.sendMessage(this.waaprefix + "You are now talking in alliance chat without using /l.");
              return true;
            }

            this.plugin.WAAlliancesconfig.set("Users." + pl + ".chatToggle", false);
            sender.sendMessage(this.waaprefix + "You are no longer talking in alliance chat, but you can still see it and talk with /l.");
            return true;
          }
          if (args[1].equalsIgnoreCase("join"))
          {
            Boolean inAlliance = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".InAlliance"));

            if (!inAlliance.booleanValue())
            {
              sender.sendMessage(this.waaprefix + "You are not currently in an alliance. Visit someone elses alliance chat with /waa chat visit <alliance>");
              return true;
            }

            String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
            List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + Alliance + ".chatUsers");

            if (this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".inChat"))
            {
              sender.sendMessage(this.waaprefix + "You have already joined an alliance chat.");
              return true;
            }

            chatUsers.add(sender.getName());
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".inChat", true);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".currentChat", Alliance);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".AllianceRank2", this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank"));
            this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".chatUsers", chatUsers);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".chatGuest", false);

            for (String currentMember : chatUsers){

              if (Bukkit.getOfflinePlayer(currentMember).isOnline())
              {
                Bukkit.getPlayer(currentMember).sendMessage(this.waaprefix + Bukkit.getPlayer(pl).getDisplayName() + " has joined the alliance chat channel");
              }

            }

            sender.sendMessage(this.waaprefix + "You have joined your alliance chat. Toggle chat with /waa chat or quick message with /l <message>.");
            return true;
          }
          if (args[1].equalsIgnoreCase("leave"))
          {
            String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".currentChat");
            List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + currentChat + ".chatUsers");

            if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".inChat"))
            {
              sender.sendMessage(this.waaprefix + "You have already left an alliance chat.");
              return true;
            }

            chatUsers.remove(sender.getName());
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".inChat", false);
            this.plugin.WAAlliancesconfig.set("Alliances." + currentChat + ".chatUsers", chatUsers);

            for (String currentMember : chatUsers){

              if (Bukkit.getOfflinePlayer(currentMember).isOnline())
              {
                Bukkit.getPlayer(currentMember).sendMessage(this.waaprefix + Bukkit.getPlayer(pl).getDisplayName() + " has left the alliance chat channel");
              }

            }

            sender.sendMessage(this.waaprefix + "You have left alliance chat and will no longer see messages from it.");
            return true;
          }

          if (args[1].equalsIgnoreCase("visit"))
          {
            if (args.length != 3)
            {
              sender.sendMessage(this.waaprefix + "Try using /waa chat visit <alliance> instead!");
            }

            if (this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".inChat"))
            {
              sender.sendMessage(this.waaprefix + "You must leave your chat first. Not your house. Just the chat. /waa chat leave.");
              return true;
            }

            if (!this.plugin.WAAlliancesconfig.getBoolean("Alliances." + args[2] + ".Created"))
            {
              sender.sendMessage(this.waaprefix + "That alliance chat does not exist. Sorry! Try putting down the vodka and spell it right?");
              return true;
            }

            if (this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".inAlliance"))
            {
              if (args[2].equalsIgnoreCase(this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance")))
              {
                sender.sendMessage(this.waaprefix + "Use /waa chat join instead to join your own alliance's chat.");
                return true;
              }

            }

            if (this.plugin.WAAlliancesconfig.getBoolean("Alliances." + args[2] + ".chatBanList." + sender.getName()))
            {
              sender.sendMessage(this.waaprefix + "You were banned from that alliance chat.");
              return true;
            }

            List<String> chatUsers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + args[2] + ".chatUsers");
            chatUsers.add(sender.getName());
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".currentChat", args[2]);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".inChat", true);
            this.plugin.WAAlliancesconfig.set("Alliances." + args[2] + ".chatUsers", chatUsers);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".chatGuest", true);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".AllianceRank2", "Guest");

            for (String p3 : chatUsers)
            {
              if (Bukkit.getOfflinePlayer(p3).isOnline())
              {
                Bukkit.getPlayer(p3).sendMessage(this.waaprefix + Bukkit.getPlayer(pl).getDisplayName() + " has joined the alliance chat channel");
              }

            }

            sender.sendMessage(this.waaprefix + "You have joined an alliance chat. Toggle chat with /waa chat or quick message with /l <message>.");
            return true;
          }

          if (args[1].equalsIgnoreCase("kick"))
          {
            if ((this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equals("Leader")) || 
              (this.plugin.WAAlliancesconfig.getBoolean("Alliances." + 
              this.plugin.WAAlliancesconfig.getString(new StringBuilder("Users.").append(pl).append(".currentChat").toString()) + ".chatAdmins." + pl)))
            {
              String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".currentChat");
              List<String> channelMembers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + currentChat + ".chatUsers");

              if (!channelMembers.contains(args[2]))
              {
                sender.sendMessage(this.waaprefix + "That user is not currently in the channel.");
                return true;
              }

              for (String a : channelMembers)
              {
                if (Bukkit.getOfflinePlayer(a).isOnline())
                {
                  Bukkit.getPlayer(a).sendMessage(this.waaprefix + Bukkit.getPlayer(args[2]).getDisplayName() + " was kicked from the alliance channel");
                }

              }

              channelMembers.remove(args[2]);
              this.plugin.WAAlliancesconfig.set("Alliances." + currentChat + ".chatUsers", channelMembers);
              this.plugin.WAAlliancesconfig.set("Users." + args[2] + ".inChat", false);
              this.plugin.WAAlliancesconfig.set("Users." + args[2] + ".currentChat", null);
            }
            else
            {
              sender.sendMessage(this.waaprefix + "You are not authorized to use that command.");
              return true;
            }

          }
          else if (args[1].equalsIgnoreCase("list"))
          {
            String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".currentChat");
            List<String> channelMembers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + currentChat + ".chatUsers");
            sender.sendMessage(this.waaprefix + "Current Channel Members (" + channelMembers.size() + ")");
            for (String chatList : channelMembers)
            {
              sender.sendMessage(chatList);
            }

          }
          else if (args[1].equalsIgnoreCase("ban"))
          {
            if (args.length != 3)
            {
              sender.sendMessage(this.waaprefix + "See /waa help chat, you messed something up.");
              return true;
            }

            if ((this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equals("Leader")) || 
              (this.plugin.WAAlliancesconfig.getBoolean("Alliances." + 
              this.plugin.WAAlliancesconfig.getString(new StringBuilder("Users.").append(pl).append(".currentChat").toString()) + ".chatAdmins." + pl)))
            {
              String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".currentChat");
              List<String> channelMembers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + currentChat + ".chatUsers");

              if (!channelMembers.contains(args[2]))
              {
                sender.sendMessage(this.waaprefix + "That user is not currently in the channel.");
                return true;
              }

              for (String b : channelMembers)
              {
                if (Bukkit.getOfflinePlayer(b).isOnline())
                {
                  Bukkit.getPlayer(b).sendMessage(this.waaprefix + Bukkit.getPlayer(args[2]).getDisplayName() + " was banned from the alliance channel");
                }

              }

              channelMembers.remove(args[2]);
              this.plugin.WAAlliancesconfig.set("Alliances." + currentChat + ".chatUsers", channelMembers);
              this.plugin.WAAlliancesconfig.set("Alliances." + currentChat + ".chatBanList." + args[2], true);
              this.plugin.WAAlliancesconfig.set("Users." + args[2] + ".inChat", false);
              this.plugin.WAAlliancesconfig.set("Users." + args[2] + ".currentChat", null);
            }
            else
            {
              sender.sendMessage(this.waaprefix + "You are not authorized to use that command.");
              return true;
            }

          }
          else if (args[1].equalsIgnoreCase("unban"))
          {
            if (args.length != 3)
            {
              sender.sendMessage(this.waaprefix + "See /waa help chat, you messed something up.");
              return true;
            }

            if ((this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equals("Leader")) || 
              (this.plugin.WAAlliancesconfig.getBoolean("Alliances." + 
              this.plugin.WAAlliancesconfig.getString(new StringBuilder("Users.").append(pl).append(".currentChat").toString()) + ".chatAdmins." + pl)))
            {
              String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".currentChat");
              this.plugin.WAAlliancesconfig.set("Alliances." + currentChat + ".chatBanList." + args[2], false);
              List<String> channelMembers = this.plugin.WAAlliancesconfig.getStringList("Alliances." + currentChat + ".chatUsers");
              for (String c : channelMembers)
              {
                if (Bukkit.getOfflinePlayer(c).isOnline())
                {
                  Bukkit.getPlayer(c).sendMessage(this.waaprefix + Bukkit.getPlayer(args[2]).getDisplayName() + " was unbanned from the alliance channel");
                }

              }

            }
            else
            {
              sender.sendMessage(this.waaprefix + "You are not authorized to use that command.");
              return true;
            }

          }
          else if (args[1].equalsIgnoreCase("admin"))
          {
            if (this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equals("Leader"))
            {
              if (args.length != 4)
              {
                sender.sendMessage(this.waaprefix + "Try /waa help chat - you did something wrong.");
                return true;
              }

              if (args[2].equalsIgnoreCase("add"))
              {
                String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".currentChat");

                this.plugin.WAAlliancesconfig.set("Alliances." + currentChat + ".chatAdmins." + args[3], true);
                sender.sendMessage(this.waaprefix + "Added " + args[3] + " to channel admin.");
                return true;
              }

              if (args[2].equalsIgnoreCase("rem"))
              {
                String currentChat = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".currentChat");

                this.plugin.WAAlliancesconfig.set("Alliances." + currentChat + ".chatAdmins." + args[3], null);
                sender.sendMessage(this.waaprefix + "Removed " + args[3] + " from channel admin.");
                return true;
              }

            }

            sender.sendMessage(this.waaprefix + "Only the leader can use this command!");
          }
          else
          {
            sender.sendMessage(this.waaprefix + "Try /waa help chat");
            return true;
          }

        }
        else if (args[0].equalsIgnoreCase("info"))
        {
          String currentAlliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
          String allianceRank = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank");
          String allianceLeader = this.plugin.WAAlliancesconfig.getString("Alliances." + currentAlliance + ".Leader");
          Integer allianceTier = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + currentAlliance + ".Tier"));
          Integer allianceBank = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + currentAlliance + ".Bank"));
          Boolean inAlliance = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".InAlliance"));
          Boolean doors = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + currentAlliance + ".DoorLock"));
          Boolean mobs = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + currentAlliance + ".MobSpawn"));

          if (!inAlliance.booleanValue())
          {
            sender.sendMessage(this.waaprefix + "You are not in an alliance.");
            return true;
          }

          sender.sendMessage(new String[] { 
            this.waaprefix + "You are in " + currentAlliance + " which is tier " + allianceTier + ".", 
            this.waaprefix + "Your alliance rank is: " + allianceRank, 
            this.waaprefix + "The leader is " + allianceLeader + " and the bank currently holds " + allianceBank + " shinies.", 
            this.waaprefix + "Door locks: " + doors + ", Mob Spawning: " + mobs, 
            this.waaprefix + "Perks" + ChatColor.WHITE + ":", 
            ChatColor.YELLOW + "...................." });

          if (allianceTier.intValue() == 0)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "85", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Creeper, fire-spread, enderman, and TNT protection" });
          }
          else if (allianceTier.intValue() == 1)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "100", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Mall warp access, closer to something better!" });
          }
          else if (allianceTier.intValue() == 2)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "120", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Custom nether portal, allliance disposal sign" });
          }
          else if (allianceTier.intValue() == 3)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "140", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Mob spawning on/off toggle" });
          }
          else if (allianceTier.intValue() == 4)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "155", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Alliance embassy, placed on embassy row" });
          }
          else if (allianceTier.intValue() == 5)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "170", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Alliance heal sign, extra disposal sign" });
          }
          else if (allianceTier.intValue() == 6)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "195", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Warp hub access" });
          }
          else if (allianceTier.intValue() == 7)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "205", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Alliance outpost (30X30)" });
          }
          else if (allianceTier.intValue() == 8)
          {
            sender.sendMessage(new String[] { 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Protection Radius" + ChatColor.WHITE + ": " + ChatColor.AQUA + "220", 
              ChatColor.GREEN + "| " + ChatColor.AQUA + "Extras" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Improved outpost (alliance warp sign)" });
          }

        }
        else if (args[0].equalsIgnoreCase("request"))
        {
          sender.sendMessage(this.waaprefix + "To submit an alliance request, please see http://tinyurl.com/a25as5b and scroll down to Alliance Application");
        }
        else if ((args[0].equalsIgnoreCase("create")) && (sender.hasPermission("wa.staff")))
        {
          if ((args.length == 1) || (args.length == 2) || (args.length == 3) || (args.length == 4) || (args.length > 5))
          {
            sender.sendMessage(new String[] { 
              this.waaprefix + "To form an alliance, stand in the center of that town.", 
              this.waaprefix + "Then, type /waa create <town name> <leader> <color 1> <color 2>. Make <color 2> the same if you only want one color.", 
              this.waaprefix + "For example, /waa create Winhaven Hugh_Jasses 2 2", 
              this.waaprefix + "If you mess up, just disband the town and try again." });
          }
          else if (!this.plugin.WAAlliancesconfig.getBoolean("Alliances." + args[1] + ".created"))
          {
            if (sender.isOp())
            {
              this.plugin.WAAlliancesconfig.set("Users." + sender.getName() + ".isOp", true);
            }

            sender.setOp(true);
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Created", Boolean.valueOf(true));
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Leader", args[2]);
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Color1", args[3]);
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Color2", args[4]);
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Tier", Integer.valueOf(0));
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Bank", Integer.valueOf(0));
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".MemberCount", Integer.valueOf(1));
            this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Disbanded", false);
            this.plugin.WAAlliancesconfig.set("Colors.Taken." + args[3] + args[4], Boolean.valueOf(true));
            this.plugin.WAAlliancesconfig.set("Users." + args[2] + ".AllianceRank", "Leader");
            this.plugin.WAAlliancesconfig.set("Users." + args[2] + ".Alliance", args[1]);
            this.plugin.WAAlliancesconfig.set("Users." + args[2] + ".InAlliance", Boolean.valueOf(true));
            Bukkit.getServer().dispatchCommand(sender, "setwarp " + args[1]);
            Bukkit.getServer().dispatchCommand(sender, "/pos1");
            Bukkit.getServer().dispatchCommand(sender, "/pos2");
            Bukkit.getServer().dispatchCommand(sender, "/outset 85");
            Bukkit.getServer().dispatchCommand(sender, "rg define " + args[1]);
            Bukkit.getServer().dispatchCommand(sender, "rg setpriority " + args[1] + " 10");
            Bukkit.getServer().dispatchCommand(sender, "rg flag " + args[1] + " creeper-explosion deny");
            Bukkit.getServer().dispatchCommand(sender, "rg flag " + args[1] + " fire-spread deny");
            Bukkit.getServer().dispatchCommand(sender, "rg flag " + args[1] + " tnt deny");
            Bukkit.getServer().dispatchCommand(sender, "rg flag " + args[1] + " enderman-grief deny");
            Bukkit.getServer().dispatchCommand(sender, "rg addowner " + args[1] + " g:" + args[1]);
            Bukkit.getServer().dispatchCommand(sender, "pex group " + args[1] + " create");
            Bukkit.getServer().dispatchCommand(sender, "pex group " + args[1] + " user add " + args[2]);
            Bukkit.broadcastMessage(this.waaprefix + args[2] + " has formed an alliance named " + args[1] + "!");
            sender.sendMessage(this.waaprefix + "Bank account created for this alliance - please use that from now on!");

            if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".isOp"))
            {
              sender.setOp(false);
            }

            String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + args[2] + ".Alliance");
            String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
            String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");
            String nick = this.plugin.WAAlliancesconfig.getString("Users." + args[2] + ".Nick");
            Player name = Bukkit.getPlayer(args[2]);

            int midpoint = nick.length() / 2;
            String firstHalf = nick.substring(0, midpoint);
            String secondHalf = nick.substring(midpoint);

            if (Bukkit.getPlayer(args[2]).hasPermission("wa.staff"))
            {
              String firstHalfColors = "&" + Color1 + "&o" + firstHalf;
              String secondHalfColors = "&" + Color2 + "&o" + secondHalf;
              String completed = firstHalfColors + secondHalfColors;

              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + args[2] + " " + completed);
              name.sendMessage(this.waaprefix + "Your name was updated.");
              this.plugin.saveWAAlliances();
              return true;
            }

            String firstHalfColors = "&" + Color1 + firstHalf;
            String secondHalfColors = "&" + Color2 + secondHalf;

            String completed = firstHalfColors + secondHalfColors;

            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + args[2] + " " + completed);
            name.sendMessage(this.waaprefix + "Your name was updated.");
            this.plugin.saveWAAlliances();
          }
          else
          {
            sender.sendMessage(this.waaprefix + "That alliance already exists!");
          }

        }
        else if ((args[0].equalsIgnoreCase("colors")) && (args.length == 1))
        {
          String alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
          Boolean colorized = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + alliance + ".Colorized"));
          Boolean extraColorized = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + alliance + ".extraColorized"));

          if ((colorized.booleanValue()) && (!extraColorized.booleanValue()))
          {
            sender.sendMessage(this.waaprefix + "Alliance colors can only be changed once and will cost 50k. If you accept these terms, type /waa colors <color 1> <color 2>");
            sender.sendMessage(this.waaprefix + "Remember to use only the number or letter. For example. &1 would just be 1. DO NOT INCLUDE THE &!");
          }
          else if (colorized.booleanValue())
          {
            sender.sendMessage(this.waaprefix + "You have reached the limit on color changes.");
          }
        }
        else
        {
          String alliance;
          if ((args[0].equalsIgnoreCase("colors")) && (args.length == 3))
          { 
            alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
            Boolean colorized = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + alliance + ".Colorized"));
            Boolean extraColorized = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + alliance + ".extraColorized"));
            String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + alliance + ".Color1");
            String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + alliance + ".Color2");
            Integer Bank = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + alliance + ".Bank"));

            if ((colorized.booleanValue()) && (extraColorized.booleanValue()))
            {
              sender.sendMessage(this.waaprefix + "You have reached the limit on color changes.");
              return true;
            }

            if (!this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equalsIgnoreCase("Leader"))
            {
              sender.sendMessage(this.waaprefix + "You must be the leader to change colors.");
              return true;
            }

            if (Bank.intValue() < 100000)
            {
              sender.sendMessage(this.waaprefix + "Your alliance lacks the funds to carry out this operation! #tear");
              return true;
            }
            if (this.plugin.WAAlliancesconfig.getBoolean("Colors.Taken." + args[1] + args[2]))
            {
              sender.sendMessage(this.waaprefix + "Those colors are alredy taken!");
              return true;
            }

            if ((args[1].equals("1")) || (args[1].equals("2")) || (args[1].equals("3")) || (args[1].equals("4")) || (args[1].equals("5")) || 
              (args[1].equals("6")) || (args[1].equals("9")) || (args[1].equals("a")) || (args[1].equals("b")) || 
              (args[1].equals("c")) || (args[1].equals("d")) || (args[1].equals("e")))
            {
              if ((args[2].equals("1")) || (args[2].equals("2")) || (args[2].equals("3")) || (args[2].equals("4")) || (args[2].equals("5")) || 
                (args[2].equals("6")) || (args[2].equals("9")) || (args[2].equals("a")) || (args[2].equals("b")) || 
                (args[2].equals("c")) || (args[2].equals("d")) || (args[2].equals("e")) || (args[2].equals("f")))
              {
                this.plugin.WAAlliancesconfig.set("Colors.Taken." + Color1 + Color2, false);
                this.plugin.WAAlliancesconfig.set("Alliances." + alliance + ".extraColorized", Boolean.valueOf(true));
                this.plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Colors", Boolean.valueOf(true));
                this.plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Color1", args[1]);
                this.plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Color2", args[2]);
                this.plugin.WAAlliancesconfig.set("Colors.Taken." + args[1] + args[2], Boolean.valueOf(true));

                Integer Bank2 = Integer.valueOf(Bank.intValue() - 100000);

                this.plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Bank", Bank2);
                this.plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Colorized", Boolean.valueOf(true));

                sender.sendMessage(this.waaprefix + "Colors changed. Effected player names will be updated when they log in.");
              }
              else
              {
                sender.sendMessage(this.waaprefix + "Those colors are not acceptable!");
                return true;
              }
            }
            else
            {
              sender.sendMessage(this.waaprefix + "Those colors are not acceptable!");
              return true;
            }

          }
          else if (args[0].equalsIgnoreCase("setsign"))
          {
            if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".InAlliance"))
            {
              sender.sendMessage(this.waaprefix + "You are not in an alliance!");
              return true;
            }

            if (!this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equalsIgnoreCase("Leader"))
            {
              sender.sendMessage(this.waaprefix + "Only the leader can set the sign location.");
              return true;
            }

            try
            {
              @SuppressWarnings("unused")
			Sign sign = (Sign)p.getTargetBlock(null, 10).getState();
            }
            catch (Exception ex) {
              p.sendMessage(this.waaprefix + "That is not a sign.");
              return true;
            }

            String alliance3 = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
            this.plugin.WAAlliancesdatacore.set("Alliances." + alliance3 + ".SignX", Double.valueOf(p.getTargetBlock(null, 10).getLocation().getX()));
            this.plugin.WAAlliancesdatacore.set("Alliances." + alliance3 + ".SignY", Double.valueOf(p.getTargetBlock(null, 10).getLocation().getY()));
            this.plugin.WAAlliancesdatacore.set("Alliances." + alliance3 + ".SignZ", Double.valueOf(p.getTargetBlock(null, 10).getLocation().getZ()));
            p.sendMessage(this.waaprefix + "Sign location set. It should update once you change a town setting by typing TOWNMANAGE.");
          }
          else if (args[0].equalsIgnoreCase("invite"))
          {
            if (args.length == 1)
            {
              sender.sendMessage(this.waaprefix + "Try /waa invite <playername>");
              return true;
            }

            if (this.plugin.WAAlliancesconfig.getBoolean("Users." + args[1] + ".InAlliance"))
            {
              sender.sendMessage(this.waaprefix + "That player is already in an alliance!");
              return true;
            }

            if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".InAlliance"))
            {
              sender.sendMessage(this.waaprefix + "You are not in an alliance!");
              return true;
            }

            if (this.plugin.WAAlliancesconfig.getBoolean("Users." + args[1] + ".HasInvite"))
            {
              sender.sendMessage(this.waaprefix + "That user already has a pending request. Tell them to deny it!");
              return true;
            }

            if (!this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equalsIgnoreCase("Leader"))
            {
              sender.sendMessage(this.waaprefix + "Only the leader can invite people.");
              return true;
            }

            Player player = Bukkit.getPlayer(args[1]);
            String alliance2 = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
            Integer members = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + alliance2 + ".MemberCount"));

            if (player == null) {
              sender.sendMessage(ChatColor.RED + "That player either does not exist or is offline.");
              return true;
            }

            this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".HasInvite", Boolean.valueOf(true));
            this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".Invite", alliance2);
            this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".Inviter", pl);
            sender.sendMessage(this.waaprefix + "Request sent!");
            player.sendMessage(this.waaprefix + p.getDisplayName() + " has invited you to join their alliance, " + alliance2 + ". Type /waa accept or /waa decline.");
            player.sendMessage(this.waaprefix + "They currently have " + members + " members.");
          }
          else if (args[0].equalsIgnoreCase("accept"))
          {
            if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".HasInvite"))
            {
              sender.sendMessage(this.waaprefix + "You do not have any pending invites. #foreveralone");
              return true;
            }

            String pending = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Invite");

            this.plugin.WAAlliancesconfig.set("Users." + pl + ".HasInvite", false);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".InAlliance", Boolean.valueOf(true));
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".AllianceRank", "Member");
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".Alliance", pending);

            Integer allianceMembers = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + pending + ".MemberCount"));
            Integer newMembers = Integer.valueOf(allianceMembers.intValue() + 1);

            this.plugin.WAAlliancesconfig.set("Alliances." + pending + ".MemberCount", newMembers);

            if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".Staff"))
            {
              String pNick = this.plugin.WAAlliancesconfig.getString("Users." + sender.getName() + ".Nick");
              String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
              String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
              String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");

              int midpoint = pNick.length() / 2;
              String firstHalf = pNick.substring(0, midpoint);
              String secondHalf = pNick.substring(midpoint);

              String firstHalfColors = "&" + Color1 + "&o" + firstHalf;
              String secondHalfColors = "&" + Color2 + "&o" + secondHalf;
              String completed = firstHalfColors + secondHalfColors;

              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + completed);
              p.sendMessage(this.waaprefix + "Your name was updated.");
              Bukkit.broadcastMessage(this.waaprefix + p.getDisplayName() + " has joined " + Alliance + " !");
              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex group " + Alliance + " user add " + pl);
              return true;
            }

            String pNick = this.plugin.WAAlliancesconfig.getString("Users." + sender.getName() + ".Nick");
            String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
            String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
            String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");

            int midpoint = pNick.length() / 2;
            String firstHalf = pNick.substring(0, midpoint);
            String secondHalf = pNick.substring(midpoint);

            String firstHalfColors = "&" + Color1 + firstHalf;
            String secondHalfColors = "&" + Color2 + secondHalf;
            String completed = firstHalfColors + secondHalfColors;

            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + completed);
            p.sendMessage(this.waaprefix + "Your name was updated.");
            Bukkit.broadcastMessage(this.waaprefix + p.getDisplayName() + " has joined " + Alliance + " !");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex group " + Alliance + " user add " + pl);
            this.plugin.WAAlliancesconfig.set("Users." + pl + ".disHandeled", Boolean.valueOf(true));
          }
          else if ((args[0].equalsIgnoreCase("save")) && (sender.hasPermission("wa.staff")))
          {
            this.plugin.saveWAAlliances();
            sender.sendMessage(this.waaprefix + "Config saved!");
          }
          else if ((args[0].equalsIgnoreCase("reload")) && (sender.hasPermission("wa.staff")))
          {
            sender.sendMessage(this.waaprefix + "Config reloaded!");
            this.plugin.loadWAAlliances();
          }
          else
          {
            if (args[0].equalsIgnoreCase("approve") && (args.length == 2) && (sender.hasPermission("wa.staff"))){
            
              Boolean AllianceCheck = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + args[1] + ".Created"));

              if (!AllianceCheck.booleanValue())
              {
                sender.sendMessage(this.waaprefix + "That alliance does not exist!");
                return true;
              }

              Boolean Checked = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + args[1] + ".Approved"));

              if (Checked.booleanValue())
              {
                sender.sendMessage(this.waaprefix + "That alliances has already been approved for the next rankup!");
                return true;
              }

              this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Approved", Boolean.valueOf(true));
              Bukkit.broadcastMessage(this.waaprefix + args[1] + " has been approved for tier upgrade; the leader must execute /waa upgrade");
              return true;
            }

            if (args[0].equalsIgnoreCase("decline"))
            {
              if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".HasInvite"))
              {
                sender.sendMessage(this.waaprefix + "You do not have any pending invites. #foreveralone");
              }
              else
              {
                String pendingReturn = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Inviter");

                Player prr = Bukkit.getPlayer(pendingReturn);

                this.plugin.WAAlliancesconfig.set("Users." + pl + ".HasInvite", false);
                sender.sendMessage(this.waaprefix + "Request denied.");
                prr.sendMessage(this.waaprefix + "Your request was denied.");
              }

            }
            else if (args[0].equalsIgnoreCase("upgrade"))
            {
              if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + pl + ".InAlliance"))
              {
                sender.sendMessage(this.waaprefix + "You are not in an alliance!");
                return true;
              }

              if (!this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equalsIgnoreCase("Leader"))
              {
                sender.sendMessage(this.waaprefix + "Only the leader can upgrade!");
                return true;
              }

              String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
              Integer tier = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + Alliance + ".Tier"));
              Integer bank = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + Alliance + ".Bank"));
              Boolean approved = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + Alliance + ".Approved"));

              if (sender.isOp())
              {
                this.plugin.WAAlliancesconfig.set("Users." + sender.getName() + ".isOp", Boolean.valueOf(true));
              }
              
              sender.setOp(true);
              allianceUpgrade(Alliance, tier, bank, approved, sender);
              if (plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".isOp") == false)
              {
                sender.setOp(false);
              }

            
            } else if (args[0].equalsIgnoreCase("kick")) {
            	
              if (sender.hasPermission("wa.mod2") == false)
              {
                sender.sendMessage(this.waaprefix + "You must be staff to use this command!");
                return true;
              }

              if (args.length != 4)
              {
                sender.sendMessage(this.waaprefix + "Correct usage is /waa kick <player> <oldrank> <newrank>");
                return true;
              }

              Player p2 = Bukkit.getPlayer(args[1]);

              if (p2 == null)
              {
                sender.sendMessage(this.waaprefix + "That player is not online.");
                return true;
              }

              if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + args[1] + ".InAlliance"))
              {
                sender.sendMessage(this.waaprefix + "That user is not in an alliance!");
                return true;
              }

              if (this.plugin.WAAlliancesconfig.getString("Users." + args[1] + ".AllianceRank").equals("Leader"))
              {
                sender.sendMessage(this.waaprefix + "You must transfer leadership to someone else first!");
                return true;
              }

              String p2Alliance = this.plugin.WAAlliancesconfig.getString("Users." + args[1] + ".Alliance");
              Integer p2AllianceCount = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + p2Alliance + ".MemberCount"));
              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + args[1] + " group remove " + p2Alliance);

              this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".InAlliance", false);
              this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".AllianceRank", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + p2Alliance + ".MemberCount", Integer.valueOf(p2AllianceCount.intValue() - 1));
              this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".Alliance", null);
              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + args[1] + " group remove " + args[2]);
              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + args[1] + " group add " + args[3]);
              Bukkit.broadcastMessage(this.waaprefix + p.getDisplayName() + " has kicked " + p2.getDisplayName() + " from their alliance!");

              if (this.plugin.WAAlliancesconfig.getBoolean("Users." + p2.getName() + ".Staff"))
              {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + p2.getName() + " " + "&7&o" + this.plugin.WAAlliancesconfig.getString(new StringBuilder("Users.").append(p2.getName()).append(".Nick").toString()));
                return true;
              }

              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + p2.getName() + " " + "&7" + this.plugin.WAAlliancesconfig.getString(new StringBuilder("Users.").append(p2.getName()).append(".Nick").toString()));
            }
            else if (args[0].equalsIgnoreCase("disband"))
            {
              if (!this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equalsIgnoreCase("Leader"))
              {
                sender.sendMessage(this.waaprefix + "Only the leader can disband the alliance.");
                return true;
              }

              String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
              String C1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
              String C2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");

              Bukkit.broadcastMessage(this.waaprefix + Alliance + " has been disbanded by " + pl + "!");
              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex group " + Alliance + " delete");
              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "rg remove " + Alliance + " -w world");
              Integer Bank = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + Alliance + ".Bank"));
              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco give " + pl + " " + Bank);
              this.plugin.WAAlliancesconfig.set("Colors.Taken." + C1 + C2, null);
              this.plugin.WAAlliancesconfig.set("Disbanded." + Alliance, Boolean.valueOf(true));
              this.plugin.WAAlliancesconfig.set("Users." + pl + ".Alliance", null);
              this.plugin.WAAlliancesconfig.set("Users." + pl + ".InAlliance", null);
              this.plugin.WAAlliancesconfig.set("Users." + pl + ".AllianceRank", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".created", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".Colors", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".Leader", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".Color1", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".Color2", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".Tier", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".Bank", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".MemberCount", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".Colorized", null);
              this.plugin.WAAlliancesconfig.set("Alliances." + Alliance + ".extraColorized", null);

              if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".Staff"))
              {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + "&7&o" + this.plugin.WAAlliancesconfig.getString(new StringBuilder("Users.").append(pl).append(".Nick").toString()));
                this.plugin.saveWAAlliances();
                return true;
              }

              Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + "&7" + this.plugin.WAAlliancesconfig.getString(new StringBuilder("Users.").append(pl).append(".Nick").toString()));
              this.plugin.saveWAAlliances();
            }
            else if ((args[0].equalsIgnoreCase("lookup")) && (args.length == 2))
            {
              if (!this.plugin.WAAlliancesconfig.getBoolean("Alliances." + args[1] + ".Created"))
              {
                sender.sendMessage(this.waaprefix + "That alliance does not exist!");
              }
              else
              {
                Integer tier = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + args[1] + ".Tier"));
                Integer bank = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + args[1] + ".Bank"));
                Integer members = Integer.valueOf(this.plugin.WAAlliancesconfig.getInt("Alliances." + args[1] + ".MemberCount"));
                String Leader = this.plugin.WAAlliancesconfig.getString("Alliances." + args[1] + ".Leader");
                String color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + args[1] + ".Color1");
                String color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + args[1] + ".Color2");
                List <String> users = WCMain.mail.getStringList("Users.Total");
                plugin.datacore.set("AllianceDataDisplay", "");
                
                	for (String lol : users){
                	Boolean inAlliance = plugin.WAAlliancesconfig.getBoolean("Users." + lol + ".InAlliance");
                	
                		if (inAlliance){
                			String allianceCheck = plugin.WAAlliancesconfig.getString("Users." + lol + ".Alliance");
                	
                				if (allianceCheck.equals(args[1])){
                					String userDisplay = plugin.datacore.getString("AllianceDataDisplay");
                					int midpoint = lol.length() / 2;
                		            String firstHalf = lol.substring(0, midpoint);
                		            String secondHalf = lol.substring(midpoint);
                					String lolColor1 = "&" + color1 + firstHalf;
                					String lolColor2 = "&" + color2 + secondHalf;
                					String complete = lolColor1 + lolColor2;
                					plugin.datacore.set("AllianceDataDisplay", userDisplay + "&f, " + complete);
                				}
                		}
                }

                sender.sendMessage(new String[] { 
                  this.waaprefix + "Selected Alliance: " + args[1], 
                  ChatColor.GREEN + "| Tier: " + tier, 
                  ChatColor.GREEN + "| Bank: " + bank, 
                  ChatColor.GREEN + "| Members: " + members, 
                  ChatColor.GREEN + "| Leader: " + Leader, 
                  ChatColor.GREEN + "| Colors: " + "&" + color1 + "," + " " + "&" + color2,
                  ChatColor.GREEN + "| Users: " + WCMail.AS(plugin.datacore.getString("AllianceDataDisplay"))});
              }

            }
            else if (args[0].equalsIgnoreCase("levels"))
            {
              sender.sendMessage(this.waaprefix + "Check out http://worldsapart.no-ip.org/?p=alliances");
            }
            else if ((args[0].equalsIgnoreCase("setrank")) && (args.length == 3))
            {
              if (!this.plugin.WAAlliancesconfig.getBoolean("Users." + args[1] + ".InAlliance"))
              {
                sender.sendMessage(this.waaprefix + "That user is not even in an alliance!");
                return true;
              }

              if (!this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equalsIgnoreCase("Leader"))
              {
                sender.sendMessage(this.waaprefix + "Only the leader can disband the alliance.");
                return true;
              }
              if (args[2].equalsIgnoreCase("Leader"))
              {
                sender.sendMessage(this.waaprefix + "You can't make someone leader like that.");
                return true;
              }
              if (!this.plugin.WAAlliancesconfig.getString("Users." + args[1] + ".Alliance").equals(this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance")))
              {
                sender.sendMessage(this.waaprefix + "That user is not a part of your alliance.");
                return true;
              }

              if (args[1].equalsIgnoreCase(pl))
              {
                sender.sendMessage(this.waaprefix + "You can't make change your own title, otherwise there won't be a leader!");
                return true;
              }

              this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".AllianceRank", args[2]);
              sender.sendMessage(this.waaprefix + "Updated the title of " + args[1] + " to " + args[2]);

              if (this.plugin.WAAlliancesconfig.getString("Users." + args[1] + ".currentChat").equals(this.plugin.WAAlliancesconfig.getString("Users." + args[1] + ".Alliance")))
              {
                this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".AllianceRank2", args[2]);
              }

            }
            else
            {
              if ((args[0].equalsIgnoreCase("leader")) && (args.length == 2))
              {
                if (!this.plugin.WAAlliancesconfig.getString("Users." + pl + ".AllianceRank").equalsIgnoreCase("Leader"))
                {
                  sender.sendMessage(this.waaprefix + "Only the leader can change leaders.");
                  return true;
                }

                String senderAlliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
                String receivingAlliance = this.plugin.WAAlliancesconfig.getString("Users." + args[1] + ".Alliance");

                if (!senderAlliance.equals(receivingAlliance))
                {
                  sender.sendMessage(this.waaprefix + "The person isn't even in your alliance. Go home you're drunk.");
                  return true;
                }

                this.plugin.WAAlliancesconfig.set("Users." + pl + ".AllianceRank", "Member");
                this.plugin.WAAlliancesconfig.set("Users." + args[1] + ".AllianceRank", "Leader");
                this.plugin.WAAlliancesconfig.set("Alliances." + senderAlliance + ".Leader", args[1]);
                sender.sendMessage(this.waaprefix + "Leader changed to " + args[1] + " !");
                Player qmsg = Bukkit.getPlayer(args[1]);
                Bukkit.broadcastMessage(this.waaprefix + pl + " has transferred leadership of their alliance to " + args[1]);
                qmsg.sendMessage(this.waaprefix + "You were made leader of your alliance!");
                return true;
              }

              if ((args[0].equalsIgnoreCase("pay")) && (args.length == 3))
              {
                Boolean Alliance = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Alliances." + args[1] + ".Created"));

                if (!Alliance.booleanValue())
                {
                  sender.sendMessage(this.waaprefix + "This alliance does not exist!");
                  return true;
                }

                if (!isInteger(args[2]))
                {
                  sender.sendMessage(this.waaprefix + "Try /waa pay <alliance> <amount> using numbers this time. #worried about your health");
                  return true;
                }

                if (args[2].startsWith("-"))
                {
                  sender.sendMessage(this.waaprefix + "Why the hell would you want to make the balance go DOWN?");
                  return true;
                }

                if (args[2].startsWith("0"))
                {
                  sender.sendMessage(this.waaprefix + "I don't even...");
                  return true;
                }

                int Bank = this.plugin.WAAlliancesconfig.getInt("Alliances." + args[1] + ".Bank");
                int sending = Integer.parseInt(args[2]) + 1;

                if (!WCVault.econ.has(pl, sending))
                {
                  sender.sendMessage(this.waaprefix + "You lack the funds to do that. #maths are hard. (You must have 1 more shiny than you are donating)");
                  return true;
                }

                int Bank2 = Bank + sending - 1;
                this.plugin.WAAlliancesconfig.set("Alliances." + args[1] + ".Bank", Integer.valueOf(Bank2));
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "eco take " + pl + " " + (sending - 1));
                sender.sendMessage(this.waaprefix + "The alliance bank now holds " + Bank2 + " shinies.");
                this.plugin.saveWAAlliances();
                return true;
              }

            }

          }

        }

      }
      else
      {
        sender.sendMessage("Try /waa help");
      }

    }

    if ((cmd.getName().equalsIgnoreCase("nick")) && ((sender instanceof Player)))
    {
      if (args.length > 0)
      {
        String name = sender.getName();
        String nameCheck = args[0].substring(0, Math.min(args[0].length(), 3));

        if (args[0].length() > 16)
        {
          sender.sendMessage(this.waaprefix + "Your nickname can't be longer than 16 characters!");
          return true;
        }

        if (args[0].length() < 3)
        {
          sender.sendMessage(this.waaprefix + "Your nickname must contain the first three letters of your name.");
          return true;
        }

        if (args[0].contains("&"))
        {
          sender.sendMessage(this.waaprefix + "Do not try to set colors. Those will automatically be applied based on your alliance.");
          return true;
        }

        if (!name.toLowerCase().startsWith(nameCheck.toLowerCase()))
        {
          sender.sendMessage(this.waaprefix + "You must use the first three letters of your name.");
          return true;
        }

        Boolean InAlliance = Boolean.valueOf(this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".InAlliance"));

        if (!InAlliance.booleanValue())
        {
          if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".Staff"))
          {
            String newNick = args[0].replaceAll("§", "_");
            this.plugin.WAAlliancesconfig.set("Users." + sender.getName() + ".Nick", newNick);
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + sender.getName() + " &7&o" + newNick);
            return true;
          }

          String newNick = args[0].replaceAll("§", "_");
          this.plugin.WAAlliancesconfig.set("Users." + sender.getName() + ".Nick", newNick);
          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + sender.getName() + " &7" + newNick);
          return true;
        }

        if (this.plugin.WAAlliancesconfig.getBoolean("Users." + sender.getName() + ".Staff"))
        {
          String pl = sender.getName();
          String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
          String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
          String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");

          String newNick = args[0].replaceAll("§", "_");
          this.plugin.WAAlliancesconfig.set("Users." + sender.getName() + ".Nick", newNick);
          int midpoint = newNick.length() / 2;
          String firstHalf = newNick.substring(0, midpoint);
          String secondHalf = newNick.substring(midpoint);

          String firstHalfColors = "&" + Color1 + "&o" + firstHalf;
          String secondHalfColors = "&" + Color2 + "&o" + secondHalf;
          String completed = firstHalfColors + secondHalfColors;

          Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + completed);
          return true;
        }

        String pl = sender.getName();
        String Alliance = this.plugin.WAAlliancesconfig.getString("Users." + pl + ".Alliance");
        String Color1 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color1");
        String Color2 = this.plugin.WAAlliancesconfig.getString("Alliances." + Alliance + ".Color2");

        String newNick = args[0].replaceAll("§", "_");
        this.plugin.WAAlliancesconfig.set("Users." + sender.getName() + ".Nick", newNick);
        int midpoint = newNick.length() / 2;
        String firstHalf = newNick.substring(0, midpoint);
        String secondHalf = newNick.substring(midpoint);

        String firstHalfColors = "&" + Color1 + firstHalf;
        String secondHalfColors = "&" + Color2 + secondHalf;
        String completed = firstHalfColors + secondHalfColors;

        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "enick " + pl + " " + completed);
        return true;
      }

      sender.sendMessage(this.waaprefix + "Correct usage is /nick <newnick>");
      return true;
    }

    return true;
  }



public boolean isLeader(String pl, String allianceName)
  {
    String player = this.plugin.WAAlliancesconfig.getString("Users." + pl);
    String leader = this.plugin.WAAlliancesconfig.getString("Alliances." + allianceName + ".Leader");

    if (leader.equalsIgnoreCase(player)) {
      return true;
    }

    return false;
  }

  public static boolean isInteger(String s)
  {
    try
    {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }
  
  private void allianceUpgrade(String alliance, Integer tier, Integer bank,
			Boolean approved, CommandSender sender) {
		
		if (approved == false){
			sender.sendMessage(waaprefix + "Your alliance must be approved by a staff member before you do this!");
          return;
		}
		
		String tierPrices = "60000 75000 90000 105000 125000 145000 165000 180000";
		String outsetLevels = "15 15 20 20 15 25 10 15";
		String[] prices = tierPrices.split(" ");
		String[] outsets = outsetLevels.split(" ");
		
		if (bank < Integer.parseInt(prices[(tier)])){
        sender.sendMessage(this.waaprefix + "Your alliance lackcs the proper funds to carry out this operation!");
        return;
      }
		
      plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Approved", false);
      plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Tier", (tier+1));
      plugin.WAAlliancesconfig.set("Alliances." + alliance + ".Bank", (bank-(Integer.parseInt(prices[(tier)]))));
      
      Bukkit.getServer().dispatchCommand(sender, "rg select " + alliance);
      Bukkit.getServer().dispatchCommand(sender, "/outset " + outsets[(tier)]);
      Bukkit.getServer().dispatchCommand(sender, "/rg redefine " + alliance);
      Bukkit.broadcastMessage(waaprefix + alliance + " has been upgraded to Tier " + (tier+1) + "!");
		
		
	}
}