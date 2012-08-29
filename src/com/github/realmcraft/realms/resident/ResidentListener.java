package com.github.realmcraft.realms.resident;

import java.util.Date;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.realmcraft.realms.main.Realms;

public class ResidentListener extends Realms implements Listener {

	
	public ResidentListener() {

	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Resident resident = em.find(Resident.class, event.getPlayer().getName());
        if (resident == null) {
        	em.getTransaction().begin();
        	resident = new Resident(event.getPlayer().getName(), new Date(), new Date(), 0);
            em.persist(resident);
            em.getTransaction().commit();
        }
        return;
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Resident resident = em.find(Resident.class, event.getPlayer().getName());
    	em.getTransaction().begin();
        if (resident != null) {
        	Date newLastOnline = new Date();
        	resident.setOnlineTime(resident.getOnlineTime() - resident.getLastOnline().getTime() + newLastOnline.getTime());
        	resident.setLastOnline(newLastOnline);
        }
        em.persist(resident);
        em.getTransaction().commit();
        return;
	}
}
