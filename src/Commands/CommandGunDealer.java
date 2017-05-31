package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;

public class CommandGunDealer implements CommandExecutor{

	ArrayList<RPPlayer> onlinePlayers;
	public CommandGunDealer(ArrayList<RPPlayer> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player) {
			
			sender.sendMessage("Provide citizens with weapons to defend themselves.");
			sender.sendMessage("Or attack...");
			sender.sendMessage("Use /perks to view your skills");
			Player player = ((Player) sender).getPlayer();
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					onlinePlayers.get(i).setProfession("gundealer");
					String currentGroup = Main.permission.getPrimaryGroup(player);
					Main.permission.playerRemoveGroup(player, currentGroup);
					Main.permission.playerAddGroup(player, "gundealer");
					
				}
			}
			
		}
		
		return true;
	}
}
