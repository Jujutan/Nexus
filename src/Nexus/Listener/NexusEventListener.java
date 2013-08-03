package Nexus.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import Nexus.Commands.CommandNexus;
import Nexus.Core.NexusCore;
import Nexus.Core.NexusTeamManager;

public class NexusEventListener implements Listener {
	@EventHandler
	public void onNexusBlockSet(PlayerInteractEvent event){
		if( NexusCore.isNexusSetmode &&
				event.hasItem() && event.hasBlock() &&
				event.getItem().getTypeId() == Material.STICK.getId()){
			Location loc = event.getClickedBlock().getLocation();
			NexusCore.Team.setLocation("NexusBlock." + CommandNexus.Team, loc);
			NexusCore.Team.setInteger("NexusBlock." + CommandNexus.Team + ".Block",
						event.getClickedBlock().getTypeId());
			NexusCore.broadCastMessage(NexusCore.getMessage("success.NexusSet"));
			event.setCancelled(true);
			NexusCore.isNexusSetmode = false;
		}
	}
	@EventHandler
	public void onNexusDamaged(PlayerInteractEvent event){
		if( !NexusCore.isNexusSetmode &&
				event.hasItem() && event.hasBlock()){
			if(isNexus(event.getClickedBlock()))
			event.setCancelled(true);
			NexusCore.getTeamManager().updateHPBar();
		}
	}
	private boolean isNexus(Block par1Block) {
		NexusTeamManager manager = NexusCore.getTeamManager();
		for(String t : manager.getTeams()){
			if(par1Block.getTypeId() ==
					NexusCore.Team.getInteger("NexusBlock." + t + ".Block") &&
				par1Block.getLocation().equals(NexusCore.Team.getLocation("NexusBlock." + t))){
				return true;
			}
		}
		return false;
	}
}
