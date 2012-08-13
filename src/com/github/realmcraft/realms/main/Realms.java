package com.github.realmcraft.realms.main;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.realmcraft.realms.towns.TownMain;

/**
 * This is the main Realms class, which extends the bukkit plugin.  All other classes should extend this.
 * 
 * @author Sparky007 & ryan_turner
 */
public abstract class Realms extends JavaPlugin {
	
	//variable declaration
	
	//main methods
	//plugin enabled in bukkit
	public void onEnable() {
		getLogger().info("Realms has been enabled.");
		PluginManager pm = this.getServer().getPluginManager();
		//register each package/section of the plugin
		//announcements
		
		//arena
		
		//capturetheflag
		
		//chat
		
		//data
		
		//economy
		
		//guilds
		
		//kingdoms
		
		//kits
		
		//mail
		
		//misc
		
		//plots
		
		//protection
		
		//qapilot
		
		//ranks
		
		//resident
		
		//spleef
		
		//staff
		
		//teleport
		
		//test
		
		//towns
		pm.registerEvents(new TownMain(this), this);
		
		//vote
		
		//warps
		
		//wars
		
	}
	
	//plugin disabled in bukkit
	public void onDisable() {
		getLogger().info("Realms has been disabled.");
	}
	
	
	
}