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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.lyokofirelyte.WaterCloset.Alliances.WACommandEx;
import com.github.lyokofirelyte.WaterCloset.Extras.StaticField;
import com.github.lyokofirelyte.WaterCloset.Extras.TNTNerf;
import com.github.lyokofirelyte.WaterCloset.Extras.TimeStampEX;
import com.github.lyokofirelyte.WaterCloset.Extras.waOSReport;

public class WCMain extends JavaPlugin
{
  public WCVault vaultMgr = new WCVault(this);
  File WASpleefconfigFile;
  File WASpleefdatacoreFile;
  File configFile;
  File datacoreFile;
  File WAAlliancesconfigFile;
  File WAAlliancesdatacoreFile;
  public FileConfiguration WASpleefconfig;
  public FileConfiguration WASpleefdatacore;
  public FileConfiguration config;
  public FileConfiguration datacore;
  public FileConfiguration WAAlliancesconfig;
  public FileConfiguration WAAlliancesdatacore;
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

    this.WASpleefconfigFile = new File(getDataFolder() + File.separator + "WASpleef", "config.yml");
    this.WASpleefdatacoreFile = new File(getDataFolder() + File.separator + "WASpleef", "datacore.yml");

    this.WAAlliancesconfigFile = new File(getDataFolder() + File.separator + "WAAlliances", "config.yml");
    this.WAAlliancesdatacoreFile = new File(getDataFolder() + File.separator + "WAAlliances", "datacore.yml");

    this.configFile = new File(getDataFolder(), "config.yml");
    this.datacoreFile =  new File(getDataFolder(), "datacore.yml");

    this.vaultMgr.hookSetup();
    try
    {
      firstRun();
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.WAAlliancesconfig = new YamlConfiguration();
    this.WAAlliancesdatacore = new YamlConfiguration();
    this.WASpleefconfig = new YamlConfiguration();
    this.WASpleefdatacore = new YamlConfiguration();
    this.config = new YamlConfiguration();
    this.datacore = new YamlConfiguration();
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
  }

  private void copy(InputStream in, File file)
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
      e.printStackTrace();
    }
  }

  public void saveYamls()
  {
    try
    {
      this.config.save(this.configFile);
      this.datacore.save(this.datacoreFile);

      this.WASpleefconfig.save(this.WASpleefconfigFile);
      this.WASpleefdatacore.save(this.WASpleefdatacoreFile);

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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveWASpleef()
  {
    try {
      this.WASpleefconfig.save(this.WASpleefconfigFile);
      this.WASpleefdatacore.save(this.WASpleefdatacoreFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadWASpleef()
  {
    try
    {
      this.WASpleefconfig.load(this.WASpleefconfigFile);
      this.WASpleefdatacore.load(this.WASpleefdatacoreFile);
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

      this.WASpleefconfig.load(this.WASpleefconfigFile);
      this.WASpleefdatacore.load(this.WASpleefdatacoreFile);

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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void firstRun() throws Exception {
    if (!this.configFile.exists()) {
      this.configFile.getParentFile().mkdirs();
      this.WASpleefconfigFile.getParentFile().mkdirs();
      this.WAAlliancesconfigFile.getParentFile().mkdirs();
      copy(getResource("config.yml"), this.configFile);
      copy(getResource("WASpleefconfig.yml"), this.WASpleefconfigFile);
      copy(getResource("WAAlliancesconfig.yml"), this.WAAlliancesconfigFile);
    }
    if (!this.datacoreFile.exists()) {
      this.datacoreFile.getParentFile().mkdirs();
      this.WASpleefdatacoreFile.getParentFile().mkdirs();
      this.WAAlliancesdatacoreFile.getParentFile().mkdirs();
      copy(getResource("datacore.yml"), this.datacoreFile);
      copy(getResource("WASpleefdatacore.yml"), this.WASpleefdatacoreFile);
      copy(getResource("WAAlliancesdatacore.yml"), this.WAAlliancesdatacoreFile);
    }
  }
}