package com.github.lyokofirelyte.WaterCloset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import com.github.lyokofirelyte.WaterCloset.Alliances.WACommandEx;
import com.github.lyokofirelyte.WaterCloset.Extras.StaticField;
import com.github.lyokofirelyte.WaterCloset.Extras.TNTNerf;
import com.github.lyokofirelyte.WaterCloset.Extras.TimeStampEX;
import com.github.lyokofirelyte.WaterCloset.Extras.TraceFW;
import com.github.lyokofirelyte.WaterCloset.Extras.waOSReport;
import com.github.lyokofirelyte.WaterCloset.Games.HungerGames.CGMain;

public class WCMain extends JavaPlugin {
	
  public WCVault vaultMgr = new WCVault(this);
  
  File WAGamesconfigFile;
  File WAGamesdatacoreFile;
  
  File WAGamesconfigFileBACKUP;
  File WAGamesdatacoreFileBACKUP;
  
  File configFile;
  File datacoreFile;
  
  File configFileBACKUP;
  File datacoreFileBACKUP;
  
  File WAAlliancesconfigFile;
  File WAAlliancesdatacoreFile;
  
  File WAAlliancesconfigFileBACKUP;
  File WAAlliancesdatacoreFileBACKUP;
  
  File mailFile;
  File helpFile;
  
  File mailFileBACKUP;
  File helpFileBACKUP;
  
  public FileConfiguration WAGamesconfig;
  public FileConfiguration WAGamesdatacore;
  
  public FileConfiguration WAGamesconfigBACKUP;
  public FileConfiguration WAGamesdatacoreBACKUP;
  
  public FileConfiguration config;
  public FileConfiguration datacore;
  
  public FileConfiguration configBACKUP;
  public FileConfiguration datacoreBACKUP;
  
  public FileConfiguration WAAlliancesconfig;
  public FileConfiguration WAAlliancesdatacore;
  
  public FileConfiguration WAAlliancesconfigBACKUP;
  public FileConfiguration WAAlliancesdatacoreBACKUP; 
  
  public static FileConfiguration mail;
  public static FileConfiguration help;
  
  public static FileConfiguration mailBACKUP;
  public static FileConfiguration helpBACKUP;
  
  private String url;
  private String username;
  private String password;
  private Connection conn;

  public void onEnable()
  {

    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new WACommandEx(this), this);
    pm.registerEvents(new WCJoin(this), this);
    pm.registerEvents(new WCQuit(this), this);
    pm.registerEvents(new StaticField(this), this);
    pm.registerEvents(new WCChannels(this), this);
    pm.registerEvents(new WCMobDrops(this), this);
    pm.registerEvents(new TNTNerf(this), this);
    pm.registerEvents(new WCBlockBreak(this), this);
    pm.registerEvents(new WCBlockPlace(this), this);
    pm.registerEvents(new WCExpSystem(this), this);
    pm.registerEvents(new WCDeath(this), this);
    pm.registerEvents(new WCInteract(this), this);

    this.WAGamesconfigFile = new File(getDataFolder() + File.separator + "WAGames", "config.yml");
    this.WAGamesdatacoreFile = new File(getDataFolder() + File.separator + "WAGames", "datacore.yml");
    
    this.WAGamesconfigFileBACKUP = new File(getDataFolder() + File.separator + "Backups", "WAGamesconfigBACKUP.yml");
    this.WAGamesdatacoreFileBACKUP = new File(getDataFolder() + File.separator + "Backups", "WAGamesdatacoreBACKUP.yml");

    this.WAAlliancesconfigFile = new File(getDataFolder() + File.separator + "WAAlliances", "config.yml");
    this.WAAlliancesdatacoreFile = new File(getDataFolder() + File.separator + "WAAlliances", "datacore.yml");
    
    this.WAAlliancesconfigFileBACKUP = new File(getDataFolder() + File.separator + "Backups", "WAAlliancesconfigBACKUP.yml");
    this.WAAlliancesdatacoreFileBACKUP = new File(getDataFolder() + File.separator + "Backups", "WAAlliancesdatacoreBACKUP.yml");

    this.configFile = new File(getDataFolder(), "config.yml");
    this.configFileBACKUP = new File(getDataFolder() + File.separator + "Backups", "configBACKUP.yml");
    
    this.datacoreFile =  new File(getDataFolder(), "datacore.yml");
    this.datacoreFileBACKUP =  new File(getDataFolder() + File.separator + "Backups", "datacoreBACKUP.yml");
    
    this.mailFile = new File(getDataFolder(), "mail.yml");
    this.mailFileBACKUP = new File(getDataFolder() + File.separator + "Backups", "mailBACKUP.yml");
    
    this.helpFile = new File(getDataFolder(), "help.yml");
    this.helpFileBACKUP = new File(getDataFolder() + File.separator + "Backups", "helpBACKUP.yml");


    this.vaultMgr.hookSetup();
    try
    {
      firstRun();
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.WAAlliancesconfig = new YamlConfiguration();
    this.WAAlliancesdatacore = new YamlConfiguration();
    
    this.WAAlliancesconfigBACKUP = new YamlConfiguration();
    this.WAAlliancesdatacoreBACKUP = new YamlConfiguration();
    
    this.WAGamesconfig = new YamlConfiguration();
    this.WAGamesdatacore = new YamlConfiguration();
    
    this.WAGamesconfigBACKUP = new YamlConfiguration();
    this.WAGamesdatacoreBACKUP = new YamlConfiguration();
    
    this.config = new YamlConfiguration();
    this.configBACKUP = new YamlConfiguration();
    
    this.datacore = new YamlConfiguration();
    this.datacoreBACKUP = new YamlConfiguration();
    
    WCMain.mail = new YamlConfiguration();
    WCMain.mailBACKUP = new YamlConfiguration();
    
    WCMain.help = new YamlConfiguration();
    WCMain.helpBACKUP = new YamlConfiguration();
    
    loadYamls();

    url = config.getString("url");
    username = config.getString("username");
    password = config.getString("password");

    if ((url == null) || (username == null) || (password == null))
    {
      Bukkit.getServer().getLogger().info("You must provide a url, username, and password in the config.yml.");
      Bukkit.getServer().getPluginManager().disablePlugin(this);
    }

    
    registerCommands();
    
    
    getLogger().log(Level.INFO, "CORE HAS BEEN INITIALIZED.");

    List<String> pluginList = this.config.getStringList("Core.Plugins");

    for (String pluginMessage : pluginList)
    {
      getLogger().log(Level.INFO, pluginMessage.toUpperCase() + " IS READY.");
    }
    
  	}

  public void onDisable() {
	  
    getLogger().info("CORE AND ALL EXTENSIONS HAVE BEEN DEACTIVATED.");
    saveYamls();
    
    try
    {
      if (this.conn != null)
      {
        this.conn.close();
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  
  private void registerCommands()
  {
    getCommand("watercloset").setExecutor(new WCCommands(this));
    getCommand("wc").setExecutor(new WCCommands(this));

    getCommand("waa").setExecutor(new WACommandEx(this));
    getCommand("l").setExecutor(new WCChannels(this));
    getCommand("o").setExecutor(new WCChannels(this));
    getCommand("nick").setExecutor(new WACommandEx(this));

    getCommand("timestamp").setExecutor(new TimeStampEX(this));
    getCommand("getnick").setExecutor(new TimeStampEX(this));
    getCommand("stringbuilder").setExecutor(new TimeStampEX(this));
    getCommand("itemname").setExecutor(new TimeStampEX(this));

    getCommand("msg").setExecutor(new WCChannels(this));
    getCommand("tell").setExecutor(new WCChannels(this));
    getCommand("t").setExecutor(new WCChannels(this));
    getCommand("r").setExecutor(new WCChannels(this));

    getCommand("forcefield").setExecutor(new StaticField(this));
    getCommand("ff").setExecutor(new StaticField(this));
    
    getCommand("report").setExecutor(new waOSReport(this));
    
    getCommand("mail").setExecutor(new WCMail(this));
    
    getCommand("search").setExecutor(new WCHelp(this));
    
    getCommand("blame").setExecutor(new WCCommands(this));
    
    getCommand("member").setExecutor(new WCCommands(this));
    
    getCommand("cg").setExecutor(new CGMain(this));
    
    getCommand("home").setExecutor(new WCHome(this));
    getCommand("sethome").setExecutor(new WCHome(this));
    getCommand("remhome").setExecutor(new WCHome(this));
    getCommand("delhome").setExecutor(new WCHome(this));
    
    getCommand("bday").setExecutor(new TraceFW(this));
    
    getCommand("warp").setExecutor(new WCWarps(this));
    getCommand("w").setExecutor(new WCWarps(this));
    getCommand("setwarp").setExecutor(new WCWarps(this));
    getCommand("remwarp").setExecutor(new WCWarps(this));
    getCommand("delwarp").setExecutor(new WCWarps(this));
    
  }

  public void copy(InputStream in, File file)
  {
    try
    {
      OutputStream out = new FileOutputStream(file);
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0)
      {
        out.write(buf, 0, len);
      }
      out.close();
      in.close();
    } catch (Exception e) {
  //  e.printStackTrace();
    }
  }
  
  public void backupYamls() throws InvalidConfigurationException {
	    
	  try
	    {
	      this.config.save(this.configFileBACKUP);
	      this.datacore.save(this.datacoreFileBACKUP);
	      WCMain.mail.save(this.mailFileBACKUP);
	      WCMain.help.save(this.helpFileBACKUP);

	      this.WAGamesconfig.save(this.WAGamesconfigFileBACKUP);
	      this.WAGamesdatacore.save(this.WAGamesdatacoreFileBACKUP);

	      this.WAAlliancesconfig.save(this.WAAlliancesconfigFileBACKUP);
	      this.WAAlliancesdatacore.save(this.WAAlliancesdatacoreFileBACKUP);
	      
	      List <String> users = datacore.getStringList("FileSystem.Users");
	      
	      	for (String user : users){
	      		FileConfiguration userConfig = new YamlConfiguration();
	      		File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
	      		File userFileBackup = new File(getDataFolder() + File.separator + "Backups" + File.separator + "Users", user + ".yml");
	      		userConfig.load(userFile);
	      		userConfig.save(userFileBackup);
	      	}
	    
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
  }

  public void saveYamls()
  {
    try
    {
      this.config.save(this.configFile);
      this.datacore.save(this.datacoreFile);
      WCMain.mail.save(this.mailFile);
      WCMain.help.save(this.helpFile);

      this.WAGamesconfig.save(this.WAGamesconfigFile);
      this.WAGamesdatacore.save(this.WAGamesdatacoreFile);

      this.WAAlliancesconfig.save(this.WAAlliancesconfigFile);
      this.WAAlliancesdatacore.save(this.WAAlliancesdatacoreFile);
    
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveWC()
  {
    try {
      this.config.save(this.configFile);
      this.datacore.save(this.datacoreFile);
      WCMain.mail.save(this.mailFile);
      WCMain.help.save(this.helpFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveWAGames()
  {
    try {
      this.WAGamesconfig.save(this.WAGamesconfigFile);
      this.WAGamesdatacore.save(this.WAGamesdatacoreFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadWAGames()
  {
    try
    {
      this.WAGamesconfig.load(this.WAGamesconfigFile);
      this.WAGamesdatacore.load(this.WAGamesdatacoreFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void saveWAAlliances()
  {
    try
    {
      this.WAAlliancesconfig.save(this.WAAlliancesconfigFile);
      this.WAAlliancesdatacore.save(this.WAAlliancesdatacoreFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadWAAlliances()
  {
    try
    {
      this.WAAlliancesconfig.load(this.WAAlliancesconfigFile);
      this.WAAlliancesdatacore.load(this.WAAlliancesdatacoreFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void loadYamls()
  {
    try
    {
      this.config.load(this.configFile);
      this.datacore.load(this.datacoreFile);
      WCMain.mail.load(this.mailFile);
      WCMain.help.load(this.helpFile);

      this.WAGamesconfig.load(this.WAGamesconfigFile);
      this.WAGamesdatacore.load(this.WAGamesdatacoreFile);

      this.WAAlliancesconfig.load(this.WAAlliancesconfigFile);
      this.WAAlliancesdatacore.load(this.WAAlliancesdatacoreFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void loadWC() {
    try {
      this.config.load(this.configFile);
      this.datacore.load(this.datacoreFile);
      WCMain.mail.load(this.mailFile);
      WCMain.help.load(this.helpFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void firstRun() throws Exception {
    if (!this.configFile.exists()) {
    	this.configFile.getParentFile().mkdirs();
    	copy(getResource("config.yml"), this.configFile);
    }
    
    if (!this.mailFile.exists()){
        this.mailFile.getParentFile().mkdirs();
        copy(getResource("mail.yml"), this.mailFile);
    }
    
    if (!this.helpFile.exists()){
        this.helpFile.getParentFile().mkdirs();
        copy(getResource("help.yml"), this.helpFile);
    }
    
    if (!this.WAGamesconfigFile.exists()){
        this.WAGamesconfigFile.getParentFile().mkdirs();
        copy(getResource("WAGamesconfig.yml"), this.WAGamesconfigFile);
    }
    
    if (!this.WAAlliancesconfigFile.exists()){
        this.WAAlliancesconfigFile.getParentFile().mkdirs();
        copy(getResource("WAAlliancesconfig.yml"), this.WAAlliancesconfigFile);
    }
    
    if (!this.datacoreFile.exists()) {
    	this.datacoreFile.getParentFile().mkdirs();
        copy(getResource("datacore.yml"), this.datacoreFile);
    }
    
    if (!this.WAGamesdatacoreFile.exists()){
      this.WAGamesdatacoreFile.getParentFile().mkdirs();
      copy(getResource("WAGamesdatacore.yml"), this.WAGamesdatacoreFile);
    }
    
    if (!this.WAAlliancesdatacoreFile.exists()){ 	
      this.WAAlliancesdatacoreFile.getParentFile().mkdirs();
      copy(getResource("WAAlliancesdatacore.yml"), this.WAAlliancesdatacoreFile);
    }
  }
  
FileConfiguration loadedFile;
  
	public void userCreate(String user) throws Exception {
		
		loadedFile = new YamlConfiguration();
		File fileToCheck = new File(getDataFolder() + File.separator + "Users", user + ".yml");

		    if (!fileToCheck.exists()) {
		    	fileToCheck.getParentFile().mkdirs();
		    	copy(getResource(user + ".yml"), fileToCheck);
		    	userLogUpdate(user);
		    	getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&aNEW USER FILE CREATED FOR " + user.toUpperCase() + "&c!"));
		    	
		    }

	}
	

	  public void userWriteS(String user, String path, String data){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
			
			try {
				  loadedFile.load(userFile);
			} catch (Exception e) {
				  e.printStackTrace();
				}
			
		  loadedFile.set(path, data);
		  
		  try {
			loadedFile.save(userFile);
		  } catch (IOException e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE WRITING FILE FOR " + user + "!"));
		      }
		  
	  }
	  
	  public void userWriteSL(String user, String path, List<String> data){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
			
			try {
				  loadedFile.load(userFile);
			} catch (Exception e) {
				  e.printStackTrace();
				}
			
		  loadedFile.set(path, data);
		  
		  try {
			loadedFile.save(userFile);
		  } catch (IOException e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE WRITING FILE FOR " + user + "!"));
		      }
		  
	  }
	  

	  public void userWriteI(String user, String path, int data){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
			
			try {
				  loadedFile.load(userFile);
			} catch (Exception e) {
				  e.printStackTrace();
				}
			
		  loadedFile.set(path, data);
		  
		  try {
			loadedFile.save(userFile);
		  } catch (IOException e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE WRITING FILE FOR " + user + "!"));
		      }
		  
	  }
	  
	  public void userWriteB(String user, String path, Boolean data){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
			
			try {
				  loadedFile.load(userFile);
			} catch (Exception e) {
				  e.printStackTrace();
				}
			
		  loadedFile.set(path, data);
		  
		  try {
			loadedFile.save(userFile);
		  } catch (IOException e) {
			e.printStackTrace();
			Bukkit.getServer().getConsoleSender().sendMessage(WCMail.AS(WCMail.WC + "&cISSUE WRITING FILE FOR " + user + "!"));
		      }
		  
	  }
	  
	  public Boolean userGrabB(String user, String path){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getBoolean(path);
	  }
	  
	  public String userGrabS(String user, String path){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getString(path);
	  }
	  
	  public int userGrabI(String user, String path){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getInt(path);
	  }
	  
	  public List <String> userGrabSL(String user, String path){
		  
		  File userFile = new File(getDataFolder() + File.separator + "Users", user + ".yml");
		  loadedFile = new YamlConfiguration();
		  
		  try {
			  loadedFile.load(userFile);
		} catch (Exception e) {
			  e.printStackTrace();
			}
		  
		  return loadedFile.getStringList(path);
	  }
	  
	  public void userLogUpdate(String user){
		  List <String> users = datacore.getStringList("FileSystem.Users");
		  users.add(user);
		  datacore.set("FileSystem.Users", users);
	  }
	  
	  public static void s(Player p, String s){
		  p.sendMessage(WCMail.AS(WCMail.WC + s));
	  }
	  
	  public static void s2(Player p, String s){
		  p.sendMessage(WCMail.AS(s));
	  }

}