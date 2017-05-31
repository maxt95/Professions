package Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.ThiefTimer;

import Commands.CommandThief;

import java.util.ArrayList;
import java.util.Random;
public class Pickpocket implements Listener{
	ArrayList<Object> thiefCooldown = new ArrayList<Object>();
	CommandThief thief;
	ThiefTimer thiefTimer;
	public Pickpocket(CommandThief thief) {
		this.thief = thief;
		thiefTimer = thief.getThiefTimer();
	}
	
	public void playerInteract(PlayerInteractEntityEvent e) {
		if(Main.permission.playerInGroup(e.getPlayer(), "thief")) {
			if(thiefTimer.pickpocket()) {
				if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.SHEARS) {
					Player currentPlayer = e.getPlayer();
					Player targetPlayer = null;
					
					try {
						targetPlayer = (Player) e.getRightClicked();
					}
					catch (Exception exc) {
						
					}
					
					if(e.getHand().equals(EquipmentSlot.HAND)) {
						Random rand = new Random();
						int num = rand.nextInt(100) + 1;
						
						double targetBalance = Main.economy.getBalance(targetPlayer);
						double picked = 0;
						
						Object[] input = new Object[2];
						input[0] = currentPlayer;
						input[1] = 20;
						if(num < 5) {
							//get 20%
							picked = targetBalance*.2;
						}
						else if(num < 15) {
							// get 15%
							picked = targetBalance*.15;
						}
						else if(num < 30) {
							// get 10%
							picked = targetBalance*.1;
							
						}
						else if(num < 50) {
							// get 5%
							picked = targetBalance*.05;
						}
						else {
							// get nothing
						}
						Main.economy.withdrawPlayer(targetPlayer, picked);
						Main.economy.depositPlayer(currentPlayer, picked);
						currentPlayer.sendMessage("You have pickpocketed " + picked + " from " + targetPlayer.getName());
						thiefCooldown.add(input);
					}
				}
			}
			else {
				e.getPlayer().sendMessage("Your pickpocket cooldown is not up yet");
			}
			
		}
		
	}
}
