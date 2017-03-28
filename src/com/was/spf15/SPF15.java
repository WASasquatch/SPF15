package com.was.spf15;

// Import Java Utils
import java.io.File;

// Import Bukkit API & Utils
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class SPF15 extends JavaPlugin {
	
	public static SPF15 plugin;
	public static FileConfiguration config;
	
	public SPF15() {
    	createConfig();
    	config = getConfig();
	}
	
    @Override
    public void onEnable() {
    	plugin = this;
    	getServer().getPluginManager().registerEvents(new SunBlock(), plugin);
    }
    
    @Override
    public void onDisable() {
    	//...
    }
    
    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating it for you!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}