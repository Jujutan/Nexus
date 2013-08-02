package Nexus.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import Nexus.Commands.CommandNexus;
import Nexus.Core.NexusCore;

public class NexusEventListener implements Listener {
	@EventHandler
	public void onNexusBlockSet(PlayerInteractEvent event){
		if( NexusCore.isNexusSetmode &&
				event.hasItem() && event.hasBlock() &&
				event.getItem().getTypeId() == Material.STICK.getId()){
			Location loc = event.getClickedBlock().getLocation();
			NexusCore.Team.setLocation("NexusBlock." +
					NexusCore.Team.getString(CommandNexus.Team + ".name"), loc);
			NexusCore.broadCastMessage(NexusCore.getMessage("success.NexusSet"));
			event.setCancelled(true);
			NexusCore.isNexusSetmode = false;
		}
	}

}
