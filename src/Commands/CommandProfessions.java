package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandProfessions implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] content) {
		
		if(sender instanceof Player) {
			sender.sendMessage("Drug Dealer: /drugdealer");
			sender.sendMessage("Hobo: /hobo");
			sender.sendMessage("Police Officer: /police");
			sender.sendMessage("Police Chief: appointed by mayor");
			sender.sendMessage("Thief: /thief");
			sender.sendMessage("Mayor: Get voted in");
			sender.sendMessage("Citizen: starting profession, /citizen");
			sender.sendMessage("Guard: /guard");
			sender.sendMessage("BlackMarketDealer: /blackmarketdealer");
			sender.sendMessage("Gun Dealer: /gundealer");
			sender.sendMessage("Hitman: /hitman");
			sender.sendMessage("Mobster: /mobster");
			sender.sendMessage("Mob Boss: get voted in");
			
		}
		return true;
	}

}
