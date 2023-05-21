package teams.penguinMoment;

import ctf.Player;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class LurePlayer extends OffensePlayer {
    private Location lurePlace;
    private Location oldLoc;
    private Location oldOldLoc;

    private int count = 0;

    private static int steps;

    public LurePlayer(Location startLocation) {
        super(startLocation, 1);
        oldLoc = new Location(-1,-1);
        oldOldLoc = new Location(-1,-1);

    }

    public Location getMoveLocation() {
        steps++;
        //Every 50 steps make a new random location to pathfind to
        if(steps % 50 == 0){
            if(this.getMyTeam().getSide() == 1) {
                lurePlace = new Location(((int)(Math.random() * 50)), ((int)(Math.random() * 50)));
            }else{
                lurePlace = new Location(((int)(Math.random() * 50)), ((int)(Math.random() * 50 + 50)));
            }
        }
        if(lurePlace == null){
            lurePlace = new Location(0, Math.abs(99 * (this.getMyTeam().getSide() - 1)));
        }

        //Figures out where other plays are to navigate around them
        ArrayList<Player> ePlayers = this.getOtherTeam().getPlayers();
        ArrayList<Location> eLocs = new ArrayList<>();
        for (int j = 0; j < ePlayers.size(); j++) {
            ArrayList<Location> notOccLocs = ePlayers.get(j).getGrid().getValidAdjacentLocations(ePlayers.get(j).getLocation());
            for (int i = 0; i < notOccLocs.size(); i++) {
                eLocs.add(notOccLocs.get(i));
            }
        }
        count++;
        //go to the random location navigating around rocks and players
        if (this.getLocation() != lurePlace) {
            if (this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(lurePlace)) != null) {

                int dir = this.getLocation().getDirectionToward(lurePlace);
                int negPos = -1;
                for (int i = 0; i < 8; i++) {
                    dir += 45 * i * negPos;
                    negPos *= -1;
                    Location adjLoc = this.getLocation().getAdjacentLocation(dir);
                    boolean enemyAdjacent = false;
                    for (int j = 0; j < eLocs.size(); j++) {
                        if (eLocs.get(j).equals(adjLoc)) {
                            enemyAdjacent = true;
                        }
                    }
                    if (this.getGrid().isValid(adjLoc)) {
                        if (this.getGrid().get(adjLoc) == null && !oldLoc.equals(adjLoc) && !oldOldLoc.equals(adjLoc) && !this.getMyTeam().nearFlag(adjLoc) && !enemyAdjacent) {

                            if (count > 1) {
                                oldOldLoc = oldLoc;
                            }
                            oldLoc = this.getLocation();
                            return adjLoc;
                        }
                    }
                }
            } else {
                return lurePlace;
            }
            return this.getLocation();
            //If at the random Location, move to the corners until a new location is selected
        } else {
            if (this.getMyTeam().getSide() == 0) {
                return new Location(0, 98);
            } else {
                return new Location(0, 1);
            }
        }
    }
}
