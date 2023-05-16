package teams.penguinMoment;

import ctf.Player;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;

public class DefensePlayer extends Player {
	private Location locto;
	private int num;
	public DefensePlayer(Location startLocation, int num) {
		super(startLocation);
		this.locto = startLocation;
		this.num = num;
	}

	public Location getMoveLocation() {

		if (this.getMyTeam().getSide() == 0){
			locto = new Location(this.getMyTeam().getFlag().getLocation().getRow() + num, this.getMyTeam().getFlag().getLocation().getCol() + 5);
		} else{
			locto = new Location(this.getMyTeam().getFlag().getLocation().getRow() + num, this.getMyTeam().getFlag().getLocation().getCol() - 5);
		}
		System.out.println(locto);

		boolean hasFlagStatic = false;



		ArrayList<Player> team= getOtherTeam().getPlayers();
		for (int i = 0; i < team.size(); i++) {
			if(team.get(i).hasFlag()){
				hasFlagStatic = true;
			}

		}
		if(hasFlagStatic) {
			ArrayList<Player> play = this.getOtherTeam().getPlayers();
			for (int i = 0; i < play.size(); i++) {
				if (play.get(i).hasFlag()) {
					return play.get(i).getLocation();
				}
			}
		}else if(this.getLocation().getRow() != locto.getRow() && (locto.getCol() != this.getLocation().getCol() || locto.getCol() + 1 != this.getLocation().getCol() || locto.getCol() -1 != this.getLocation().getCol())) {
			if (!this.getMyTeam().nearFlag(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(locto)))){
				return locto;
			}	else{
				ArrayList<Location> emptlocs1 = this.getGrid().getEmptyAdjacentLocations(this.getLocation());
				for (int i = 0; i < emptlocs1.size(); i++) {
					if(!this.getMyTeam().nearFlag(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(emptlocs1.get(i))))){
						return emptlocs1.get(i);
					}
				}
			}
		}else{
			if(this.getMyTeam().getSide() == 0){
				if(this.getLocation().getCol() == this.getMyTeam().getFlag().getLocation().getCol() + 5) {
					return new Location(this.getLocation().getRow(), this.getLocation().getCol() + 1);
				}else{
					return new Location(this.getLocation().getRow(), this.getLocation().getCol() - 1);
				}
			}
			if(this.getMyTeam().getSide() == 1){
				if(this.getLocation().getCol() == this.getMyTeam().getFlag().getLocation().getCol() - 5) {
					return new Location(this.getLocation().getRow(), this.getLocation().getCol() - 1);
				}else{
					return new Location(this.getLocation().getRow(), this.getLocation().getCol() + 1);
				}
			}
		}


		return this.getLocation();
	}
}
