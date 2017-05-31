package Listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CuffListener implements Listener {

	JavaPlugin plugin;
	public CuffListener(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	//This hasmap stores important information and lets me check if they are prisoners or guards, instead of regular players. it requires 2 strings; one for each player   
	private HashMap<String, String> prisoner = new HashMap<String, String>();
     
	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEntityEvent e)
	{
		System.out.println("test1");
		final Player p = e.getPlayer();
		Entity cuffed = e.getRightClicked();
		//First we must check for the prisoner and the guard. I use a rightclick player event here, to initate the link
		
		if ( (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) && (cuffed instanceof Player)){
			if(e.getHand().equals(EquipmentSlot.HAND)) {
				Player y1 = (Player) cuffed;
				y1.sendMessage("You have been leashed!");
	
				Entity pig = p.getWorld().spawnEntity(y1.getLocation(), EntityType.PIG);
				Pig glue = (Pig) pig;
				//the pig is set to a baby to minimise its size and make it more naturaly looking when invisibile.
				glue.setBaby();
				
				glue.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 180000000, 3));
				//This sets the actual lead to the pig; as leads cant be set to players
				glue.setLeashHolder(p);
				//Makes the pig custom, and allows for us to clear it later on
				glue.setMetadata(y1.getName(), new FixedMetadataValue(plugin, true));
				glue.setCollidable(false);
				
				// clearing the hashmap so it dosent get messy!
				prisoner.clear();
				//Adding both players to the hashmap; we dont want regular players teleporting around :P
				prisoner.put(p.getName()+"*", cuffed.getName()+"p*");
				//A runnable to teleport the pig constantly to the player. This allows for the 'leash' effect
				new BukkitRunnable() {
					
					@Override
					public void run() {
						if(glue.isDead())
						{
							System.out.println("dead");
							this.cancel();
						}
						else
						{
							if(prisoner.containsKey(p.getName()+"*")){
								System.out.println("teleport");
								y1.teleport(glue.getLocation());
								//glue.teleport(y1.getLocation());
							
							}
							
	                    }
						
					}
					
				}.runTaskTimerAsynchronously(plugin, 0, 20);
			}
				
				
	    }
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if(prisoner.values().contains(p.getName()+"p*")) {
			prisoner.remove(p.getName()+"p*");
		}
	}

 

	//So the prisoner cant escape and break the lead, but can still move
	
	@EventHandler
	public void onPrisoner(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (prisoner.values().contains(p.getName()+"p*")){
			p.setWalkSpeed((float) 0.01);
			p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, -8));
	
		}
	}

	//Teleports the prisoner to the guard when they is holding the stick and is moving over blocks (not just their head or something)
	
	
	/*@EventHandler
	public void onPrisonguard(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if (prisoner.containsKey(p.getName()+"*")){
			String s = prisoner.values()+"";
			String t = s.replace("p*", "").replace("[", "").replace("]", "");
			//Changing a string to a player
			Player pr = Bukkit.getServer().getPlayer(t);
			if(pr.isOnline()){
				if(p.getInventory().getItemInMainHand().getType().equals(Material.BLAZE_ROD))
					if(e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) {
						Vector direction = p.getLocation().toVector().subtract(pr.getLocation().toVector()).normalize();
						pr.setVelocity(direction);   
					}
			}
	
		}
	}*/


	//A simple method to remove the prisoner

	@EventHandler(priority=EventPriority.NORMAL)
	public void onPlayerInteract2(PlayerInteractEvent e)
	{
		final Player p = e.getPlayer();
	
		if  (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD && (e.getAction() == Action.LEFT_CLICK_AIR)){
			if (prisoner.containsKey(p.getName()+"*")){
				String s = prisoner.values()+"";
				String t = s.replace("p*", "").replace("[", "").replace("]", "");
				Player pr = Bukkit.getServer().getPlayer(t);
				prisoner.remove(p.getName()+"*");
				pr.sendMessage("You are no longer a prisoner!");
				p.sendMessage("You have rid yourself of a prisoner!");
				//Remember adding the metadata? this lets you remove that pig, without killing other pigs!. Since the pig will be close due to the constant tp,
				// it is a simple method to kill the nearest pigs with metadata
				// it also wont kill others pigs!
				for (Entity entity : pr.getNearbyEntities(10.0D, 10.0D, 10.0D)) {
					if (entity instanceof Pig) {
						System.out.println(entity.getMetadata(pr.getName()));
						if(entity.hasMetadata(pr.getName())){
							
							pr.setWalkSpeed((float) 0.2);
							pr.removePotionEffect(PotionEffectType.JUMP);
							((Pig) entity).setLeashHolder(null);
							((Pig) entity).remove();
						}
					}
				}
			}
		}
	}


}
