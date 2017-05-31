package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;

public class CommandAcceptJob implements CommandExecutor{

	ArrayList<String> availableJobs = CommandCreateJob.availableJobs;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(Main.permission.playerInGroup(player, "guard")) {
				if(content.length > 0) {
					for(int i = 0; i < availableJobs.size(); i++){
						String[] s = availableJobs.get(i).split(" ");
						if(s[0].equals(content[0])) {
							player.sendMessage("You have accepted the job from " + s[0]);
							CommandCreateJob.availableJobs.remove(i);
							availableJobs.remove(i);
						}
						else {
							sender.sendMessage("Please enter the player's name correctly");
						}
					}
				}
				else{
					sender.sendMessage("Please use the format, /acceptjob playername");
					
				}
				
			}
			else{
				sender.sendMessage("You must be a guard to use this command");
			}
			
		}
		return true;
	}

}
