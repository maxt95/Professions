package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.RPPlayer;

public class CommandPerks implements CommandExecutor {
	RPPlayer rpPlayer;
	ArrayList<RPPlayer> onlinePlayers;
	public CommandPerks(ArrayList<RPPlayer> onlinePlayers) {
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
			
			
			if(profession.equals("citizen")) {
				sender.sendMessage("Act like a normal Citizen");
				sender.sendMessage("Go about your daily life in peace.");
			}
			else if(profession.equals("police")) {
				sender.sendMessage("Put peaple into jail");
				sender.sendMessage("Inspect people to see name and chance at inventory contents");
			}
			else if(profession.equals("policechief")) {
				sender.sendMessage("Manage the police force");
				sender.sendMessage("");
			}
			else if(profession.equals("hobo")) {
				sender.sendMessage("Nothing?");
			}
			else if(profession.equals("drugdealer")) {
				sender.sendMessage("Buy drugs at low prices and sell them for higher");
				sender.sendMessage("");
			}
			else if(profession.equals("mayor")) {
				sender.sendMessage("Control public officials daily pay");
				sender.sendMessage("Control tax rate");
				sender.sendMessage("Appoint Chief of Police");
				sender.sendMessage("Highest income level");
				sender.sendMessage("Possibly invest into things? rng chances at succeeding and failing and costing the city money");
			}
			else if(profession.equals("drugdealer")) {
				sender.sendMessage("Buy drugs at low prices and sell them for higher");
				sender.sendMessage("");
			}
			else {
				
			}
			
		}
		return true;
	}

}
