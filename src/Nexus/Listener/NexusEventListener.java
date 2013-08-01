package Nexus.Listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import Nexus.Core.NexusCore;

public class NexusEventListener implements Listener {
	@EventHandler
	public void onNexusBlockSet(PlayerInteractEvent event){
		if(event.hasItem() && event.hasBlock() && event.getItem().getTypeId() == Material.STICK.getId()){
			Location loc = event.getClickedBlock().getLocation();
			NexusCore.plugin.cfg.setLocation("Nexus.NexusBlock", loc);
			NexusCore.broadCastMessage(NexusCore.plugin.getPrefix() + NexusCore.plugin.getMessage("success.NexusSet"));
		}
	}

}
