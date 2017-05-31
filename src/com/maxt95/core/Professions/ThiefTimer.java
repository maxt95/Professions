package com.maxt95.core.Professions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ThiefTimer {
	JavaPlugin plugin;
	private boolean canPickpocket = true;
	public ThiefTimer(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void timer() {
		canPickpocket = false;
		new BukkitRunnable() {
			@Override
			public void run() {
				
				canPickpocket = true;
				this.cancel();
			}
		}.runTaskTimerAsynchronously(plugin, 0, 400);
	}
	public boolean pickpocket(){
		return canPickpocket;
	}
}
