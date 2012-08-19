package com.github.realmcraft.realms.resident;

import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.avaje.ebean.EbeanServer;
import com.github.realmcraft.realms.main.Realms;

public class ResidentListener extends Realms implements Listener {

	public ResidentListener(EbeanServer database) {
		this.database = database;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Resident resident = database.find(Resident.class).where().ieq("name", event.getPlayer().getName()).findUnique();
        if (resident == null) {
        	resident = new Resident(event.getPlayer().getName(), new Date(), new Date(), 0);
        	database.save(resident);
        }
        return;
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Resident resident = database.find(Resident.class).where().ieq("name", event.getPlayer().getName()).findUnique();
        if (resident != null) {
        	Date newLastOnline = new Date();
        	resident.setOnlineTime(resident.getOnlineTime() - resident.getLastOnline().getTime() + newLastOnline.getTime());
        	resident.setLastOnline(newLastOnline);
        }
        database.save(resident);
        return;
	}
}
