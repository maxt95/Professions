package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;

public class CommandPolice implements CommandExecutor {

	RPPlayer rpPlayer;
	ArrayList<RPPlayer> onlinePlayers;
	public CommandPolice(ArrayList<RPPlayer> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player)
		{
			sender.sendMessage("Welcome to City Protection!");
			sender.sendMessage("You are now a Police Officer.");
			sender.sendMessage("Use /perks to view your skills");
			
			Player player = ((Player) sender).getPlayer();
			
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					
					onlinePlayers.get(i).setProfession("police");
					String currentGroup = Main.permission.getPrimaryGroup(player);
					Main.permission.playerRemoveGroup(player, currentGroup);
					Main.permission.playerAddGroup(player, "police");
				}
			}
			
			
		}
		
		
		return true;
	}

}
