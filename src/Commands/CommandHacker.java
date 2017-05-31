package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;

public class CommandHacker implements CommandExecutor{

	ArrayList<RPPlayer> onlinePlayers;
	public CommandHacker(ArrayList<RPPlayer> onlinePlayers)
	{
		this.onlinePlayers = onlinePlayers;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] content) {
		
		if(sender instanceof Player){
			
			sender.sendMessage("Thanks for the support!");
			sender.sendMessage("You can now hack the city as you please.");
			sender.sendMessage("Use /perks to view your skills");
			Player player = ((Player) sender).getPlayer();
			for(int i = 0; i < onlinePlayers.size(); i++) {
				if (onlinePlayers.get(i).getName().equals(player.getName()))
				{
					onlinePlayers.get(i).setProfession("hacker");
					String currentGroup = Main.permission.getPrimaryGroup(player);
					Main.permission.playerRemoveGroup(player, currentGroup);
					Main.permission.playerAddGroup(player, "hacker");
					
				}
			}
		}
		return true;
	}

}
