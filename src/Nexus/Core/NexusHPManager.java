package Nexus.Core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.bukkit.entity.Player;

public class NexusHPManager {
	int[] HP;
	Map<Integer, String> TeamMap = new HashMap<Integer, String>();
	Map<Integer, LinkedList<Player>> NexusPlayers = new HashMap<Integer, LinkedList<Player>>();
	public void updateHPBar(){
		for(int i = 0; i < TeamMap.size(); i++){
			for(Player t : NexusPlayers.get(TeamMap.keySet().toArray()[i])){
				t.setExp(HP[i]);
			}
		}
	}
	public void addTeam(int TeamID, String TeamName){
		TeamMap.put(TeamID, TeamName);
	}
	public void addPlayer(int TeamID, Player player){
		NexusPlayers.get(TeamID).add(player);
	}
	public void clear(){
		TeamMap.clear();
		NexusPlayers.clear();
	}
	
}
