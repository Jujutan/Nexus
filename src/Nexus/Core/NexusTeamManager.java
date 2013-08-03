package Nexus.Core;

import java.util.LinkedList;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import Nexus.Util.BiMap;

public class NexusTeamManager {
	private Map<Integer, Integer> HPMap = new BiMap<Integer, Integer>();
	private Map<Integer, Location> NexusMap = new BiMap<Integer, Location>();
	private Map<Integer, String> TeamMap = new BiMap<Integer, String>();
	private Map<Integer, LinkedList<Player>> Players = new BiMap<Integer, LinkedList<Player>>();
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
	public String getTeamName(String par1Str){
		return NexusCore.Team.getString(par1Str + ".name");
	}
	public String[] getTeams(){
		return TeamMap.values().toArray(new String[]{});
	}
	public int getTeamID(String par1Team){
		for(int i = 0; i < TeamMap.size(); i++){
			if(TeamMap.get(i).contains(par1Team))
				return i;
		}
		return -1;
	}
	public String getTeam(Player player){
		for(int i = 0; i < Players.size(); i++){
			if(Players.get(i).contains(player)){
				return TeamMap.get(i);
			}
		}
		return null;
	}
	public boolean containTeam(String par1Str){
		for(int i = 0; i < TeamMap.size(); i++){
			if(TeamMap.get(i).contains(par1Str))
				return true;
		}
		return false;
	}
	public void clear(){
		HPMap.clear();
		NexusMap.clear();
		TeamMap.clear();
		Players.clear();
	}

}
