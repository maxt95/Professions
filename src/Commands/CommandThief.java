package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.maxt95.core.Professions.Main;
import com.maxt95.core.Professions.RPPlayer;
import com.maxt95.core.Professions.ThiefTimer;

public class CommandThief implements CommandExecutor {

	ThiefTimer thiefTimer;
	ArrayList<RPPlayer> onlinePlayers;
	JavaPlugin plugin;
	public CommandThief(ArrayList<RPPlayer> onlinePlayers, JavaPlugin plugin) {
		this.onlinePlayers = onlinePlayers;
		this.plugin = plugin;
	}
	

	public ThiefTimer getThiefTimer() {
		return thiefTimer;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		sender.sendMessage("Part of the underground community huh?");
		sender.sendMessage("Well what are you waiting for?");
		sender.sendMessage("Use /perks to view your skills");
		Player player = ((Player) sender).getPlayer();
		for(int i = 0; i < onlinePlayers.size(); i++) {
			if (onlinePlayers.get(i).getName().equals(player.getName()))
			{
				
				onlinePlayers.get(i).setProfession("thief");
				String currentGroup = Main.permission.getPrimaryGroup(player);
				Main.permission.playerRemoveGroup(player, currentGroup);
				Main.permission.playerAddGroup(player, "thief");
				ThiefTimer thiefTimer = new ThiefTimer(plugin);
			}
		}
		return true;
	}

}
