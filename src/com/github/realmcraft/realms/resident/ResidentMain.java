package com.github.realmcraft.realms.resident;

import java.util.Date;

import javax.persistence.EntityManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.avaje.ebean.*;
import com.github.realmcraft.realms.main.Realms;
//Future features notes
//Town population - size (hamlet, metropolis, etc.)

public class ResidentMain implements CommandExecutor {

//variable declarations
	private final Realms plugin;
	
	//initialize persistence
	protected EntityManager em;
	
	//main methods:
	public ResidentMain(Realms plugin) {
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
		if(cmd.getName().equalsIgnoreCase("resident")) {
			if(sender instanceof Player)
			{		
				//a player has typed /town - we'll be doing stuff here!
				//check if no options were passed after the town command.
				if(args.length == 0)
				{
					sender.sendMessage("You entered /resident, which is not a valid command.  Please see /resident help for a list of valid commands.");
					return true;
				}
				else
				{
					if(args[0].equalsIgnoreCase("info")) {
						return info(sender, args);
					}
				}
			}
			else
			{
				sender.sendMessage("You must be a player to run /resident commands!");
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("residentadmin"))
		{
			//user(or console) has typed /townadmin - we'll be doing stuff here!
			//check if no options were passed after the townadmin command.
			if(args.length == 0)
			{
				sender.sendMessage("You entered /residentadmin, which is not a valid command.  Please see /residentadmin help for a list of valid commands.");
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
	//process town spawn command - syntax /resident info [name]
	private boolean info(CommandSender sender, String[] args)
	{
		String name;
        if (args.length > 1) { //Player specified a name
            name = args[1];
        } else {
            name = sender.getName();
        }
        
        Resident resident = plugin.getDatabase().find(Resident.class).where().ieq("name", name).findUnique();
        if (resident == null) {
        	sender.sendMessage(ChatColor.RED + "That player does not exist.");
        	return true;
        }
        
        plugin.getDatabase().save(resident);

        boolean onlineNow = false;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equals(resident.getName())) {
            	onlineNow = true;
            	break;
            }
        }
        
        sender.sendMessage(ChatColor.LIGHT_PURPLE + "+---------------------------------------------------+");
        sender.sendMessage(ChatColor.GOLD + "Name: " + ChatColor.YELLOW + resident.getName());
        if(onlineNow) {
        	sender.sendMessage(ChatColor.GOLD + "Last Online: " + ChatColor.YELLOW + "Online now!");
        } else {
        	sender.sendMessage(ChatColor.GOLD + "Last Online: " + ChatColor.YELLOW + resident.getLastOnline().toString());
        }
        sender.sendMessage(ChatColor.GOLD + "First Online: " + ChatColor.YELLOW + resident.getFirstOnline().toString());
        sender.sendMessage(ChatColor.GOLD + "Online Time: " + ChatColor.YELLOW + resident.getOnlineTimeString());
        if(resident.getFriends() == null) {
        	sender.sendMessage(ChatColor.GOLD + "Friends [0]: " + ChatColor.YELLOW + "None yet.");
        } else {
        	sender.sendMessage(ChatColor.GOLD + "Friends [" + (resident.getFriends()).size() + "]: " + ChatColor.YELLOW + resident.getFriendsString());
        }
        
       
        
        return true;
	}
	
	
//Supporting methods

}