package ctf2022.penguinMomentTeam;

import ctf2022.Player;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.util.List;

public class DefensePlayer extends Player {
    public DefensePlayer(Location startLocation) {
        super(startLocation);
//        this.getGrid().put(new Location(24, 11), new Rock());
//        this.getGrid().put(new Location(25, 11), new Rock());
//        this.getGrid().put(new Location(23, 11), new Rock());
//        this.getGrid().put(new Location(24, 9), new Rock());
//        this.getGrid().put(new Location(25, 9), new Rock());
//        this.getGrid().put(new Location(23, 9), new Rock());
//        this.getGrid().put(new Location(25, 10), new Rock());
//        this.getGrid().put(new Location(23, 10), new Rock());
    }

    @Override
    public Location getMoveLocation() {

        List<Location> possibleMoveLocations = getGrid().getEmptyAdjacentLocations(getLocation());
        if (possibleMoveLocations.size() == 0) return null;
        return possibleMoveLocations.get((int) (Math.random() * possibleMoveLocations.size()));
    }
}
