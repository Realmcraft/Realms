package com.github.realmcraft.realms.resident;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


////////////////////////////////////////////////////////////////////////////////
/**
 * Represents a town.
 * @author Realmcraft Developer Team
 *
 */
@Entity
public class Resident implements Serializable {
	@Id
	private String name;
	@Basic
	private Date lastOnline;
	@Basic
	private Date firstOnline;
	@Basic
	private long onlineTime;
	  
} // end class Resident
////////////////////////////////////////////////////////////////////////////////