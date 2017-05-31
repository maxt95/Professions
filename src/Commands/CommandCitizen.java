package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;

public class CommandCitizen implements CommandExecutor{
	ArrayList<RPPlayer> onlinePlayers;
	public CommandCitizen(ArrayList<RPPlayer> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player) {
			
			sender.sendMessage("You are an everyday law abiding citizen.");
			sender.sendMessage("You can live out your boring life if you must.");
			sender.sendMessage("Use /perks to view your skills");
			Player player = ((Player) sender).getPlayer();
			RPPlayer rpPlayer = (RPPlayer) player;
			
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					onlinePlayers.get(i).setProfession("citizen");
					String currentGroup = Main.permission.getPrimaryGroup(player);
					Main.permission.playerRemoveGroup(player, currentGroup);
					Main.permission.playerAddGroup(player, "citizen");
					//rpPlayer.setProfession("Citizen");
					
				}
			}
			
		}
		
		return true;
	}
}
