package com.github.lyokofirelyte.WaterCloset.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.lyokofirelyte.WaterCloset.WCMain;
import com.github.lyokofirelyte.WaterCloset.Util.Utils;
import com.github.lyokofirelyte.WaterCloset.Util.WCVault;

public class WCRanks implements CommandExecutor {

	WCMain plugin;
	public WCRanks(WCMain instance){
	plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equals("rankup")){
			
			Player p = ((Player)sender);
		
			List <String> ranks = plugin.datacore.getStringList("Ranks");
			String[] groups =  WCVault.perms.getPlayerGroups(p);
			int x = 0;
			int y = 0;
			
				for (String r : ranks){
					while (groups.length > x){
						
						if (y >= ranks.size() - 1){
							WCMain.s(p, "You are already the highest rank!");
							return true;
						}
						
						if (groups[x].toLowerCase().contains(r.toLowerCase().substring(2))){
							checkCash(p, ranks.get(y+1).substring(2), groups[x], ranks.get(y+1));
							return true;
						}
						x++;
					}
				    x = 0;
				    y++;
				}
				
			WCMain.s(p, "You're not in a normal rank group - see Hugs. SOMETHING WENT WRONG OH SHIT.");
	    } 
	
		return true;
	}

	private void checkCash(Player p, String newGroup, String oldGroup, String newGroupFancy) {
	
		if (newGroup == null){
			WCMain.s(p, "You are already the highest rank!");
			return;
		}
		
		int cost = plugin.datacore.getInt("RankCosts." + newGroup);
			if (!WCVault.econ.has(p.getName(), cost)){
				WCMain.s(p, "You need &6" + cost + " &dshinies for that rank.");
				return;
			}
		WCVault.econ.withdrawPlayer(p.getName(), cost);
		WCVault.perms.playerAddGroup(p, newGroup);
		WCVault.perms.playerRemoveGroup(p, oldGroup);
		Bukkit.broadcastMessage(Utils.AS(WCMail.WC + p.getDisplayName() + " &dhas been promoted to " + newGroupFancy + "&d."));	
	}
}
