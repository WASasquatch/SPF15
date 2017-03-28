package com.was.spf15;

import java.util.List;

// Import Bukkit Utils
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;

// Event Handlers & Listener
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

// Event
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;

// Entities
import org.bukkit.entity.Zombie;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Skeleton;

public class SunBlock implements Listener {
	
	private FileConfiguration config;
	public List<String> worlds;
	public static Server server;
	
	public SunBlock() {
		config = SPF15.config;
		worlds = config.getStringList("worlds");
		server = Bukkit.getServer();
	}
	
	@EventHandler(priority = EventPriority.HIGH)
    public void onEntityCombust(EntityCombustEvent event) {
		// Return if not valid world
	    String world = event.getEntity().getWorld().getName();
	    boolean validWorld = false;
	    for(String str: worlds) {
	    	if(str.trim().contains(world)) {
	    		validWorld = true;
	    	}
	    }
	    if ( ! validWorld ) {
	    	return;
	    }
		// Return if interactive event
	    if (event instanceof EntityCombustByBlockEvent
	            || event instanceof EntityCombustByEntityEvent) {
	        return;
	    }
		if (config.getBoolean("sunproof-zombies") ) {
			if(event.getEntity() instanceof Zombie) {
				event.setCancelled(true);
			}
		}
		if (config.getBoolean("sunproof-skeletons") ) {
			if(event.getEntity() instanceof Skeleton) {
				event.setCancelled(true);
			}
		}
		if (config.getBoolean("sunproof-pigmen") ) {
			if(event.getEntity() instanceof PigZombie) {
				event.setCancelled(true);
			}
		}
	}

}
