package com.maxt95.core.Professions;

import org.bukkit.entity.Player;



public class RPPlayer {
	Player player;
	String profession = "Citizen";
	
	public RPPlayer(Player player) {
		this.player = player;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getProfession() {
		return profession;
	}
	
	public void getBalance() {
		Main.economy.getBalance(player);
	}
	public String getName() {
		return player.getName();
	}
	
	public Player getPlayer() {
		return player;
	}
}
