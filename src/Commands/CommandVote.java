package Commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maxt95.core.Professions.RPPlayer;
import com.maxt95.core.Professions.VoteCore;

public class CommandVote implements CommandExecutor {

	RPPlayer rpPlayer;
	ArrayList<RPPlayer> onlinePlayers;
	ArrayList<Player> playerVotes = new ArrayList<Player>();
	public CommandVote(ArrayList<RPPlayer> onlinePlayers){
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
		
			
			
			if(!profession.equals("")) {
				if(VoteCore.getStatus() && VoteCore.isMayor()) { // if election can be held and there is a mayor
					if(content.length == 0) {	// get the status on votes
						if(VoteCore.getVoteStats().size() > 0) {
							sender.sendMessage("Current Stats");
							for(int i = 0; i < VoteCore.getVoteStats().size(); i++) {
								String[] votes = VoteCore.getVoteStats().get(i);
								
								sender.sendMessage(votes[0] + " | " + votes[1]);
							}
						}
						
						
					}
					else if(content.length == 1){ // if casting a vote
						
						for(int i = 0; i < onlinePlayers.size(); i++) {
							if(content[0].equals(onlinePlayers.get(i).getName())) {
								if(VoteCore.voteForMayor(player.getName(), content[0])) { // if player has not voted
									sender.sendMessage("You have successfully voted!");
								}
								else {
									sender.sendMessage("You have already voted");
								}
							}
							else{
								sender.sendMessage("Please vote for an online player");
							}
						}
						//sender.sendMessage("You have successfully voted for " + content[0]);
					}
					else {
						sender.sendMessage("Please use the format: /vote playername");
					}
				}
				else if(!VoteCore.isMayor()) { // if election can be held but there is not a mayor
					if(content.length == 0) {	// get the status on votes
						if(VoteCore.getVoteStats().size() > 0) {
							sender.sendMessage("Current Stats");
							for(int i = 0; i < VoteCore.getVoteStats().size(); i++) {
								String[] votes = VoteCore.getVoteStats().get(i);
								
								sender.sendMessage(votes[0] + " | " + votes[1]);
							}
						}
						
						
					}
					else if(content.length == 1){ // if casting a vote
						
						for(int i = 0; i < onlinePlayers.size(); i++) {
							if(content[0].equals(onlinePlayers.get(i).getName())) {
								if(VoteCore.voteForMayor(player.getName(), content[0])) { // if player has not voted
									sender.sendMessage("You have successfully voted!");
								}
								else {
									sender.sendMessage("You have already voted");
								}
							}
							else{
								sender.sendMessage("Please vote for an online player");
							}
						}
						//sender.sendMessage("You have successfully voted for " + content[0]);
					}
					else {
						sender.sendMessage("Please use the format: /vote playername");
					}
				}
				else {
					sender.sendMessage("You cannot vote for a new mayor for ");
				}
			}
			
		}
		return true;
	}
	public String getTimeRemaining() {
		String s = "";
		return s;
	}

}
