package com.github.lyokofirelyte.WaterCloset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import com.github.lyokofirelyte.WaterCloset.Extras.FireworkShenans;

public class WCCommands implements CommandExecutor {
	
	private HashMap<String, Long> rainoffCooldown = new HashMap<String, Long>();
	
	WCMain plugin;
	String WC = "§dWC §5// §d";
	Boolean home;
	Boolean homeSet;

	public WCCommands(WCMain instance){
	
	
		this.plugin = instance;
	}
	
	public static boolean isInteger(String str) {
		try {
				Integer.parseInt(str);
				return true;
		} catch (NumberFormatException nfe) {
				return false;
		}
	}
	
	public void spawnWorks(Location loc, Player p){
		
		int xBase = loc.getBlockX();
		int yBase = loc.getBlockY();
		int zBase = loc.getBlockZ();
		World world = p.getWorld();
		
		List <Location> borderLocations = new ArrayList<Location>();
		List <Location> borderLocations2 = new ArrayList<Location>();
		List <Location> borderLocations3 = new ArrayList<Location>();
		List <Location> borderLocations4 = new ArrayList<Location>();
		int x = 0;
		
		while (x <= 10){
			Location temp = new Location(world, xBase+x, yBase, zBase);
			borderLocations.add(temp);
			x++;
		}
		
		x = 0;
		
		while (x <= 10){
			Location temp = new Location(world, xBase-x, yBase, zBase);
			borderLocations2.add(temp);
			x++;
		}
		
		x = 0;
		
		while (x <= 10){
			Location temp = new Location(world, xBase, yBase, zBase+x);
			borderLocations3.add(temp);
			x++;
		}
		
		x = 0;
		
		while (x <= 10){
			Location temp = new Location(world, xBase, yBase, zBase-x);
			borderLocations4.add(temp);
			x++;
		}
		
		int h = 0;
		long delay = 0L;
		long delay2 = 0L;
		
		while (h <= 10){
			
			for (Location bleh : borderLocations){
				int xBase2 = bleh.getBlockX();
				int yBase2 = bleh.getBlockY();
				int zBase2 = bleh.getBlockZ();
				Location newLoc = new Location(world, xBase2, yBase2+h, zBase2);
				delay = delay+10L;
				spawnGO(newLoc, p, delay);
			}
			
			delay = delay2;
			
			for (Location bleh : borderLocations2){
				int xBase2 = bleh.getBlockX();
				int yBase2 = bleh.getBlockY();
				int zBase2 = bleh.getBlockZ();
				Location newLoc = new Location(world, xBase2, yBase2+h, zBase2);
				delay = delay+10L;
				spawnGO(newLoc, p, delay); 
			}
			
			delay = delay2;
				
			for (Location bleh : borderLocations3){
				int xBase2 = bleh.getBlockX();
				int yBase2 = bleh.getBlockY();
				int zBase2 = bleh.getBlockZ();
				Location newLoc = new Location(world, xBase2, yBase2+h, zBase2);
				delay = delay+10L;
				spawnGO(newLoc, p, delay);
			}
			
			delay = delay2;
			
			for (Location bleh : borderLocations4){
				int xBase2 = bleh.getBlockX();
				int yBase2 = bleh.getBlockY();
				int zBase2 = bleh.getBlockZ();
				Location newLoc = new Location(world, xBase2, yBase2+h, zBase2);
				delay = delay+10L;
				spawnGO(newLoc, p, delay);
			}
			
			delay2 = delay2+10L;
			h++;
		}
			
	}
		
	public static List<Location> circle (Player player, Location loc, Integer r, Integer h, Boolean hollow, Boolean sphere, int plus_y) {
		List<Location> circleblocks = new ArrayList<Location>();
		int cx = loc.getBlockX();
		int cy = loc.getBlockY();
		int cz = loc.getBlockZ();
		for (int x = cx - r; x <= cx +r; x++)
			for (int z = cz - r; z <= cz +r; z++)
				for (int y = (sphere ? cy - r : cy); y < (sphere ? cy + r : cy + h); y++) {
					double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
					if (dist < r*r && !(hollow && dist < (r-1)*(r-1))) {
						Location l = new Location(loc.getWorld(), x, y + plus_y, z);
						circleblocks.add(l);
					}
				}
		 
		return circleblocks;
	}
		
	public void spawnGO(final Location l, final Player p, long delay) {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
			public void run() {
									
				FireworkShenans fplayer = new FireworkShenans();
				try {
					fplayer.playFirework(p.getWorld(), l,
					FireworkEffect.builder().with(Type.BURST).withColor(Color.WHITE).build());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}, delay);
	}
	
	public void fireWorkCrazyAssShit2(Location loc, final Player p) {
		
		int w = 0;
		
		while (w <= 50){
			List<Location> circleblocks = WCBlockBreak.circle(p, loc, 5, 1, true, false, w);
			long delay = 0L;
			
			for (final Location l : circleblocks){
				delay = delay + 2L;
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable(){
					public void run(){
						
						FireworkShenans fplayer = new FireworkShenans();
						try {
							fplayer.playFirework(p.getWorld(), l,
							FireworkEffect.builder().with(Type.BURST).withColor(Color.WHITE).build());
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}, delay);
			}
			
			w++;
		}
		
	}
	
	public static Entity getTarget(final Player player) {
		BlockIterator iterator = new BlockIterator(player.getWorld(), player
			.getLocation().toVector(), player.getEyeLocation()
			.getDirection(), 0, 100);
		Entity target = null;
		while (iterator.hasNext()) {
			Block item = iterator.next();
			for (Entity entity : player.getNearbyEntities(100, 100, 100)) {
				int acc = 2;
				for (int x = -acc; x < acc; x++)
					for (int z = -acc; z < acc; z++)
						for (int y = -acc; y < acc; y++)
							if (entity.getLocation().getBlock()
									.getRelative(x, y, z).equals(item)) {
								return target = entity;
							}
			}
		}
		return target;
	}
	
	public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
		
		final Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("blame")){
			
			Random rand = new Random();
			int randomNumber = rand.nextInt(Bukkit.getOnlinePlayers().length);
			int x = 0;
			
			for (Player bleh : Bukkit.getOnlinePlayers()){
				x++;
				
				if (x == randomNumber){
					Bukkit.broadcastMessage(WCMail.AS(((Player) sender).getDisplayName() + " &dblames " + bleh.getDisplayName() + "!"));
					break;
				}
			}
			
		}
		
		if (cmd.getName().equalsIgnoreCase("wc") || cmd.getName().equalsIgnoreCase("watercloset") || cmd.getName().equalsIgnoreCase("worldscollide")){

			if (args.length < 1)
			{
				sender.sendMessage(this.WC + "I'm not sure what you mean. Try /wc help or /wc ?");
				return true;
			}
			
			if (!(sender instanceof Player) && !args[0].equals("con"))
			{
				sender.sendMessage(this.WC + "Sorry console, these commands are for players only.");
				return true;
			}
			
			switch (args[0]){
			
			case "con":
				
				if (sender instanceof Player == false){
					for (Player bleh : Bukkit.getOnlinePlayers()){
						
						String globalColor = plugin.datacore.getString("Users." + bleh.getName() + ".GlobalColor");
						
							if (globalColor == null){
								plugin.datacore.set("Users." + bleh.getName() + ".GlobalColor", "&f");
							}
							
							globalColor = plugin.datacore.getString("Users." + bleh.getName() + ".GlobalColor");
							String message = WCChannels.createString2(args, 1);
							bleh.sendMessage(WCMail.AS("&4(づ｡◕‿‿◕｡)づ §f// &6Console§f: " + globalColor + message));	 
					}
				} else {
					
					sender.sendMessage(WC + "You're not the console, silly!");
					
				}
			
			break;
			
			case "globalcolor":
				
				if (args.length != 2){
					sender.sendMessage(WCMail.WC + "/wc globalcolor <color>.");
					break;
				}
				
				List <String> c1 = Arrays.asList(
						"&1",
						"&2",
						"&3",
						"&4",
						"&5",
						"&6",
						"&7",
						"&8",
						"&9",
						"&0",
						"&a",
						"&b",
						"&c",
						"&d",
						"&e",
						"&f",
						"&k"
				);
				
				if (c1.contains(args[1]) == false){
					sender.sendMessage(WCMail.WC + "That's not a color! Choose from " + c1 + ".");
					break;
				}
				
				sender.sendMessage(WCMail.AS(WCMail.WC + "You've changed your color to " + args[1] + "this."));
				plugin.datacore.set("Users." + sender.getName() + ".GlobalColor", args[1]);
				break;
			
			case "fork":
				
				sender.sendMessage(WCMail.WC + "LET'S DO THE FORK IN THE GARBAGE DISPOSAL!");
				final Location self = p.getLocation();
				final double x1 = self.getX();
				final double y1 = self.getY();
				final double z1 = self.getZ();
				int c = 100;
				long dl = 0L;
				
			while (c > 0){		
			
				c--;
				dl = dl+2L;
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					{
						public void run()
						{

								p.sendMessage("DING");
								Random rand = new Random();
								int randomNumber = rand.nextInt(7);
								int randomNumber2 = rand.nextInt(7);
								int yawRandom = rand.nextInt(360);
								int pitchRandom = rand.nextInt(90);
								int plusMinus = rand.nextInt(1);
								p.getWorld().playSound(self, Sound.NOTE_BASS_DRUM, 3.0F, 0.5F);
								
								if (plusMinus == 0){
									Location current = new Location(p.getWorld(), x1+randomNumber, y1, z1+randomNumber2, yawRandom, pitchRandom);
									p.teleport(current);
								}
								
							if (plusMinus == 1){
								Location current = new Location(p.getWorld(), x1-randomNumber, y1, z1-randomNumber2, yawRandom, (pitchRandom)-(pitchRandom*2));
								p.teleport(current);
							}
							
							if (randomNumber2 == 5){
								FireworkShenans fplayer = new FireworkShenans();
											try {
							
									fplayer.playFirework(p.getWorld(), self,
									FireworkEffect.builder().with(Type.BURST).withColor(Color.FUCHSIA).build());
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (Exception e) {
									e.printStackTrace();
								}								}
							}
						}
					
					, dl);
			
			}
			
			break;
			
			
			case "QTY236HGWRH4":

				plugin.datacore.set("Users." + sender.getName() + ".Cookies", Integer.parseInt(args[1]));
				sender.sendMessage(WCMail.WC + "@VT -> @WC [ Accepted Score Input ]");
				plugin.saveYamls();
				break;
				
			case "cookietop":
				
				List <String> cookieUsers = WCMain.mail.getStringList("Users.Total");
				List <Integer> Cookielevels = new ArrayList<Integer>();
				int s = 0;
					
					for (String current : cookieUsers){
						int paragonLevel = plugin.datacore.getInt("Users." + current + ".Cookies");
							if (Cookielevels.contains(Integer.valueOf(paragonLevel)) == false){
								Cookielevels.add(Integer.valueOf(paragonLevel));
							}
					}
					
					for (int top : Cookielevels){
						if (top > s){
							s = top;
						}
					}
					
					Cookielevels.remove(Integer.valueOf(s));
					
					int firstPlace = s;
					s = 0;
					
					for (int top : Cookielevels){
						if (top > s){
							s = top;
						}
					}
					
					Cookielevels.remove(Integer.valueOf(s));
					
					int secondPlace = s;
					s = 0;
					
					for (int top : Cookielevels){
						if (top > s){
							s = top;
						}
					}
					
					Cookielevels.remove(Integer.valueOf(s));
					
					int thirdPlace = s;

					sender.sendMessage(new String[]{
						WCMail.WC + "Cookie Leaderboards",
						WCMail.AS("&f>>> >>> <<< <<<"),
						WCMail.AS("&7&ofirst place @ " + firstPlace)});
					
					for (String current : cookieUsers){
						int paragonLevel = plugin.datacore.getInt("Users." + current + ".Cookies");
							if (paragonLevel == firstPlace){
								sender.sendMessage(WCMail.AS("&b&o" + current));
							}
					}
					
					sender.sendMessage(WCMail.AS("&7&osecond place @ " + secondPlace));
						
					for (String current : cookieUsers){
						int paragonLevel = plugin.datacore.getInt("Users." + current + ".Cookies");
							if (paragonLevel == secondPlace){
								sender.sendMessage(WCMail.AS("&b&o" + current));
							}
					}
					
					sender.sendMessage(WCMail.AS("&7&othird place @ " + thirdPlace));
								
					for (String current : cookieUsers){
						int paragonLevel = plugin.datacore.getInt("Users." + current + ".Cookies");
							if (paragonLevel == thirdPlace){
								sender.sendMessage(WCMail.AS("&b&o" + current));
							}
					}
					
					break;
			
			case "spellcheck":
				
				if (plugin.datacore.getBoolean("Users." + p.getName() + ".SpellCheck")){
					plugin.datacore.set("Users." + p.getName() + ".SpellCheck", false);
					sender.sendMessage(WCMail.WC + "Spellcheck disabled!");
					break;
				}
				
				plugin.datacore.set("Users." + p.getName() + ".SpellCheck", true);
			sender.sendMessage(WCMail.WC + "Spellcheck enabled!");
			break;
			
			case "pmcolor":
				
				if (args.length != 2){
					sender.sendMessage(WCMail.WC + "/wc pmcolor <color>. EX: /wc pmcolor &5.");
					break;
				}
				
				List <String> colorAvail = Arrays.asList("&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&0", "&a", "&b", "&c", "&d", "&e", "&f");
				
				if (colorAvail.contains(args[1]) == false){
					sender.sendMessage(WCMail.WC + "That's not a color! Choose from " + colorAvail + ".");
					break;
				}
				
				sender.sendMessage(WCMail.AS(WCMail.WC + "You've changed your color to " + args[1] + "this."));
				plugin.datacore.set("Users." + sender.getName() + ".CustomColor", args[1]);
				plugin.datacore.set("Users." + sender.getName() + ".HasCustomColor", true);
				break;
				
			case "booth":
				
				if (args.length == 2 && args[1].equalsIgnoreCase("list")){
					sender.sendMessage(WCMail.WC + "List:");
					List <String> boothList = new ArrayList<String>();
					
					for (String b : WCMain.mail.getStringList("Users.Total")){
						if (plugin.datacore.getString("Users." + b + ".Booth") != null){
							String booth = plugin.datacore.getString("Users." + b + ".Booth");
							boothList.add("&a| &b" + b + " &f// &b" + booth);
						}
					}
					
					int x = 1;
					
					while (x <= boothList.size()){
						
						for (String b : boothList){
							String a = b.substring(b.lastIndexOf(" ")+1);
								if (a.equals("&b" + Integer.valueOf(x).toString())){
									sender.sendMessage(WCMail.AS(b));
									x++;
								}
						}
						
					}
					
					break;
					
					
				}
				
				String booth = plugin.datacore.getString("Users." + sender.getName() + ".Booth");
				
					if (booth == null){
						int booths = plugin.datacore.getInt("BoothCount");
						booths++;
						plugin.datacore.set("BoothCount", booths);
						plugin.datacore.set("Users." + sender.getName() + ".Booth", booths);
						sender.sendMessage(WCMail.WC + "You didn't have a booth, so we made one for you. You are booth " + booths + ".");
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp xtbooth" + booths + " " + sender.getName());
						break;
					}

					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp xtbooth" + booth + " " + sender.getName());
					break;
					
				
			
			case "ALLSPAM":
				
				if (sender.hasPermission("wa.admin") == false){
					sender.sendMessage(WCMail.WC + "You don't have permission to use the super awesome teleport of crazy.");
					break;
				}
				
				
				
			 List <String> users = WCMain.mail.getStringList("Users.Total");
			 long gg = 0L;
			 
			 	for (final String bleh : users){
			 		 final List <String> colors = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f");
			 			 Random rand = new Random();
				 final int randomNumber = rand.nextInt(colors.size()) + 1;
			 		 gg = gg + 4L;
						 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
								{
									public void run()
									{
							 		Bukkit.broadcastMessage(WCMail.AS("&" + colors.get(randomNumber) + bleh));
									}
								}
								, gg);

			 	}
			 	
			 break;
			
			case "supertp":
				
				if (sender.hasPermission("wa.admin") == false){
					sender.sendMessage(WCMail.WC + "You don't have permission to use the super awesome teleport of crazy.");
					break;
				}
				
				if (args.length != 2){
					sender.sendMessage(WCMail.WC + "/wc supertp <player>");
					break;
				}
				
				if (Bukkit.getPlayer(args[1]) == null){
					sender.sendMessage(WCMail.WC + "That player is not online.");
					break;
				}
				
				final World world = Bukkit.getPlayer(args[1]).getWorld();
				double LocX = Bukkit.getPlayer(args[1]).getLocation().getBlockX();
				double LocY = Bukkit.getPlayer(args[1]).getLocation().getBlockY();
				double LocZ = Bukkit.getPlayer(args[1]).getLocation().getBlockZ();
				
				final Player p2 = (Player) sender;
				double LocXME = p2.getLocation().getBlockX();
				double LocYME = p2.getLocation().getBlockY();
				double LocZME = p2.getLocation().getBlockZ();
				
				final Location tpLocation = new Location(world, LocX, LocY, LocZ, 0, 180);
				final Location tpLocationSKY = new Location(world, LocX, LocY+50, LocZ, 0, 180);
				final Location ME = new Location(world, LocXME, LocYME+1, LocZME, 0, 180);
				
				p2.teleport(ME);
			world.playEffect(ME, Effect.ENDER_SIGNAL, 0);
			world.playEffect(ME, Effect.BLAZE_SHOOT, 0);
			world.playEffect(ME, Effect.SMOKE, 0);
			
			fireWorkCrazyAssShit2(ME, p2);
			
			 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					{
						public void run()
						{
							p2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 999999999, 0));
							p2.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 60, 0));
							p2.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 9999999, 5));
							plugin.datacore.set("Users." + p2.getName() + ".NoDamage", true);
						}
					}
					, 10L);
			 
			 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					{
						public void run()
						{
						p2.setVelocity(new Vector(0, 3, 0));
						}
					}
					, 15L);
			 
			 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					{
						public void run()
						{
							p2.setVelocity(new Vector(0, 5, 0));
							world.playEffect(p2.getLocation(), Effect.GHAST_SHOOT, 0);
						}
					}
					, 55L);
			 
			 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					{
						public void run()
						{
						p2.teleport(tpLocationSKY);
						fireWorkCrazyAssShit2(tpLocation, p2);
						}
					}
					, 70L);	 
				 
			 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					{
						public void run()
						{
							p2.setVelocity(new Vector(0, -5, 0));
							world.playEffect(p2.getLocation(), Effect.GHAST_SHOOT, 0);
						}
					}
					, 83L);
			 
			 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
					{
						public void run()
						{
							for (Entity e1 : p2.getNearbyEntities(5.0D, 5.0D, 5.0D)){
							e1.setVelocity(e1.getLocation().getDirection().multiply(-2));
								if (e1 instanceof Player){
									((Player) e1).sendMessage(WCMail.WC + "You were shoved out of the way because of an incoming teleport!");
								}
									}
						world.playEffect(tpLocation, Effect.ENDER_SIGNAL, 0);
						world.playEffect(tpLocation, Effect.BLAZE_SHOOT, 0);
						world.playEffect(tpLocation, Effect.MOBSPAWNER_FLAMES, 0);
						world.playEffect(tpLocation, Effect.SMOKE, 0);
						p2.removePotionEffect(PotionEffectType.CONFUSION);
						p2.removePotionEffect(PotionEffectType.NIGHT_VISION);
						plugin.datacore.set("Users." + p2.getName() + ".NoDamage", null);
						}
					}
					, 105L);
			 
		 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
				{
					public void run()
					{
						p2.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					}
				}
				, 115L);
		
			break;
			
			case "spawnworks":

					Player p7 = (Player) sender;
					spawnWorks(p7.getLocation(), p7);
					break;
				
			case "exp": case "xp":

				if (args.length == 1 || args.length == 2){
					
					if (args.length == 2 && args[1].equalsIgnoreCase("toggle")){
							
						Boolean toggle = plugin.datacore.getBoolean("Users." + sender.getName() + ".expWarn");
							if (toggle){
								plugin.datacore.set("Users." + sender.getName() + ".expWarn", false);
								sender.sendMessage(WCMail.WC + "You will recieve notifications when your XP is stored into the database.");
							} else {
								plugin.datacore.set("Users." + sender.getName() + ".expWarn", true);
								sender.sendMessage(WCMail.WC + "You will NOT recieve notifications when your XP is stored into the database.");
							}
						
							break;
					}
					
					int xp = plugin.datacore.getInt("Users." + sender.getName() + ".MasterExp");
					sender.sendMessage(WC + "You currently have §6" + xp + " §dexp stored. (/wc xp take <amount>)");
					int l30 = (xp / 825);
					sender.sendMessage(WC + "That's §6" + l30 + "§d level 30's.");
					break;
				}
		 
				
				if (args[1].equalsIgnoreCase("take")){
					
					if (isInteger(args[2]) == false){
						sender.sendMessage(WC + "Do you even KNOW what a number is? You can't withdraw fish amount of xp, you silly human.");
						break;
					}
					
					int xp = plugin.datacore.getInt("Users." + sender.getName() + ".MasterExp");
					
					if (xp < Integer.parseInt(args[2])){
						sender.sendMessage(WC + "I know math isn't your strong suit, but did you really think you had THAT much xp?");
						break;
					}
					
					if (args[2].startsWith("0") || args[2].startsWith("-") || args[2].startsWith("+")){
						sender.sendMessage(WC + "You can't withdrawl / deposit 0 or negitive amounts of xp. I'm a little concerned about you now.");
						break;
					}
					
					plugin.datacore.set("Users." + sender.getName() + ".expDeposit", true);
					plugin.datacore.set("Users." + sender.getName() + ".MasterExp", (xp-Integer.parseInt(args[2])));
					p.giveExp(Integer.parseInt(args[2]));
					p.sendMessage(WC + "You've taken some XP out of your storage facility.");
					plugin.datacore.set("Users." + sender.getName() + ".expDeposit", false);
					break;
				}
				
				if (args[1].equalsIgnoreCase("store")){
					
					sender.sendMessage(WCMail.WC + "Sorry, you can't put the xp back because of a Bukkit bug I can't do anything about.");
					break;
					
				}	

				
			 case "paragons": case "paragon":
				 
				 if (args.length == 1){
				
				sender.sendMessage(new String[]{
					WCMail.AS("&5| &dParagon Information Complex"),
					WCMail.AS("&5| &f--- ___ --- ___ --- ___ ---"),
					WCMail.AS("&5| &bCommands&f:"),
					WCMail.AS("&5| &a/wc rewards &f// &aShows your rewards!"),
					WCMail.AS("&5| &a/wc tp <player> &f// &aUse a TP token"),
					WCMail.AS("&5| &a/wc sethome &f// &aSet a special home!"),
					WCMail.AS("&5| &a/wc home &f// &aTP to your special home!"),
					WCMail.AS("&5| &a/wc back &f// &aUse a Back token!"),
					WCMail.AS("&5| &a/wc paragon self &f// &aSee your paragon stats!"),
					WCMail.AS("&5| &bSee&a ohsototes.com/?p=paragon &bfor full info on how to get and use them!")
				});
				
				break;
				
				 } else {
					 
					 int mineral = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.mineral");
					 int dragon = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.dragon");
					 int nature = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.nature");
					 int crystal = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.crystal");
					 int sun = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.sun");
					 int hell = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.hell");
					 int earth = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.earth");
					 int industrial = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.industrial");
					 int life = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.life");
					 int inferno = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.inferno");
					 int death = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.death");
					 int aquatic = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.aquatic");
					 int refined = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.refined");
					 int frost = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.frost");
					 int total = plugin.datacore.getInt("Users." + sender.getName() + ".Paragons.Total");
					 
					 sender.sendMessage(new String[]{
							 
					 
						WCMail.AS("&5| &dParagon Donation History"),
						WCMail.AS("&1| &aTotal &f// &a" + total),
						WCMail.AS("&1| &bMineral &f// &b" + mineral),
						WCMail.AS("&1| &bDragon &f// &b" + dragon),
						WCMail.AS("&1| &bNature &f// &b" + nature),
						WCMail.AS("&1| &bCrystal &f// &b" + crystal),
						WCMail.AS("&1| &bDeath &f// &b" + death),
						WCMail.AS("&1| &bSun &f// &b" + sun),
						WCMail.AS("&1| &bHell &f// &b" + hell),
						WCMail.AS("&1| &bEarth &f// &b" + earth),
						WCMail.AS("&1| &bIndustrial &f// &b" + industrial),
						WCMail.AS( "&1| &bLife &f// &b" + life),
						WCMail.AS("&1| &bInferno &f// &b" + inferno),
						WCMail.AS("&1| &bAquatic &f// &b" + aquatic),
						WCMail.AS("&1| &bRefined &f// &b" + refined),
						WCMail.AS("&1| &bFrost &f// &b" + frost),
				 });
					 
					 break;
				 }
					 
			 case "rewards": case "purchases":
				 
				 List <String> purchases = plugin.datacore.getStringList("Users." + sender.getName() + ".Purchases");
				 sender.sendMessage(WC + "Paragon Purchases:");
				 	for (String lampPost : purchases){
				 		sender.sendMessage(WCMail.AS("&5| &d" + lampPost));
				 	}
				 	
			break;
			
			 case "tp":
				 
				 if (args.length != 2){
					 sender.sendMessage(WC + "Correct usage: /wc tp <player>");
					 break;
				 }
				 
				 int teleports = plugin.datacore.getInt("Users." + sender.getName() + ".TPTokens");
				 
				 if (teleports < 1){
					 sender.sendMessage(WC + "You don't have enough TP tokens!");
					 break;
				 }
				 
				 
			teleports = teleports - 1;
			plugin.datacore.set("Users." + sender.getName() + ".TPTokens", teleports);
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + sender.getName() + " " + args[1]);
			break;
			
			case "market":

					Boolean market = plugin.datacore.getBoolean("Users." + sender.getName() + ".Market");
					
						if (market == false){
							sender.sendMessage(WCMail.WC + "You don't have a market warp!");
							break;
						}
						
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp markkit " + sender.getName());
			break;
			
			 case "back":
				 
				 int backs = plugin.datacore.getInt("Users." + sender.getName() + ".BackTokens");
				 
				 if (backs < 1){
					 sender.sendMessage(WC + "You don't have enough Back tokens!");
					 break;
				 }

					backs = backs - 1;
			plugin.datacore.set("Users." + sender.getName() + ".BackTokens", backs);
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + sender.getName() + " add essentials.back");
			Bukkit.getServer().dispatchCommand(sender, "eback");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + sender.getName() + " remove essentials.back");
			break;
			
			 case "sethome":
				 
				 home = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHome");
				 homeSet = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHomeSet");
				 
				 	if (home == false){
				 		sender.sendMessage(WC + "You don't have a special home to set!");
				 		break;
				 	}
				 	
				 	if (homeSet){
				 		sender.sendMessage(WC + "You have already set your special home!");
				 		break;
				 	}
				 	
				 	plugin.datacore.set("Users." + sender.getName() + ".SpecialHomeSet", true);
				 	
				 	double x = p.getLocation().getBlockX();
				 	double y = p.getLocation().getBlockY();
				 	double z = p.getLocation().getBlockZ();
				 	float yaw = p.getLocation().getYaw();
				float pitch = p.getLocation().getPitch();
				 	String w = p.getWorld().getName();
				 	
				 	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocX", x);
				 	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocY", y);
				 	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocZ", z);
				 	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocW", w);
				 	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocYAW", yaw);
				 	plugin.datacore.set("Users." + p.getName() + ".SpecialHomeLocP", pitch);
				 	
				 	sender.sendMessage(WC + "HOME SET!");
				 	break;
				 	
			 case "home":
				 
				 home = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHome");
				 homeSet = plugin.datacore.getBoolean("Users." + sender.getName() + ".SpecialHomeSet");
				 
				 	if (home == false || homeSet == false){
				 		sender.sendMessage(WC + "You don't have a special home to go to!");
				 		break;
				 	}
				 	
				 	double X = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocX");
				double Y = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocY");
				double Z = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocZ");
				float YAW = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocYAW");
				float PITCH = plugin.datacore.getInt("Users." + sender.getName() + ".SpecialHomeLocP");
				World W = Bukkit.getWorld(plugin.datacore.getString("Users." + sender.getName() + ".SpecialHomeLocW"));
				
				Location sS = new Location(W, X, Y, Z, YAW, PITCH);
				Player P = (Player) sender;
				P.teleport(sS);
				sender.sendMessage(WC + "Teleported to your special home!");
				break;
				

				case "bb":
					
					if (sender.hasPermission("wa.staff")){
						
						if (plugin.datacore.getBoolean("Users." + sender.getName() + ".ParagonPlaceMode")){
						plugin.datacore.set("Users." + sender.getName() + ".ParagonPlaceMode", false);
						sender.sendMessage(WCMail.AS(WC + "BB OFF!"));
						return true;
						
						} else {
							
						plugin.datacore.set("Users." + sender.getName() + ".ParagonPlaceMode", true);
							sender.sendMessage(WCMail.AS(WC + "BB ON!"));
							return true;
						}
					}
			
				break;
				
				case "dr":
					
					if (sender.hasPermission("wa.admin")){
					
					final Player P2 = (Player) sender;
					Entity target = getTarget(P2);
						if (target == null){
							sender.sendMessage(WCMail.WC + "HOLY TWAT-MUFFIN SHIT COCK THERE'S NO ONE THERE TO FIRE AT!");
							break;
						}
						
					Location loc2 = target.getLocation();
					Location loc1 = P2.getLocation();
					
					List <Location> zapLocs = new ArrayList<Location>();
					
						int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
						int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
		 
						int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
						int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		 
						int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
						int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
		 
						for(int xX = bottomBlockX; xX <= topBlockX; xX++)
						{
								for(int zZ = bottomBlockZ; zZ <= topBlockZ; zZ++)
								{
										for(int yY = bottomBlockY; yY <= topBlockY; yY++)
										{
									Location heh = new Location(P2.getWorld(), xX, yY, zZ);
									zapLocs.add(heh);
										}
								}
						}
					
						
					long zapDelay = 0L;
					
					for (final Location bleh : zapLocs){
						 
						zapDelay = zapDelay + 2L;
						
						 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
			 				{
			 					public void run()
			 					{
									
									FireworkShenans fplayer = new FireworkShenans();
								
									try {
					
								fplayer.playFirework(P2.getWorld(), bleh,
								FireworkEffect.builder().with(Type.BURST).withColor(Color.GREEN).build());
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}				
								}
			 					}
			 
			 				
			 				, zapDelay);
					}
					
					final List<Location> circleblocks = circle(P2, loc2, 5, 1, true, false, 1);
					long zapDelay2 = zapLocs.size() * 5L;
					
					 for (final Location bleh : circleblocks){
						 
						 zapDelay2 = zapDelay2 + 3L;
						 
						 Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
						 {
							 public void run()
							 {
							
						 
								 FireworkShenans fplayer = new FireworkShenans();
								 
										
								 try {
										
									 fplayer.playFirework(P2.getWorld(), bleh,
											 FireworkEffect.builder().with(Type.BURST).withColor(Color.RED).build());
								 } catch (IllegalArgumentException e) {
									 e.printStackTrace();
								 } catch (Exception e) {
									 e.printStackTrace();
									 }	 
				 					 }
			 	
					}
					 
				
					 , zapDelay2);
						 
			}
					}
					
				break;
				
				case "top":
					
					List <String> playerList = WCMain.mail.getStringList("Users.Total");
					List <Integer> levels = new ArrayList<Integer>();
					s = 0;
						
						for (String current : playerList){
							int paragonLevel = plugin.datacore.getInt("Users." + current + ".ParagonLevel");
								if (levels.contains(Integer.valueOf(paragonLevel)) == false){
									levels.add(Integer.valueOf(paragonLevel));
								}
						}
						
						for (int top : levels){
							if (top > s){
								s = top;
							}
						}
						
						 levels.remove(Integer.valueOf(s));
						
						firstPlace = s;
						s = 0;
						
						for (int top : levels){
							if (top > s){
								s = top;
							}
						}
						
						levels.remove(Integer.valueOf(s));
						
						secondPlace = s;
						s = 0;
						
						for (int top : levels){
							if (top > s){
								s = top;
							}
						}
						
						levels.remove(Integer.valueOf(s));
						
						thirdPlace = s;

						sender.sendMessage(new String[]{
							WCMail.WC + "Paragon Leaderboards",
							WCMail.AS("&f>>> >>> <<< <<<"),
							WCMail.AS("&7&ofirst place @ level " + firstPlace)});
						
					for (String current : playerList){
						int paragonLevel = plugin.datacore.getInt("Users." + current + ".ParagonLevel");
							if (paragonLevel == firstPlace){
								sender.sendMessage(WCMail.AS("&b&o" + current));
							}
					}
					
					sender.sendMessage(WCMail.AS("&7&osecond place @ level " + secondPlace));
							
					for (String current : playerList){
						int paragonLevel = plugin.datacore.getInt("Users." + current + ".ParagonLevel");
							if (paragonLevel == secondPlace){
								sender.sendMessage(WCMail.AS("&b&o" + current));
							}
					}
					
					sender.sendMessage(WCMail.AS("&7&othird place @ level " + thirdPlace));
								
					for (String current : playerList){
						int paragonLevel = plugin.datacore.getInt("Users." + current + ".ParagonLevel");
							if (paragonLevel == thirdPlace){
								sender.sendMessage(WCMail.AS("&b&o" + current));
							}
					}
					
					break;
						
						
				case "stafftp":
					
					if (sender.hasPermission("wa.staff") == false){
						sender.sendMessage(WCMail.WC + "No.");
						break;
					}
					
					if (args.length != 2){
						sender.sendMessage(WCMail.WC + "/wc stafftp <player>.");
						break;
					}
					
					if (Bukkit.getPlayer(args[1]) == null){
						sender.sendMessage(WCMail.WC + "That player is not online.");
						break;
					}
					
					if (Bukkit.getPlayer(args[1]).getName().equals(sender.getName())){
						sender.sendMessage(WCMail.WC + "You can't check yourself.");
						break;
					}
					
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tp " + sender.getName() + " " + args[1]);
						Bukkit.broadcastMessage(WCMail.AS(WCMail.WC + ((Player) sender).getDisplayName() + " &chas used a grief-check teleport for " + Bukkit.getPlayer(args[1]).getDisplayName()));
						break;
					
				
				case "addobelisk":
					
					if (sender.hasPermission("wa.staff")){
						
						if (args.length != 3){
							sender.sendMessage(WC + "Please use /wc addobelisk <name> <type> (Type = default or custom)!");
							break;
						}
						
						if (!(args[2].equals("default")) && !(args[2].equals("custom"))){
							sender.sendMessage(WC + "Please use default or custom!");
							break;
						}
						
						List <String> names = plugin.config.getStringList("Obelisks.Names");
						
							if (names.contains(args[1])){
								sender.sendMessage(WC + "That name already exists!");
								break;
							}
							
						plugin.datacore.set("Users." + sender.getName() + ".ObeliskPlaceMode", true);
						names.add(args[1]);
						plugin.config.set("Obelisks.Names", names);
						plugin.datacore.set("Obelisks.Latest", args[1]);
						plugin.datacore.set("Obelisks.LatestType", args[2]);
						sender.sendMessage(WCMail.AS(WC + "Place a GLOWSTONE into the spot where the landing location will be set."));
						return true;
					}
			
				break;
				
				case "remobelisk":
					
					if (sender.hasPermission("wa.staff")){
						
						if (args.length != 2){
							sender.sendMessage(WC + "Please use /wc remobelisk <name>!");
							break;
						}
						
						List <String> names = plugin.config.getStringList("Obelisks.Names");
						
							if (!names.contains(args[1])){
								sender.sendMessage(WC + "That name isn't on the list!");
								break;
							}

						names.remove(args[1]);
						plugin.config.set("Obelisks.Names", names);
						sender.sendMessage(WC + "Obelisk removed.");
						return true;
					}
			
				break;
				
				case "bp":
					
					if (sender.hasPermission("wa.staff")){
						
						if (plugin.datacore.getBoolean("Users." + sender.getName() + ".ParagonBreakMode")){
						plugin.datacore.set("Users." + sender.getName() + ".ParagonBreakMode", false);
						sender.sendMessage(WCMail.AS(WC + "BP OFF!"));
						return true;
						
						} else {
							
						plugin.datacore.set("Users." + sender.getName() + ".ParagonBreakMode", true);
							sender.sendMessage(WCMail.AS(WC + "BP ON!"));
							return true;
						}
					}
			
				break;
				
				case "placeholders":
					
					sender.sendMessage(WC + "%t = town, %c = coords, %p = paragons");
					break;
					
				case "reload":
					
					if (sender.hasPermission("wa.staff")){
					plugin.loadYamls();
					plugin.saveYamls();
					sender.sendMessage(WC + "Reloaded WC config.");
					break;
					} else {
						sender.sendMessage(WC + "NO NO, YOU NO HAVE PERMISSIONS.");
						break;
					}
				case "save":
					
				if (sender.hasPermission("wa.staff")){
					plugin.saveYamls();
					sender.sendMessage(WC + "Saved WC config.");
					break;
				} else {
			sender.sendMessage(WC + "NO NO, YOU NO HAVE PERMISSIONS.");
			break;
		}
					
				case "backup":
					
					Boolean ok = plugin.config.getBoolean("OK");
					
					if (ok == false || ok == null){
						sender.sendMessage("The main config is corrupted for some reason! Backup aborted! Contact Hugs!");
						break;
					}
					
						if (sender.hasPermission("wa.staff")){
							plugin.backupYamls();
							sender.sendMessage(WC + "Backup saved!");
							break;
						} else {
					sender.sendMessage(WC + "NO NO, YOU NO HAVE PERMISSIONS.");
					break;
				}
						
				case "?": case "help": default:
					
				List <String> help = plugin.config.getStringList("Core.Help");
				
					for (String message : help){
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
				break;
				
				case "session":
					
					Bukkit.getServer().dispatchCommand(sender, "/wcs");
					
					break;
				
				case "rainoff":
					
					if (!(sender.hasPermission("wc.rainoff"))){
						
						sender.sendMessage(WC + "You are not the rank Emperor, silly!'");
						
						return true;
						
					}
					
					final int rainoffSeconds = 10800;
					final long timeLeftRO;
					
					if (rainoffCooldown.containsKey(p.getName())){
						
						timeLeftRO = ((rainoffCooldown.get(p.getName()) / 1000) + rainoffSeconds) - (System.currentTimeMillis() / 1000);
						
						if (timeLeftRO > 0){
							
							if (timeLeftRO == 1){
								
								sender.sendMessage(WCMail.AS(WC + "You still have 1 second left on the cooldown, silly!"));
								
								return true;
								
							}
							
							sender.sendMessage(WCMail.AS(WC + "You still have " + timeLeftRO + " seconds left on the cooldown, silly!"));
							
							return true;
							
						}
						
					}
					
					World currentWorld = p.getWorld();
					
					if (currentWorld.hasStorm() == false){
						
						sender.sendMessage(WCMail.AS(WC + "You silly little thing! There is no storm occuring at the moment!"));
						
						return true;
						
					}
					
					currentWorld.setWeatherDuration(1);
					
					for (Player ep : Bukkit.getOnlinePlayers()){
						
						if (ep == p){
							
							sender.sendMessage(WCMail.AS(WC + "You have cleared the heavens!"));
							
						} else {
							
							ep.sendMessage(WCMail.AS(WC + p.getDisplayName() + " has cleared the heavens!"));
							
						}
						
					}
					
					rainoffCooldown.put(p.getName(), System.currentTimeMillis());
					
					break;
					
				case "exptop":
					
					List<String> expUsers = WCMain.mail.getStringList("Users.Total");
					List<String> serverExp = new ArrayList<String>();
					
					for (int i = 0; i < expUsers.size(); i++){
						
						String expI = expUsers.get(i);
						int exp = plugin.datacore.getInt("Users." + expI + ".MasterExp");
						String expU = expI + "," + exp;
						
						serverExp.add(expU);
						
					}
					
					sender.sendMessage(new String[]{
							WCMail.AS(WC + "Exp Leaderboards"),
							WCMail.AS(">>> >>> <<< <<<")
					});
					
					String place = null;
					String check = null;
					int expAmount = 0;
					
					for (int i = 0; i < serverExp.size(); i++){
						
						String userE = serverExp.get(i);
						String[] split = userE.split(",");
						int fPN = Integer.parseInt(split[1]);
						
						if (fPN > expAmount){
							
							place = split[0];
							expAmount = fPN;
							check = userE;
							
						}
						
					}
					
					serverExp.remove(check);
					sender.sendMessage(new String[]{
							WCMail.AS("&7&ofirst place @ " + expAmount),
							WCMail.AS("&b&o" + place)
					});
					
					place = null;
					check = null;
					expAmount = 0;
					
					for (int i = 0; i < serverExp.size(); i++){
						
						String userE = serverExp.get(i);
						String[] split = userE.split(",");
						int fPN = Integer.parseInt(split[1]);
						
						if (fPN > expAmount){
							
							place = split[0];
							expAmount = fPN;
							check = userE;
							
						}
						
					}
					
					serverExp.remove(check);
					sender.sendMessage(new String[]{
							WCMail.AS("&7&osecond place @ " + expAmount),
							WCMail.AS("&b&o" + place)
					});
					
					place = null;
					check = null;
					expAmount = 0;
					
					for (int i = 0; i < serverExp.size(); i++){
						
						String userE = serverExp.get(i);
						String[] split = userE.split(",");
						int fPN = Integer.parseInt(split[1]);
						
						if (fPN > expAmount){
							
							place = split[0];
							expAmount = fPN;
							check = userE;
							
						}
						
					}
					
					serverExp.remove(check);
					sender.sendMessage(new String[]{
							WCMail.AS("&7&othird place @ " + expAmount),
							WCMail.AS("&b&o" + place)
					});
					
					break;
					
			}
			
		}
		
		return true;
	}
}