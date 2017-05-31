package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.Main;
import com.nametagedit.plugin.NametagEdit;

public class CommandDisguiseOff implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(Main.permission.playerInGroup(player, "hacker")) {
				Main.permission.playerRemoveGroup(player, Main.permission.getPrimaryGroup(player));
				Main.permission.playerAddGroup(player, "hacker");
				
			}
		}
		return true;
	}
 
	
}
