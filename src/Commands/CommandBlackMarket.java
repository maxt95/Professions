package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;

public class CommandBlackMarket implements CommandExecutor {
	ArrayList<RPPlayer> onlinePlayers;
	public CommandBlackMarket(ArrayList<RPPlayer> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player) {
			
			sender.sendMessage("You are now the reseller of hard to get goods!");
			sender.sendMessage("Buy Black Market goods at a low price and sell them higher.");
			sender.sendMessage("Use /perks to view your skills and /commands to view available commands.");
			Player player = ((Player) sender).getPlayer();
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					onlinePlayers.get(i).setProfession("blackmarketdealer");
					String currentGroup = Main.permission.getPrimaryGroup(player);
					Main.permission.playerRemoveGroup(player, currentGroup);
					Main.permission.playerAddGroup(player, "blackmarketdealer");
					
				}
			}
			
		}
		
		return true;
	}

}
