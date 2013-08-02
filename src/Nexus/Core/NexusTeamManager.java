package Nexus.Core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NexusTeamManager {
	private Map<Integer, Integer> HPMap = new HashMap<Integer, Integer>();
	private Map<Integer, Location> NexusMap = new HashMap<Integer, Location>();
	private Map<Integer, String> TeamMap = new HashMap<Integer, String>();
	private Map<Integer, LinkedList<Player>> Players = new HashMap<Integer, LinkedList<Player>>();
	public void updateHPBar(){
		for(int i = 0; i < TeamMap.size(); i++){
			for(Player t : Players.get(TeamMap.keySet().toArray()[i])){
				t.setExp(HPMap.get(i));
			}
		}
	}
	public void addNexus(int TeamID, Location loc){
		NexusMap.put(TeamID, loc);
		HPMap.put(TeamID, 200);
	}
	public void DamageNexus(int TeamID){
		HPMap.put(TeamID, HPMap.get(TeamID) - 5);
		updateHPBar();
	}
	public void addTeam(int TeamID, String TeamName){
		TeamMap.put(TeamID, TeamName);
	}
	public void addPlayer(int TeamID, Player player){
		Players.get(TeamID).add(player);
	}
	public void clear(){
		HPMap.clear();
		NexusMap.clear();
		TeamMap.clear();
		Players.clear();
	}

}
