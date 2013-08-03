package Nexus.Core;

import java.util.Iterator;
import java.util.LinkedList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import Nexus.Util.BiMap;

public class NexusTeamManager {
	private BiMap<Integer, Integer> HPMap = new BiMap<Integer, Integer>();
	private BiMap<Integer, Location> NexusMap = new BiMap<Integer, Location>();
	private BiMap<Integer, String> TeamMap = new BiMap<Integer, String>();
	private BiMap<Integer, LinkedList<Player>> Players = new BiMap<Integer, LinkedList<Player>>();
	public void updateHPBar(){
		Iterator<Player> it;
		for(int i = 0; i < TeamMap.size(); i++){
			it = Players.get(i).iterator();
			while(it.hasNext()){
				it.next().setExp(HPMap.get(i) / 0.005F);
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
		return TeamMap.values().toArray(new String[TeamMap.size()]);
	}
	public String getTeamName(Player player){
		return TeamMap.get(getTeamID(player));
	}
	public int getTeamID(String par1Team){
		for(int i = 0; i < TeamMap.size(); i++){
			if(TeamMap.get(i).contains(par1Team))
				return i;
		}
		return -1;
	}
	public int getTeamID(Player player){
		for(int i = 0; i < Players.size(); i++){
			if(Players.get(i).contains(player)){
				return i;
			}
		}
		return -1;
	}
	public boolean containTeam(String par1Str){
		Iterator<String> i = TeamMap.values().iterator();
		while(i.hasNext()){
			if(i.next().equals(par1Str)){
				return true;
			}
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
