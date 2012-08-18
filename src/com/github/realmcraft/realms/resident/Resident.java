package com.github.realmcraft.realms.resident;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import com.avaje.ebean.validation.*;
import com.github.realmcraft.realms.main.Realms;

import org.bukkit.*;

////////////////////////////////////////////////////////////////////////////////
/**
 * Represents a town.
 * @author Realmcraft Developer Team
 *
 */
@Entity
public class Resident {
	@Id
	private String name;
	@OneToMany(mappedBy="Resident")
	private LinkedHashSet<String> friends;
	private Date lastOnline;
	@NotNull
	private Date firstOnline;
	private long onlineTime;

	public Resident() {
	}
	
	public Resident(String name, Date lastOnline, Date firstOnline, long onlineTime) {
		this.name = name;
		this.lastOnline = lastOnline;
		this.firstOnline = firstOnline;
		this.onlineTime = onlineTime;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	

	public void setLastOnline(Date lastOnline) {
		this.lastOnline = lastOnline;
	}
	
	public Date getLastOnline() {
		return lastOnline;
	}
	
	
	public void setFirstOnline(Date firstOnline) {
		this.firstOnline = firstOnline;
	}
	
	public Date getFirstOnline() {
		return firstOnline;
	}
	

	public void setOnlineTime(long onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	public long getOnlineTime() {
		return onlineTime;
	}
	
	public String getOnlineTimeString() {
		long time = onlineTime/1000;
		return String.format("%d:%02d:%02d", time/3600, (time%3600)/60, (time%60));
	}
	
	public LinkedHashSet<String> getFriends() {
		return friends;
	}
	
	public String getFriendsString() {
		String friendsString = null;
		if(friends.size() > 0) {
			for(String friend : friends) {
				friendsString = friendsString + friend + ", ";
			}
			return friendsString.substring(0, friendsString.length() - 1);
		} else {
			return friendsString;
		}
	}
	
	public void setFriends(LinkedHashSet<String> friends) {
		this.friends = friends;
	}
	
	public boolean isFriendsWith(String name) {
		if(name == null || friends == null) {
			return false;
		}
		if(friends.contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	public void addFriend(String name) {
		if(friends == null) {
			friends = new LinkedHashSet<String>();
		}
		friends.add(name);
		return;
	}
	
	public void removeFriend(String name) {
		friends.remove(name);
	}
} // end class Resident
////////////////////////////////////////////////////////////////////////////////