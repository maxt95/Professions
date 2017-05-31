package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;

public class CommandMobster implements CommandExecutor{
	ArrayList<RPPlayer> onlinePlayers;
	public CommandMobster(ArrayList<RPPlayer> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player) {
			
			sender.sendMessage("Welcome to the high life!");
			sender.sendMessage("Go out and deal.");
			sender.sendMessage("Use /perks to view your skills");
			Player player = ((Player) sender).getPlayer();
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					onlinePlayers.get(i).setProfession("mobster");
					String currentGroup = Main.permission.getPrimaryGroup(player);
					Main.permission.playerRemoveGroup(player, currentGroup);
					Main.permission.playerAddGroup(player, "mobster");
					
				}
			}
			
		}
		
		return true;
	}

}
