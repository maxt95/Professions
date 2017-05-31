package com.maxt95.core.Professions;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import Commands.CommandAcceptJob;
import Commands.CommandBlackMarket;
import Commands.CommandCitizen;
import Commands.CommandCreateJob;
import Commands.CommandCurrent;
import Commands.CommandDrugDealer;

import Commands.CommandGuard;
import Commands.CommandGunDealer;
import Commands.CommandHacker;
import Commands.CommandHobo;
import Commands.CommandJobs;
import Commands.CommandMobster;
import Commands.CommandPolice;
import Commands.CommandProfessions;
import Commands.CommandThief;
import Commands.CommandVote;
import Commands.CommandWarrant;
import Listeners.CuffListener;
import Listeners.Pickpocket;
import Listeners.PlayerListener;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;



public class Main extends JavaPlugin {
	PlayerListener playerListener;
	EconomyPayout ec;
	ThiefTimer thiefTimer;
	public static Economy economy = null;
	public static Permission permission = null;
	public int payTime = 1200; //change to 12000 later
	CommandThief thief;
	
	public void onEnable() {
		setupEconomy();
		setupPermissions();
		
		getServer().getPluginManager().registerEvents(playerListener = new PlayerListener(this), this);
		getServer().getPluginManager().registerEvents(new CuffListener(this), this);
		

		
		this.getCommand("hobo").setExecutor(new CommandHobo(playerListener.getOnlinePlayers()));
		this.getCommand("drugdealer").setExecutor(new CommandDrugDealer(playerListener.getOnlinePlayers()));
		
		this.getCommand("thief").setExecutor(thief = new CommandThief(playerListener.getOnlinePlayers(), this));
		this.getCommand("police").setExecutor(new CommandPolice(playerListener.getOnlinePlayers()));
		this.getCommand("vote").setExecutor(new CommandVote(playerListener.getOnlinePlayers()));
		this.getCommand("professions").setExecutor(new CommandProfessions());
		this.getCommand("current").setExecutor(new CommandCurrent(playerListener.getOnlinePlayers()));
		this.getCommand("citizen").setExecutor(new CommandCitizen(playerListener.getOnlinePlayers()));
		this.getCommand("mobster").setExecutor(new CommandMobster(playerListener.getOnlinePlayers()));
		this.getCommand("blackmarketdealer").setExecutor(new CommandBlackMarket(playerListener.getOnlinePlayers()));
		this.getCommand("gundealer").setExecutor(new CommandGunDealer(playerListener.getOnlinePlayers()));
		this.getCommand("warrant").setExecutor(new CommandWarrant(playerListener.getOnlinePlayers()));
		this.getCommand("jobs").setExecutor(new CommandJobs());
		this.getCommand("createjob").setExecutor(new CommandCreateJob());
		this.getCommand("acceptjob").setExecutor(new CommandAcceptJob());
		this.getCommand("guard").setExecutor(new CommandGuard(playerListener.getOnlinePlayers()));
		this.getCommand("hacker").setExecutor(new CommandHacker(playerListener.getOnlinePlayers()));
	
		getServer().getPluginManager().registerEvents(new Pickpocket(thief), this);
		ec = new EconomyPayout();
		payTimer();
		startVote(this);
		
	}
	
	public void onDisable() {
		
	}
	private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }
	
	public void payTimer() {
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.broadcastMessage("Payouts have gone out");
				ec.dailyPay(playerListener.getOnlinePlayers());
			}
		}.runTaskTimer(this, 0, payTime);
		
	}
	
	public void startVote(JavaPlugin plugin) {
		new BukkitRunnable() {
			@Override
			public void run() { // check for online players
				Iterator it = Bukkit.getOnlinePlayers().iterator();
				if(it.hasNext()) {
					VoteCore vc = new VoteCore(plugin);
					this.cancel();
				}
			}
		}.runTaskTimerAsynchronously(this, 0, 100);
		
	
		
	}
}
