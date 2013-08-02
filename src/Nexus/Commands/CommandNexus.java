package Nexus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import Nexus.Core.NexusCore;



public class CommandNexus implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender par1Sender, Command par2Cmd, String par3Label, String[] par4Args) {
		if(par4Args[0].equalsIgnoreCase("set")){
			NexusCore.broadCastMessage(NexusCore.getPrefix() +
					NexusCore.plugin.getMessage("success.NexusSetPrepare"));
			NexusCore.isNexusSetmode = true;
		}
		if(par4Args[0].equalsIgnoreCase("start")){
			
		}
		return false;
	}


}
