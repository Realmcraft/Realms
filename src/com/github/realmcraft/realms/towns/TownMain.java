package com.github.realmcraft.realms.towns;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.github.realmcraft.realms.main.Realms;
//Future features notes
//Town population - size (hamlet, metropolis, etc.)

public class TownMain implements Listener {

//variable declarations
	private Realms plugin;
	
	
	//main methods:
	public TownMain(Realms instance) {
		plugin = instance;
	}
	
	//Event Priority: normal is fine because it is a command that will not be cancelled.
	@EventHandler
	public void onCommand(){;}

// event listeners (commands issued)
   //listen for /t spawn [name]
      //lookup town "name" in data      
      //if name does not exist - tell player invalid town name and return (calls chat)
      //else lookup coordinates of town spawn in town name
           //check permissions of plot specified by coordinates of returned town spawn calls plot object to get perms
           //if permissions disallow outsiders from entering - tell player they cannot go there b/c perms and return
           //else if player doesn't have enough money - tell player they can't afford it and return
                 //else charge the player money (ensure transaction success) and teleport them to those coordinates (ensure teleport sucess if possible - display "welcome" message) calls teleport functions
     //(Test case for /t spawn name)
     //Test 1: valid town name (check welcome message)
     //Test 2: Invalid town name - should return status indicating invalid town name
     //Test 3: no permissions - add town for player X at coord X,Z, set perm to no access, /t spawn player Y - check return
     //Test 4: no money - use NPC X which has no money, /t spawn city for player X - check return

//Method to check if a given town exists or not

}