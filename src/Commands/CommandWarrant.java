package Commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;

public class CommandWarrant implements CommandExecutor {

	public static ArrayList<String> warrants = new ArrayList<String>();
	ArrayList<RPPlayer> onlinePlayers;
	public CommandWarrant(ArrayList<RPPlayer> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player) {
			Player player = ((Player) sender).getPlayer();
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					if(Main.permission.playerInGroup(player, "police")){
						if(content.length > 0) {
							String playerWarrant = content[0];
							if(content.length > 1) {
								for(int j = 0; j < onlinePlayers.size(); j++) {
									if(playerWarrant.equals(onlinePlayers.get(j).getName())){
										sender.sendMessage("Submitted search warrant for " + playerWarrant);
										String str = String.join(",", content);
										warrants.add(str);
										
										ItemStack warrantItem = new ItemStack(Material.PAPER);
										ItemMeta meta = warrantItem.getItemMeta();
										meta.setDisplayName(playerWarrant + "'s Warrant");
										ArrayList<String> lore = new ArrayList<String>();
										lore.add("Warrant to search " + playerWarrant + "'s house");
										meta.setLore(lore);
										warrantItem.setItemMeta(meta);
										player.getInventory().addItem(warrantItem);
									}
									else {
										sender.sendMessage("Error, that user does not exist! Include Capitalization when needed!");
									}
								}
							}
							
							else if(content.length == 1) {
								
								sender.sendMessage("Please use the format /warrant playername reason");
							}
							else {
								sender.sendMessage("Please use the format /warrant playername reason");
							}
						}
						else {
							sender.sendMessage("Please use the format /warrant playername reason");
						}
						
					}
					
				}
			}
		}
		
		return true;
	}

}
