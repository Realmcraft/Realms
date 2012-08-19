package com.github.realmcraft.realms.resident;

import javax.persistence.*;

@Entity
public class Friend {
	@Id
	int id;
	@ManyToOne
	Resident resident;
	@Basic
	String friend;
	public Friend() {
		
	}
	public Friend(Resident resident,String friend){
		this.resident = resident;
		this.friend = friend;
	}
	public String getFriendName() {
		return this.friend;
	}
}
