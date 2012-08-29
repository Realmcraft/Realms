package com.github.realmcraft.realms.towns;

import javax.persistence.EntityManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.realmcraft.realms.main.Realms;
//Future features notes
//Town population - size (hamlet, metropolis, etc.)

public class TownMain implements CommandExecutor {

//variable declarations
	private final Realms plugin;
	
	//initialize persistence
	protected EntityManager em;
	
	//main methods:
	public TownMain(final Realms plugin) {
		this.plugin = plugin;
	}
	
	//general handler, put here only for reference.
	//@EventHandler(priority = EventPriority.NORMAL)
	//public void onDeath(final EntityDeathEvent event) {
	// // do something useful
	//}
	
	//Command handler - sender = who sent the command, cmd = command that was executed, label = alias used, args = arguments after /town
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("town")) {
			if(sender instanceof Player)
			{		
				//a player has typed /town - we'll be doing stuff here!
				//check if no options were passed after the town command.
				if(args.length == 0)
				{
					sender.sendMessage("You entered /town, which is not a valid command.  Please see /town help for a list of valid commands.");
					return true;
				}
				else
				{
					//start by determining the sub-command issued - will be args[0], and call that function.
				}
			}
			else
			{
				sender.sendMessage("You must be a player to run /town commands!");
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("townadmin"))
		{
			//user(or console) has typed /townadmin - we'll be doing stuff here!
			//check if no options were passed after the townadmin command.
			if(args.length == 0)
			{
				sender.sendMessage("You entered /townadmin, which is not a valid command.  Please see /townadmin help for a list of valid commands.");
				return true;
			}
			else
			{
				//start by determining the sub-command issued - will be args[0], and call that function.
			}
		}
		//default return false
		return false;
	}

//command listeners from onCommand switch
	//process town spawn command - syntax /town spawn [townname] (aliased to /t spawn [name])
	private void townspawn(CommandSender sender, String[] args)
	{
		//local variables
		long townspawnWarpID;
		
		//check that command is properly formatted.
		if (args.length > 2)
		{
			//TODO use in house chat command
			sender.sendMessage("You have entered too many commands, usage is /town spawn [name].  Note that town name cannot have spaces (is there an underscore?).");
			//TODO use in house chat command
			sender.sendMessage("Using first part of command only.  Attempting to send you to" + args[1]);
			//sender.sendMessage("Aboring town spawn teleport.  Please try again!");
			//return;
		}
		//lookup town "name" in data, see if it exists - town name is args [1]
		//if name does not exist - tell player invalid town name and return (calls chat)
		if(townexists(args[1]))
		{
			//town does exist.  lookup coordinates of town spawn in town [name]
			//have to get long spawnWarp from town, then cross reference with warps table.
			
			
			//else lookup coordinates of town spawn in town name
			//check permissions of plot specified by coordinates of returned town spawn calls plot object to get perms
			//if permissions disallow outsiders from entering - tell player they cannot go there b/c perms and return
			//else if player doesn't have enough money - tell player they can't afford it and return
				//else charge the player money (ensure transaction success) and teleport them to those coordinates (ensure teleport sucess if possible - display "welcome" message) calls teleport functions//(Test case for /t spawn name)
		}
		else
		{
			//town does not exist.
			//TODO use in house chat command
			//TODO fix after removing case sensitivity in townexists
			sender.sendMessage("The town %s does not exist.  Note that town names are case sensitive.  Use /town list to see a listing of town names.");
			return;
		}
		
		//Test 1: valid town name (check welcome message)
		//Test 2: Invalid town name - should return status indicating invalid town name
		//Test 3: no permissions - add town for player X at coord X,Z, set perm to no access, /t spawn player Y - check return
		//Test 4: no money - use NPC X which has no money, /t spawn city for player X - check return
		return;
	}
	
	
//Supporting methods
	//Check if a given town name exists or not.  Case sensitive!!! (TODO: make case insensitive)
	//users: /town spawn, 
	public boolean townexists(String townname)
	{
		//find a town object with the specified name
//		if(em.find(TownObj.class, townname)) {
//			
//		}
		return false;
	}
}