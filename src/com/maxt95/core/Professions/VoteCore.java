package com.maxt95.core.Professions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class VoteCore {
	
	static boolean isTermExpired = false;
	static boolean currentMayor = false;
	JavaPlugin plugin;
	public int termTime = 6000; // ie 5 minutes
	public int voteTime = 600;
	static ArrayList<String[]> votes = new ArrayList<String[]>();
	static ArrayList<String> votedPlayers = new ArrayList<String>();
	public VoteCore(JavaPlugin plugin) {
		this.plugin = plugin;
		if(checkForMayor()) { // if there is a mayor
			if(getStatus()){  	// if mayor term is up
				voteForNewMayor();
			}
			else {
				
			}
		}
		else 					// if there is no mayor
			voteTimer();
	}
	
	public boolean checkForMayor() {
		Iterator<? extends Player> it = Bukkit.getOnlinePlayers().iterator();
		
		while(it.hasNext()) {
			Player p = (Player) it.next();
			String[] groups = Main.permission.getPlayerGroups(p);
			for(int i = 0; i < groups.length; i++) {
				if(groups[i].equals("mayor")) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean voteForMayor(String votingPlayer, String player) {
		for(int i = 0; i < votedPlayers.size(); i++) {
			if(votingPlayer.equals(votedPlayers.get(i))) { // if the player already voted
				
				return false;
			}
			
		}
		
		if(votes.size() == 0) { // if empty we add directly
			String[] tally = {player, "1"};
			votes.add(tally);
			votedPlayers.add(votingPlayer);
			
		}
		else {
			
			for(int i = 0; i < votes.size(); i++) {
				if(player.equals(votes.get(i)[0])) { 			// if name already in record we add 1 to it
					int amt = Integer.parseInt(votes.get(i)[1]);
					amt++;
					String s = Integer.toString(amt);
					String[] tally = {player, s};
					votes.set(i, tally);
					votedPlayers.add(votingPlayer);
				}
			}
		}
		if(votes.size() > 1) {
			votes.sort((o1, o2) -> o1[1].compareTo(o2[1]));			// then sort
		}
		
		return true;
	}
	
	public static boolean getStatus() {
		if(isTermExpired) {
			return true;
		}
		else
			return false;
	}
	
	public static boolean isMayor() {
		if(currentMayor) {
			return true;
		}
		else 
			return false;
	}
	
	public static ArrayList<String[]> getVoteStats() {
		
		return votes; 
	}
	
	public void voteForNewMayor() {
		
	}
	public void getWinner() {
		if(votes.size() > 0) {
			String playerName = votes.get(0)[0];
			Iterator<? extends Player> it = Bukkit.getOnlinePlayers().iterator();
			Player player = null;
			while(it.hasNext()){
				if(it.next().getName().equals(playerName)){
					player = it.next();
				}
			}
			if(player != null) {
				String[] groups = Main.permission.getPlayerGroups(player);
				for(int i = 0; i < groups.length; i++) {
					String s = groups[i];
					if(s.equals("blackmarketdealer") || s.equals("citizen") || s.equals("drugdealer") || s.equals("guard") || s.equals("gundealer") || s.equals("hacker") || s.equals("hobo") || s.equals("mobster")
							|| s.equals("police") || s.equals("thief") || s.equals("mobboss") || s.equals("hitman") || s.equals("prostitute")) {
						Main.permission.playerRemoveGroup(player, s);
						Main.permission.playerAddGroup(player, "mayor");
					}
				}
				Bukkit.broadcastMessage(votes.get(0)[0] + " has been elected mayor!");
				termTimer();
			}
			else{
				Bukkit.broadcastMessage(votes.get(0)[0] + " is not online and another election will take place!");
			}
		}
		else {
			Bukkit.broadcastMessage("No one voted! Starting another vote soon.");
			voteTimer();
		}
		
		
	}
	
	public void termTimer() { 			// term timer for mayor
		int id;
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("Term Expired");
				isTermExpired = true;
				currentMayor = true;
				
				
			}
		}.runTaskLaterAsynchronously(plugin, termTime);
	}
	
	public void cooldownTimer() {	// cooldown before a new mayor can be elected and old one impeached
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("Cooldown is up");
				
				
				
			}
		}.runTaskLaterAsynchronously(plugin, 1200);
	}
	
	public void voteTimer() {			// timer to broadcast that a mayor can be elected
		Bukkit.broadcastMessage("Voting has begun!!!!");
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("Voting has ended!");
				
				isTermExpired = true;
				currentMayor = true;
				
				getWinner();
				
				
				
			}
		}.runTaskLaterAsynchronously(plugin, voteTime);
		
	}
}
