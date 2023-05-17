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

		if (this.getMyTeam().getSide() == 0){
			locto = new Location(this.getMyTeam().getFlag().getLocation().getRow() + num, this.getMyTeam().getFlag().getLocation().getCol() + 5);
		} else{
			locto = new Location(this.getMyTeam().getFlag().getLocation().getRow() + num, this.getMyTeam().getFlag().getLocation().getCol() - 5);
		}

		boolean hasFlagStatic = false;



		ArrayList<Player> team= getOtherTeam().getPlayers();
		for (int i = 0; i < team.size(); i++) {
			if(team.get(i).hasFlag()){
				hasFlagStatic = true;
			}

		}
		count++;
		if(hasFlagStatic) {
			ArrayList<Player> play = this.getOtherTeam().getPlayers();
			for (int i = 0; i < play.size(); i++) {
				if (play.get(i).hasFlag()) {
					return play.get(i).getLocation();
				}
			}
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
