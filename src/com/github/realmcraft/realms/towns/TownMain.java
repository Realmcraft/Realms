package com.github.realmcraft.realms.towns;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.realmcraft.realms.main.Realms;
//Future features notes
//Town population - size (hamlet, metropolis, etc.)

public class TownMain implements CommandExecutor {

//variable declarations
	private Realms plugin;
	
	//main methods:
	public TownMain(Realms plugin) {
		this.plugin = plugin;
	}
	
	//Command handler - sender = who sent the command, cmd = command that was executed, label = alias used, args = arguments after /town
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("town"))
		{
			if(sender instanceof Player)
			{		
				//a player has typed /town - we'll be doing stuff here!
				//start by determining the sub-command issued - will be args[0]
				townspawn(sender, args);
			}
			else
			{
				sender.sendMessage("You must be a player to run /town commands!");
				return false;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("townadmin"))
		{
			//user(or console) has typed /townadmin - we'll be doing stuff here!
			//start by determining the sub-command issued - will be args[0]
			testingonly(sender, args);
		}
		//default return false
		return false;
	}

//command listeners from onCommand switch
	//process town spawn command - syntax /town spawn [townname]
	public void townspawn(CommandSender sender, String[] args)
	{
		System.out.println("You entered a town spawn command!");
	}
   ///t spawn [name]
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
	
	
	//testing only!
	public void testingonly(CommandSender sender, String[] args)
	{
		System.out.println("You entered a townadmin command!");
	}
}