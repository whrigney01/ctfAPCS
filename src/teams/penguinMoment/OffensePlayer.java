package teams.penguinMoment;

import ctf.Player;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class OffensePlayer extends Player {
	private Location flagGo;

	public OffensePlayer(Location startLocation) {
		super(startLocation);
	}

	public Location getMoveLocation() {
		boolean hasFlagStatic = false;
		ArrayList<Player> team = getMyTeam().getPlayers();
		for (int i = 0; i < team.size(); i++) {
			if (team.get(i).hasFlag()) {
				hasFlagStatic = true;
			}
		}
		if (flagGo == null) {
			if (this.getMyTeam().getSide() == 0) {
				flagGo = new Location((int) (Math.random() * 50), 0);
			} else {
				flagGo = new Location((int) (Math.random() * 50), 99);
			}
		}
		if (this.hasFlag()) {
			if (this.getGrid().get(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(flagGo))) == null) {
				return this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(flagGo));
			} else {
				int dir = this.getLocation().getDirectionToward(flagGo);
				int negPos = -1;
				for (int i = 0; i < 5; i++) {
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
			return this.getLocation();

		} else {
			//If a teammate has flag, move to rand loc that is adjacent
			if (hasFlagStatic) {
				ArrayList<Location> emptadjlocs = this.getGrid().getEmptyAdjacentLocations(this.getLocation());
				return emptadjlocs.get((int) (Math.random() * emptadjlocs.size()));

			} else {
				if (this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(this.getOtherTeam().getFlag().getLocation())) == null) {
					return this.getOtherTeam().getFlag().getLocation();
				} else {
					int dir = this.getLocation().getDirectionToward(this.getOtherTeam().getFlag().getLocation());
					int negPos = -1;
					for (int i = 0; i < 5; i++) {
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
				return this.getOtherTeam().getFlag().getLocation();
			}
		}
	}
}
