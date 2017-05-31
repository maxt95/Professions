package Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.maxt95.core.Professions.RPPlayer;

public class PlayerListener implements Listener {
	ArrayList<Block> instance = new ArrayList<Block>();
	JavaPlugin plugin;
	Location tmpLoc;
	public PlayerListener(JavaPlugin plugin){
		this.plugin = plugin;
	}
	RPPlayer rpPlayer;
	ArrayList<RPPlayer> onlinePlayers = new ArrayList<RPPlayer>();
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		rpPlayer = new RPPlayer(e.getPlayer());
		onlinePlayers.add(rpPlayer);
		rpPlayer.setProfession("citizen");
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		
		
	}
	
	public ArrayList<Block> getBlocks(Block start, int radius){
	     ArrayList<Block> blocks = new ArrayList<Block>();
	     for(double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++){
	       for(double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++){
	         for(double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++){
	           Location loc = new Location(start.getWorld(), x, y, z);
	           blocks.add(loc.getBlock());
	      }
	      }
	     }
	     return blocks;
	     }
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		for(int i = 0; i < onlinePlayers.size(); i++)
		{
			onlinePlayers.remove(rpPlayer);
		}
	}
	
	public RPPlayer getPlayer() {
		return rpPlayer;
	}
	public void setSomething() {
		
	}
	public ArrayList<RPPlayer> getOnlinePlayers() {
		return onlinePlayers;
	}
}
