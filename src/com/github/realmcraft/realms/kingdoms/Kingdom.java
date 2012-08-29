package com.github.realmcraft.realms.kingdoms;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
////////////////////////////////////////////////////////////////////////////////
/**
 * Represents a kingdom.
 * @author Realmcraft Developer Team
 *
 */
@Entity
public class Kingdom implements Serializable {
	  @Id
	  private String name;
} // end class Kingdom
////////////////////////////////////////////////////////////////////////////////