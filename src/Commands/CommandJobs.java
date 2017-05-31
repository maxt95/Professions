package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;

public class CommandJobs implements CommandExecutor {
	public CommandJobs() {
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		ArrayList<String> availableJobs = CommandCreateJob.availableJobs;
		Player player = (Player) sender;
		if(Main.permission.playerInGroup(player, "guard")) {
			for(int i = 0; i < availableJobs.size(); i++) {
				player.sendMessage(availableJobs.get(i));
			}
		}
		return true;
	}

}
