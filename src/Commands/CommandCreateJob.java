package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreateJob implements CommandExecutor{

	static ArrayList<String> availableJobs = new ArrayList<String>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(content.length > 0) {
				if(content.length > 1) {
					double amount = 0;
					int tmp;
					try {
						tmp = Integer.parseInt(content[0]);
					}
					catch (Exception e) {
						sender.sendMessage("Please enter a valid amount for the first argument");
					}
					String reason = String.join(" ", content);
					availableJobs.add(player.getName() + " " + reason);
					sender.sendMessage("You have created the job.");
				}
				else {
					sender.sendMessage("Please use the format: /createjob amount reason");
				}
			}
			else{
				player.sendMessage("Please use the format: /createjob amount reason");
			}
		}
		
		return true;
	}

}
