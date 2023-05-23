package teams.penguinMoment;

import ctf.Player;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;

public class DefensePlayer extends Player {
	private Location locto;
	private int num;
	private Location oldLoc;
	private Location oldOldLoc;
	private int count = 0;
	public DefensePlayer(Location startLocation, int num) {
		super(startLocation);
		this.locto = startLocation;
		this.num = num;
		oldLoc = new Location(-1,-1);
		oldOldLoc = new Location(-1,-1);
	}

	public Location getMoveLocation() {
		//Determines where the defense should go based on their size
		if (this.getMyTeam().getSide() == 0){
			locto = new Location(this.getMyTeam().getFlag().getLocation().getRow() + num, this.getMyTeam().getFlag().getLocation().getCol() + 5);
		} else{
			locto = new Location(this.getMyTeam().getFlag().getLocation().getRow() + num, this.getMyTeam().getFlag().getLocation().getCol() - 5);
		}

		boolean hasFlagStatic = false;


		//figures our if the other team has the flag
		ArrayList<Player> team= getOtherTeam().getPlayers();
		for (int i = 0; i < team.size(); i++) {
			if(team.get(i).hasFlag()){
				hasFlagStatic = true;
			}

		}
		//If the other team does have the flag go straight to them while navigating around rocks
		if(hasFlagStatic) {
			count++;
			ArrayList<Player> play = this.getOtherTeam().getPlayers();
			for (int i = 0; i < play.size(); i++) {
				if (play.get(i).hasFlag()) {

					if(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(play.get(i).getLocation())) == null) {
						return play.get(i).getLocation();
					}else{
						int dir1 = this.getLocation().getDirectionToward(play.get(i).getLocation()	);
						System.out.println(play.get(i).getLocation());
						int negPos1 = -1;
						for (int j = 0; j < 8; j++) {
							dir1 += 45 * j * negPos1;
							negPos1 *= -1;
							Location adjLoc1 = this.getLocation().getAdjacentLocation(dir1);
							if (this.getGrid().isValid(adjLoc1)) {
								if (this.getGrid().get(adjLoc1) == null && !oldLoc.equals(adjLoc1) && !oldOldLoc.equals(adjLoc1)) {
									if(count>1){
										oldOldLoc = oldLoc;
									}
									oldLoc = this.getLocation();
									return adjLoc1;
								}
							}
						}
					}
				}
			}
			//If other team does not have the flag figure out if the player is in its area where it alternates and if so then alternate
			//If it is not in its alternating area it will go to that area
		}else if(this.getLocation().getRow() != locto.getRow() && (locto.getCol() != this.getLocation().getCol() || locto.getCol() + 1 != this.getLocation().getCol() || locto.getCol() -1 != this.getLocation().getCol())) {
			if (!this.getMyTeam().nearFlag(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(locto))) && this.getGrid().get(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(locto))) == null){
				return locto;
			}	else{
				int dir = this.getLocation().getDirectionToward(locto);
				int negPos = -1;
				for (int i = 0; i < 8; i++) {
					dir += 45 * i * negPos;
					negPos *= -1;
					Location adjLoc = this.getLocation().getAdjacentLocation(dir);
					if (this.getGrid().isValid(adjLoc)) {
						if (this.getGrid().get(adjLoc) == null && !oldLoc.equals(adjLoc) && !oldOldLoc.equals(adjLoc) && !this.getMyTeam().nearFlag(adjLoc)) {
							if(count>1){
								oldOldLoc = oldLoc;
							}
							oldLoc = this.getLocation();
							return adjLoc;
						}
					}
				}
			}
		}else{
			//Code to actually alternate the player
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
