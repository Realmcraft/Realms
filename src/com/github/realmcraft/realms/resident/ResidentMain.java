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

public class ResidentMain extends Realms implements CommandExecutor {

	public ResidentMain(EbeanServer database) {
		this.database = database;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("resident")) {
			if(sender instanceof Player) {		
				if(args.length == 0) {
					sender.sendMessage("You entered /resident, which is not a valid command.  Please see /resident help for a list of valid commands.");
					return true;
				} else {
					if(args[0].equalsIgnoreCase("info")) {
						return info(sender, args);
					} else if(args[0].equalsIgnoreCase("friend")) {
						if(args.length > 1) {
							if(args.length == 3) { //Has specified /resident friend [comm] [name]

						        
								if(args[1].equalsIgnoreCase("add")) {
									return addFriend(sender, args);
								} else if(args[1].equalsIgnoreCase("remove")) {
									return removeFriend(sender, args);
								} else {
									sender.sendMessage(ChatColor.RED + "Invalid subcommand; you may only add or remove friends.");
									return true;
								}
							} else { //Has specified /resident friend [comm]
								sender.sendMessage(ChatColor.RED + "Please specify a player.");
								return true;								
							}
						} else {
							return listFriends(sender, args);
						}
					}
				}
			} else {
				sender.sendMessage("You must be a player to run /resident commands!");
				return true;
			}
		}
		else if(cmd.getName().equalsIgnoreCase("residentadmin")) {
			if(args.length == 0) {
				sender.sendMessage("You entered /residentadmin, which is not a valid command.  Please see /residentadmin help for a list of valid commands.");
				return true;
			} else {
				//start by determining the sub-command issued - will be args[0], and call that function.
			}
		}
		//default return false
		return false;
	}
	
	
	public boolean info(CommandSender sender, String[] args)
	{
		String name;
        if (args.length > 1) { //Player specified a name
            name = args[1];
        } else {
            name = sender.getName();
        }
        
        Resident resident = database.find(Resident.class).where().ieq("name", name).findUnique();

        if (resident == null) {
        	sender.sendMessage(ChatColor.RED + "That player does not exist.");
        	return true;
        }
        
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
        listFriends(sender, args);
        
       
        
        return true;
	}

	public boolean addFriend(CommandSender sender, String[] args) {
		if(args[2] == null) {
			sender.sendMessage(ChatColor.RED + "Please specify a player.");
			return true;
		}
		Resident friend = database.find(Resident.class).where().ieq("name", args[2]).findUnique();
        if (friend == null) {
        	sender.sendMessage(ChatColor.RED + "Player " + args[2] + " does not exist.");
        	return true;
        }
        if (friend.getName().equals(sender.getName())) {
        	sender.sendMessage(ChatColor.RED + "You cannot add yourself to your friends list.");
        	return true;
        }
        
        Resident resident = database.find(Resident.class).where().ieq("name", sender.getName()).findUnique();
        if(resident.isFriendsWith(args[2])) {
        	sender.sendMessage(ChatColor.RED + "You're already friends with " + args[2] + ".");
        	return true;
        }
        sender.sendMessage(ChatColor.GREEN + "Added " + args[2] + " to your friends list.");
        
        resident.addFriend(args[2]);
        database.save(resident);
		return true;
	}
	
	public boolean removeFriend(CommandSender sender, String[] args) {
		if(args[1] == null) {
			sender.sendMessage(ChatColor.RED + "Please specify a player.");
			return true;
		}
		Resident friend = database.find(Resident.class).where().ieq("name", args[2]).findUnique();
        if (friend == null) {
        	sender.sendMessage(ChatColor.RED + "Player " + args[2] + " does not exist.");
        	return true;
        }
        if (friend.getName().equals(sender.getName())) {
        	sender.sendMessage(ChatColor.RED + "You cannot remove yourself to your friends list.");
        	return true;
        }        
        Resident resident = database.find(Resident.class).where().ieq("name", sender.getName()).findUnique();
        if(!resident.isFriendsWith(args[2])) {
        	sender.sendMessage(ChatColor.RED + "You cannot remove someone who isn't a friend.");
        	return true;
        }
        	
        resident.removeFriend(friend.getName());
        database.save(resident);
        sender.sendMessage(ChatColor.GREEN + "Removed " + friend.getName() + " from your friends list.");
		return true;
	}
	
	public boolean listFriends(CommandSender sender, String[] args) {
		Resident resident = database.find(Resident.class).where().ieq("name", sender.getName()).findUnique();
		
		if(resident.getFriends() == null) {
        	sender.sendMessage(ChatColor.GOLD + "Friends [0]: " + ChatColor.YELLOW + "None yet.");
        } else {
        	sender.sendMessage(ChatColor.GOLD + "Friends [" + (resident.getFriends()).size() + "]: " + ChatColor.YELLOW + resident.getFriendsString());
        }
		
		return true;
	}
	
	
	public static void setConnectedPlayersLastOnlineAsNow() {
		return;
	}
}