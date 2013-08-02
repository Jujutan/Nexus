package Nexus.Commands;

import java.awt.Color;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import Nexus.Core.NexusCore;
import Nexus.Core.NexusTeamManager;



public class CommandNexus implements CommandExecutor {
	public static String Team;
	private NexusTeamManager manager;
	public CommandNexus(){
		manager = NexusCore.getTeamManager();
	}
	@Override
	public boolean onCommand(CommandSender par1Sender, Command par2Cmd, String par3Label, String[] par4Args) {
		if(par4Args[0].equalsIgnoreCase("set")){
			if((par4Args.length < 2 )){
				NexusCore.broadCastMessage(NexusCore.getMessage("error.IllegalArgument"));
				return false;
			}
			if(!manager.containTeam(par4Args[1])){
				NexusCore.broadCastMessage(NexusCore.getMessage("error.DontHaveTeam"),
						new String[][]{
								{"%team", par4Args[1]}
							});
				return false;
			}
			Team = par4Args[1];
			NexusCore.broadCastMessage(NexusCore.getMessage("success.NexusSetPrepare"));
			NexusCore.isNexusSetmode = true;
		}
		if(par4Args[0].equalsIgnoreCase("team")){
			if(par4Args[1].equalsIgnoreCase("list")){
				String[] teams = NexusCore.getTeamManager().getTeams();
				for(String t : teams){
					par1Sender.sendMessage(Color.LIGHT_GRAY + "Team name : " + t + "\n");
				}
			}
		}
		if(par4Args[0].equalsIgnoreCase("config")){
			if(par4Args[1].equalsIgnoreCase("force")){
				NexusCore.initCfg();
			}
		}
		return false;
	}


}
