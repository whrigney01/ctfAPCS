package ctf2022.penguinMomentTeam;

import ctf2022.Player;
import info.gridworld.grid.Location;

import java.util.List;

public class OffensePlayer extends Player {
    private static boolean hasFlager;
    public OffensePlayer(Location startLocation) {
        super(startLocation);
        hasFlager = false;
    }

    @Override
    public Location getMoveLocation() {
        int dir;
        if (this.hasFlag()) {
            hasFlager = true;
            return this.getMyTeam().getFlag().getLocation();
        } else {
            return getOtherTeam().getFlag().getLocation();
        }
    }
}
