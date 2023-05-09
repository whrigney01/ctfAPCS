package teams.penguinMoment;

import ctf.Player;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;

public class DefensePlayer extends Player {

	public DefensePlayer(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		boolean hasFlagStatic = false;
		ArrayList<Player> team= getOtherTeam().getPlayers();
		for (int i = 0; i < team.size(); i++) {
			if(team.get(i).hasFlag()){
				hasFlagStatic = true;
			}
		}
		if(hasFlagStatic){
			ArrayList<Player> play = this.getOtherTeam().getPlayers();
			for (int i = 0; i < play.size(); i++) {
				if (play.get(i).hasFlag()) {
					return play.get(i).getLocation();
				}
			}
		}else{
			ArrayList<Location> emp = this.getGrid().getEmptyAdjacentLocations(this.getLocation());
			return emp.get((int)(Math.random()*emp.size()));
		}

		return this.getLocation();
	}
}
