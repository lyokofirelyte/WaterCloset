package com.github.lyokofirelyte.WaterCloset;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WCMenus implements Listener {
	
	WCMain plugin;
	public WCMenus(WCMain instance){
	this.plugin = instance;
	}
	
	public List<Material> mats = new ArrayList<Material>();
	public static List<String> menu = new ArrayList<String>();
	public List<String> lores = new ArrayList<String>();
	public Inventory inv;
	Boolean poke = true;
	public String colorType;
	
	public static void setUp(){
		menu.add("§bMAIN MENU - WATERCLOSET CORE");
		menu.add("§bMEMBERS OPTIONS");
		menu.add("§4CHAT");
		menu.add("§b§lGLOBAL COLOR");
		menu.add("§b§lPM COLOR");
		menu.add("§b§lALLIANCE COLOR");
		menu.add("§b§lTIME CODES");
		menu.add("§dSTAFF OPTIONS");
		menu.add("§eCHAT TIMESTAMPS");
		menu.add("§3STATS");
		menu.add("§4TOGGLES");
	}
	
	public void openTimeStampMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§eCHAT TIMESTAMPS");
		inv = addToInv(p, Material.INK_SACK, "§aTOGGLE", 0, "§2Toggle timestamps", 2, inv);
		inv = addToInv(p, Material.FLINT, "§b§l<<---", 8, "§1§oPrevious Menu", 1, inv);
		p.openInventory(inv);
	}
	
	public void openStatsMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§3STATS");
		inv = addToInv(p, Material.INK_SACK, "§aME", 0, "§2Personal Stats", 2, inv);
		inv = addToInv(p, Material.INK_SACK, "§cOTHERS", 1, "§4Other people's stats", 1, inv);
		inv = addToInv(p, Material.FLINT, "§b§l<<---", 8, "§1§oPrevious Menu", 1, inv);
		p.openInventory(inv);
	}
	
	public void openToggleMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§4TOGGLES");
		inv = addToInv(p, Material.INK_SACK, "§aHOME SOUNDS", 0, "§2/home sounds", 2, inv);
		inv = addToInv(p, Material.INK_SACK, "§5THROW ITEMS", 1, "§dShift-right click throwing", 5, inv);
		inv = addToInv(p, Material.INK_SACK, "§eSIDEBOARD", 2, "§6The scoreboard", 8, inv);
		inv = addToInv(p, Material.INK_SACK, "§bPOKES", 3, "§9Allowing pokes", 12, inv);
		inv = addToInv(p, Material.FLINT, "§b§l<<---", 8, "§1§oPrevious Menu", 1, inv);
		p.openInventory(inv);
	}
	
	public static void openMembersMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§bMAIN MENU - WATERCLOSET CORE");
		inv = addToInv(p, Material.INK_SACK, "§a§lCHAT", 0, "§3Chat Options", 9, inv);
		inv = addToInv(p, Material.INK_SACK, "§4§lTOGGLES", 1, "§cToggle Options", 1, inv);
		inv = addToInv(p, Material.INK_SACK, "§6§lSTATS", 2, "§eStat Viewer", 5, inv);
		inv = addToInv(p, Material.INK_SACK, "§5§lSTAFF SECTION", 3, "§dStaff only", 8, inv);
		inv = addToInv(p, Material.FLINT, "§b§l<<---", 8, "§1§oPrevious Menu", 1, inv);
		p.openInventory(inv);
	}
	
	public void openStaffMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 54, "§dSTAFF OPTIONS");
		inv = addToInv(p, Material.STAINED_CLAY, "§4§lBANNING", 45, "§3View banning options", 6, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§c§lMUTING", 46, "§3View muting options", 6, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§e§lKICKING", 47, "§3View kicking options", 6, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§9§lFREEZING", 48, "§3View freezing options", 6, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§5§lRESTRICTIONS", 49, "§3View restriction options", 6, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§e§lWARNINGS", 50, "§3View restriction options", 6, inv);
		inv = addToInv(p, Material.SULPHUR, "§a§o§lREFRESH", 52, "§3Refresh player list", 14, inv);
		inv = addToInv(p, Material.FLINT, "§b§l<<---", 53, "§1§oPrevious Menu", 1, inv);
		int x = 0;
			for (Player p2 : Bukkit.getOnlinePlayers()){
				inv = addToInv(p, Material.SKULL_ITEM, "§a" + p2.getName(), x, p2.getDisplayName(), 3, inv);
				x++;
			}
		p.openInventory(inv);
	}
	
	public void openChatMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 9, "§4CHAT");
		inv = addToInv(p, Material.INK_SACK, "§b§lGLOBAL COLOR", 0, "§9Change global color", 14, inv);
		inv = addToInv(p, Material.INK_SACK, "§b§lPM COLOR", 1, "§9Change pm color", 10, inv);
		inv = addToInv(p, Material.INK_SACK, "§b§lALLIANCE COLOR", 2, "§9Change alliance color", 11, inv);
		inv = addToInv(p, Material.INK_SACK, "§b§lTIME CODES", 3, "§9Toggle chat timecodes", 12, inv);
		inv = addToInv(p, Material.FLINT, "§b§l<<---", 8, "§1§oPrevious Menu", 1, inv);
		p.openInventory(inv);
	}
	
	public void openColorMenu(Player p, String name){
		Inventory inv = Bukkit.createInventory(null, 18, name);
		inv = addToInv(p, Material.STAINED_CLAY, "§f§lWHITE", 0, "§fIt's a color.", 0, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§6§lORANGE-ISH", 1, "§fIt's a color.", 1, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§d§lPINK", 2, "§fIt's a color.", 2, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§9§lBLUE-ISH", 3, "§fIt's a color.", 3, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§e§lYELLOW", 4, "§fIt's a color.", 4, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§a§lLIGHT-GREEN", 5, "§fIt's a color.", 5, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§c§lLIGHT-RED", 6, "§fIt's a color.", 6, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§1§lDARK-BLUE", 7, "§fDon't ask.", 7, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§7§lLIGHT-GRAY", 16, "§fIt's a color.", 8, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§8§lGRAY", 9, "§fIt's a color.", 9, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§5§lPURPLE", 10, "§fIt's a color.", 10, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§3§lTEAL-BLUE", 11, "§fIt's a color.", 11, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§2§lDARK-GREEN", 12, "§fIt's a color.", 13, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§4§lDARK-RED", 13, "§fIt's a color.", 14, inv);
		inv = addToInv(p, Material.STAINED_CLAY, "§0§lBLACK", 14, "§fIt's a color.", 15, inv);
		inv = addToInv(p, Material.CLAY, "§b§lLIGHT-BLUE", 15, "§fIt's a color.", 1, inv);
		inv = addToInv(p, Material.FLINT, "§b§l<<---", 8, "§1§oPrevious Menu", 1, inv);
		p.openInventory(inv);
	}
	
	
	@EventHandler (priority = EventPriority.NORMAL)
	public void onClick(InventoryClickEvent e){
	    String n = e.getInventory().getName();
		if (menu.contains(n)){
			if (e.getWhoClicked() instanceof Player && e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()){
				Player p = (Player) e.getWhoClicked();
				String d = e.getCurrentItem().getItemMeta().getDisplayName();
					if (e.getCurrentItem().getType() == Material.INK_SACK){ // Clicking on things
					
						switch(d){
						
						case "§5§lSTAFF SECTION":
							if (!p.hasPermission("wa.staff")){
								WCMain.s(p, "You don't have permission!");
								break;
							}
							openStaffMenu(p);
							break;
						case "§a§lCHAT":
							openChatMenu(p);
							break;
						case "§6§lSTATS":
							openStatsMenu(p);
							break;
						case "§b§lGLOBAL COLOR": case "§b§lPM COLOR": case "§b§lALLIANCE COLOR":
							openColorMenu(p, d);
							break;
						case "§b§lTIME CODES":
							openTimeStampMenu(p);
							break;
						case "§4§lTOGGLES":
							openToggleMenu(p);
							break;
						case "§aTOGGLE":
							if (n.equals("§eCHAT TIMESTAMPS")){
								p.performCommand("wc timecode");
							}
							break;		
						case "§aHOME SOUNDS":
							p.performCommand("wc homesounds");
							break;
						case "§5THROW ITEMS":
							p.performCommand("wc throw");
							break;
						case "§eSIDEBOARD":
							p.performCommand("wc sidebar");
							break;
						case "§bPOKES":
							if (poke){
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + p.getName() + " c:/waos poke deny");
								poke = false;
							} else {
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + p.getName() + " c:/waos poke allow");
								poke = true;
							}
							break;
						
						case "§aME":
							if (n.equals("§3STATS")){
								Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + p.getName() + " c:!stats");
							}
							break;			
						case "§cOTHERS":
							if (n.equals("§3STATS")){
								Inventory inv = Bukkit.createInventory(null, 54, "§3STATS");
								inv = addToInv(p, Material.FLINT, "§b§l<<---", 53, "§1§oPrevious Menu", 1, inv);
								int x = 0;
								for (Player p2 : Bukkit.getOnlinePlayers()){
									inv = addToInv(p, Material.SKULL_ITEM, p2.getName(), x, p2.getDisplayName(), 3, inv);
									x++;
								}
								p.openInventory(inv);
							}
							break;
						default:
							break;
						}
						
					} else if (e.getCurrentItem().getType() == Material.FLINT){ // back menu click
						switch (n){
						case "§bMAIN MENU - WATERCLOSET CORE":
							p.closeInventory();
							break;
						case "§4CHAT": case "§3STATS": case "§4TOGGLES": case "§dSTAFF OPTIONS":
							openMembersMenu(p);
							break;
						case "§b§lGLOBAL COLOR": case "§b§lPM COLOR": case "§b§lALLIANCE COLOR": case "§eCHAT TIMESTAMPS":
							openChatMenu(p);
							break;
						}
						
					} else if (e.getCurrentItem().getType() == Material.STAINED_CLAY || e.getCurrentItem().getType() == Material.CLAY){
						
						switch(n){
						
							default:
								break;
							case "§b§lGLOBAL COLOR":
								colorType = "globalcolor";
								normalCommand(p, d, colorType);
								break;
							case "§b§lPM COLOR":
								colorType = "pmcolor";
								normalCommand(p, d, colorType);
								break;
							case "§b§lALLIANCE COLOR":
								allianceCommand(p, d);
								break;				
						}
					} else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){ // player selections
						if (n.equals("§3STATS")){
							Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "sudo " + p.getName() + " c:!stats " + d);
						}
					}
					
			}
			e.setCancelled(true);
		}
	}

	public void normalCommand(Player p, String d, String colorType){
		switch (d){
			case "§f§lWHITE":
				p.performCommand("wc " + colorType + " &f");
				break;
			case "§6§lORANGE-ISH":
				p.performCommand("wc " + colorType + " &6");
				break;
			case "§d§lPINK":
				p.performCommand("wc " + colorType + " &d");
				break;
			case "§9§lBLUE-ISH":
				p.performCommand("wc " + colorType + " &9");
				break;
			case "§e§lYELLOW":
				p.performCommand("wc " + colorType + " &e");
				break;
			case "§a§lLIGHT-GREEN":
				p.performCommand("wc " + colorType + " &a");
				break;
			case "§c§lLIGHT-RED":
				p.performCommand("wc " + colorType + " &c");
				break;
			case "§1§lDARK-BLUE":
				p.performCommand("wc " + colorType + " &1");
				break;
			case "§7§lLIGHT-GRAY":
				p.performCommand("wc " + colorType + " &7");
				break;
			case "§8§lGRAY":
				p.performCommand("wc " + colorType + " &8");
				break;
			case "§0§lBLACK":
				p.performCommand("wc " + colorType + " &0");
				break;
			case "§4§lDARK-RED":
				p.performCommand("wc " + colorType + " &4");
				break;
			case "§2§lDARK-GREEN":
				p.performCommand("wc " + colorType + " &2");
				break;
			case "§5§lPURPLE":
				p.performCommand("wc " + colorType + " &5");
				break;
			case "§b§lLIGHT-BLUE":
				p.performCommand("wc " + colorType + " &b");
				break;
			case "§3§lTEAL-BLUE":
				p.performCommand("wc " + colorType + " &3");
				break;
		}
	}
	
	private void allianceCommand(Player p, String d) {
		switch (d){
			case "§f§lWHITE":
				p.performCommand("waa chat color" + " f");
				break;
			case "§6§lORANGE-ISH":
				p.performCommand("waa chat color" + " 6");
				break;
			case "§d§lPINK":
				p.performCommand("waa chat color" + " d");
				break;
			case "§9§lBLUE-ISH":
				p.performCommand("waa chat color" + " 9");
				break;
			case "§e§lYELLOW":
				p.performCommand("waa chat color" + " e");
				break;
			case "§a§lLIGHT-GREEN":
				p.performCommand("waa chat color" + " a");
				break;
			case "§c§lLIGHT-RED":
				p.performCommand("waa chat color" + " c");
				break;
			case "§1§lDARK-BLUE":
				p.performCommand("waa chat color" + " 1");
				break;
			case "§7§lLIGHT-GRAY":
				p.performCommand("waa chat color" + " 7");
				break;
			case "§8§lGRAY":
				p.performCommand("waa chat color" + " 8");
				break;
			case "§0§lBLACK":
				p.performCommand("waa chat color" + " 0");
				break;
			case "§4§lDARK-RED":
				p.performCommand("waa chat color" + " 4");
				break;
			case "§2§lDARK-GREEN":
				p.performCommand("waa chat color" + " 2");
				break;
			case "§5§lPURPLE":
				p.performCommand("waa chat color" + " 5");
				break;
			case "§b§lLIGHT-BLUE":
				p.performCommand("waa chat color" + " b");
				break;
			case "§3§lTEAL-BLUE":
				p.performCommand("waa chat color" + " 3");
				break;
	}
		
	}

	public void setupInv(){
		
		
		lores.add("§3Change GLOBAL Color"); // chat toggles
		lores.add("§3Change PM Color"); 
		lores.add("§3Change TimeCodes");
		lores.add("§3Change ALLIANCE Color");
		
	    lores.add("§cThrow items"); // toggles
	    lores.add("§cHome TP Sounds");
	    lores.add("§cXP Notify");
	    lores.add("§cPokes");
	    
	    lores.add("§eWC Stats"); // stats
	}
	
	public static Inventory addToInv(Player p, Material mat, String displayName, int slot, String lore, int id, Inventory inv) {
				List<String> newLore = new ArrayList<String>();
				newLore.add(lore);
				ItemStack is = new ItemStack(mat, 1, (byte) id);
				ItemMeta im = is.getItemMeta();
				im.setLore(newLore);
				im.setDisplayName(displayName);
					if (mat == Material.FLINT || mat == Material.SULPHUR){
						im.addEnchant(Enchantment.DURABILITY, 10, true);
					}
				is.setItemMeta(im);
				inv.setItem(slot, is);
		return inv;
	}
}
