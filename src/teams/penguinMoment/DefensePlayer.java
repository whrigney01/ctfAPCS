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

			Location nextLoc = emp.get((int)(Math.random()*emp.size()));
			if(this.getMyTeam().getFlag().getLocation().getCol() - nextLoc.getCol() - 10 >= 0 && this.getMyTeam().getFlag().getLocation().getRow() - nextLoc.getRow() - 10 >= 0){
				return  nextLoc;
			}else{
				if(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(this.getMyTeam().getFlag().getLocation())) == null){
					return this.getMyTeam().getFlag().getLocation();
				}else{

					int dir = this.getLocation().getDirectionToward(this.getMyTeam().getFlag().getLocation());
					int negPos = -1;
					for (int i = 0; i < 7; i++) {
						dir += 45 * i * negPos;
						negPos *= -1;
						Location adjLoc = this.getLocation().getAdjacentLocation(dir);
						if (this.getGrid().isValid(adjLoc)) {
							if (this.getGrid().get(adjLoc) == null) {
								return adjLoc;
							}
						}
					}
				}

			}
	}

		return this.getLocation();
	}
}
