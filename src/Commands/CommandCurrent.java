package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.RPPlayer;

public class CommandCurrent implements CommandExecutor {

	ArrayList<RPPlayer> onlinePlayers;
	public CommandCurrent(ArrayList<RPPlayer> onlinePlayers) {
		this.onlinePlayers = onlinePlayers;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player) {
			Player player = ((Player) sender).getPlayer();
			String profession = "";
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					
					profession = onlinePlayers.get(i).getProfession();
				}
			}
			sender.sendMessage("Current profession: " + profession);
			
		}
		return true;
	}

}
