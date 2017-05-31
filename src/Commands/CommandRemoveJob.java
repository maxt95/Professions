package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemoveJob implements CommandExecutor{
	ArrayList<String> availableJobs = CommandCreateJob.availableJobs;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			for(int i = 0; i < availableJobs.size(); i++) {
				String[] s = availableJobs.get(i).split(" ");
				if(player.getName().equals(s[0])) {
					availableJobs.remove(i);
				}
			}
		}
		return true;
	}

}
