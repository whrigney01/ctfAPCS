package ctf2022.penguinMomentTeam;

import ctf2022.Player;
import info.gridworld.grid.Location;

import java.util.List;

public class OffensePlayer extends Player {
    public OffensePlayer(Location startLocation) {
        super(startLocation);
    }

    @Override
    public Location getMoveLocation() {
        int dir;
        if (this.hasFlag()) {
            return this.getMyTeam().getFlag().getLocation();
        } else {
            return getOtherTeam().getFlag().getLocation();
        }
    }
}
