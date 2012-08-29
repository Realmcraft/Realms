package com.github.realmcraft.realms.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.realmcraft.realms.resident.Resident;
import com.github.realmcraft.realms.resident.ResidentListener;
import com.github.realmcraft.realms.resident.ResidentMain;

import com.github.realmcraft.realms.towns.TownMain;

/**
 * This is the main Realms class, which extends the bukkit plugin.  All other classes should extend this.
 * 
 * @author Sparky007 & ryan_turner
 */
public class Realms extends JavaPlugin {
	
	//variable declaration
	private static final String PERSISTENCE_UNIT_NAME = "Realms";
	private static EntityManagerFactory factory;
	protected EntityManager em;

	public void onEnable() {
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    this.em = factory.createEntityManager();
		
		PluginDescriptionFile pdfFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		
		FileConfiguration config = getConfig();
		config.options().copyDefaults(true);

		//load data from config
		//...
		//save config
		saveConfig();
		
		//register handlers for each package/section of the plugin
		//default handler put here for reference:
		//testListener = new TestListener(this);
		//PluginManager pm = getServer().getPlluginManager();
		//pm.registerEvents(testListener, this);
		getServer().getPluginManager().registerEvents(new ResidentListener(), this);
		
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
		getCommand("resident").setExecutor(new ResidentMain());
		getCommand("residentadmin").setExecutor(new ResidentMain());
		//spleef
		
		//staff
		
		//teleport
		
		//test
		
		//towns
//		townCommandRunner = new TownMain(this);
//		getCommand("town").setExecutor(townCommandRunner);
//		getCommand("townadmin").setExecutor(townCommandRunner);
		
		//vote
		
		//warps
		
		//wars
	}
	
	//plugin disabled in bukkit
	public void onDisable() {
		getLogger().info("Realms has been disabled.");
	}
	
}