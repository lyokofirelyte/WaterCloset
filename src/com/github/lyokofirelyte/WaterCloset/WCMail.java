package com.github.lyokofirelyte.WaterCloset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.lyokofirelyte.WaterCloset.Extras.TimeStampEX;

public class WCMail implements CommandExecutor {
static String WC = "§dWC §5// §d";
static List <String> mail;
private Connection conn;
private PreparedStatement pst;
String message;

	WCMain plugin;
	public WCMail(WCMain instance){
	this.plugin = instance;
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
	if (cmd.getName().equals("mail")){
		
		if (sender instanceof Player == false){
			sender.sendMessage(AS(WC + "Sorry console, I'm too lazy to allow you to send mails. That's like, extra effort."));
			return true;
		}
		
		Player p = (Player) sender;
		
		
		switch(args.length){
		
		case 0:
			
			for (String help : WCHelp.WCHelpMail){
	    		sender.sendMessage(AS(help));
	    	}
	    	
			break;
		
		case 1:
			
			switch(args[0]){

			
			case "send":
				
				sender.sendMessage(AS(WC + "Try /mail send <player> <message>."));
				break;
	
		    case "read":

		    	mailCheck(p);
		    	break;
		    	
		    case "clear":
		    	
		        clearCheck(p);
		        break;
		        
		    case "quick":
		    	
		    	mailCheck(p);
		    	clearCheck(p);
		    	break;
		        
		    default:
		    	
		    	for (String help : WCHelp.WCHelpMail){
		    		sender.sendMessage(AS(help));
		    	}
		    	
		    break;
		
			}
			
		break;
			
		default:
			
		  switch(args[0]){
		  
		  default:
		    	
		    	for (String help : WCHelp.WCHelpMail){
		    		sender.sendMessage(AS(help));
		    	}
		    	
		    break;
		    
		  case "send":
			  
			  switch (args[1]){
			  
			  case "website":		  
				  message = TimeStampEX.createString(args, 2);
				  mailSite(sender.getName(), message);
				  sender.sendMessage(AS(WC + "Your message has been sent to http://www.ohsototes.com/?p=mail"));
				  break;
				  
			  case "all":
				  
				  if (p.hasPermission("wa.staff") == false){
					  p.sendMessage(AS(WC + "You don't have permission to send global mails."));
					  break;
				  }
				  
				  List <String> userList = WCMain.mail.getStringList("Users.Total");
				  sender.sendMessage(AS(WC + "Message sent!"));
				  
				  for (String current : userList){
					  
					  OfflinePlayer sendTo = Bukkit.getOfflinePlayer(current);
				  	
					  message = TimeStampEX.createString(args, 2);
					  mail = plugin.config.getStringList("Users." + current + ".Mail");
					  mail.add(p.getDisplayName() + " &9// &3" + message);
					  WCMain.mail.set("Users." + current + ".Mail", mail);
					  	if (sendTo.isOnline()){
					  		Bukkit.getPlayer(current).sendMessage(AS(WC + "You've recieved a new mail! Check it with /mail read."));
					  	}
					  
				  }
				  
			  break;
		  
			  default:
				  
				  if (args[1].equalsIgnoreCase(p.getName())){
					  sender.sendMessage(AS(WC + "I'm schizophrenic, and so am I. Together, I can solve this."));
					  break;
				  }
				  
				  OfflinePlayer sendTo = Bukkit.getOfflinePlayer(args[1]);
				  	if (sendTo.hasPlayedBefore() == false){
				  		sender.sendMessage(AS(WC + "That player has never logged in before!"));
				  		break;
				  	}
				  	
				  	message = TimeStampEX.createString(args, 2);
				  	mail = WCMain.mail.getStringList("Users." + sendTo.getName() + ".Mail");
				  	mail.add(p.getDisplayName() + " &9// &3" + message);
				  	WCMain.mail.set("Users." + sendTo.getName() + ".Mail", mail);
				  	sender.sendMessage(AS(WC + "Message sent!"));
				  		if (sendTo.isOnline()){
				  			Bukkit.getPlayer(args[1]).sendMessage(AS(WC + "You've recieved a new mail! Check it with /mail read."));
				  		}
				  	break;
			  }
		  }

	}
	
	}
	return true;
	}
	
	
	public void mailSite(String name, String message){
		
		String url = plugin.config.getString("urlMail");
        String username = plugin.config.getString("username");
        String password = plugin.config.getString("password");
		
		try {
	        this.conn = DriverManager.getConnection(url, username, password);

	        this.pst = this.conn.prepareStatement("INSERT INTO mail(timestamp, name, message) VALUES(?, ?, ?)");

	        long timestamp = System.currentTimeMillis() / 1000L;

	        this.pst.setInt(1, (int)timestamp);
	        this.pst.setString(2, name);
	        this.pst.setString(3, message);

	        this.pst.executeUpdate();
	        this.pst.close();
	        this.conn.close();

	      }
	      catch (SQLException e)
	      {
	        e.printStackTrace();
	      }
	    }
	
	
	public static String AS(String DecorativeToasterCozy){
		
		String FlutterShysShed = ChatColor.translateAlternateColorCodes('&', DecorativeToasterCozy);
		return FlutterShysShed;
		
	}
	
	public void clearCheck(Player p){
		
		mail = WCMain.mail.getStringList("Users." + p.getName() + ".Mail");
		
		if (mail.size() == 0){
			p.sendMessage(AS(WC + "You have no mail!"));
			return;
		}
		
		WCMain.mail.set("Users." + p.getName() + ".Mail", null);
		p.sendMessage(AS(WC + "Mail cleared!"));
	}
	
	public void mailCheck(Player p){
    	
    	mail = WCMain.mail.getStringList("Users." + p.getName() + ".Mail");
		
		if (mail.size() == 0){
			p.sendMessage(AS(WC + "You have no mail!"));
			return;
		}
		
    	p.sendMessage(AS(WC + "Viewing Mail &6(" + mail.size() + "&6)"));
    		for (String singleMail : mail){
    			p.sendMessage(AS("&5| " + singleMail));
    		}
	}
	
	public static void mailLogin(Player p){
		
		mail = WCMain.mail.getStringList("Users." + p.getName() + ".Mail");
		List <String> userList = WCMain.mail.getStringList("Users.Total");
		
		if (mail.size() > 0){
			p.sendMessage(AS(WC + "You have " + mail.size() + " &dnew messages. Read them with /mail read."));
		}
		
		if (userList.contains(p.getName())){
			return;
		} else {
			userList.add(p.getName());
			WCMain.mail.set("Users.Total", userList);
		}
		

	}
}
