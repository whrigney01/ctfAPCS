package teams.BadPenguinMoment;

import ctf.Player;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class BadOffensePlayer extends Player{
    private Location flagGo;
    private Location oldLoc;
    private Location oldOldLoc;
    private int count = 0;

    public BadOffensePlayer(Location startLocation) {
        super(startLocation);
        oldLoc = new Location(-1,-1);
        oldOldLoc = new Location(-1,-1);
    }


    public Location getMoveLocation() {
        ArrayList<Player> ePlayers = this.getOtherTeam().getPlayers();
        ArrayList<Location> eLocs = new ArrayList<>();
        for (int j = 0; j < ePlayers.size(); j++) {
            ArrayList<Location> notOccLocs= ePlayers.get(j).getGrid().getValidAdjacentLocations(ePlayers.get(j).getLocation());
            for (int i = 0; i < notOccLocs.size(); i++) {
                eLocs.add(notOccLocs.get(i));
            }
        }
        count++;
        boolean hasFlagStatic = false;
        ArrayList<Player> team = getMyTeam().getPlayers();
        for (int i = 0; i < team.size(); i++) {
            if (team.get(i).hasFlag()) {
                hasFlagStatic = true;
            }
        }
        if (flagGo == null) {
            if (this.getMyTeam().getSide() == 0) {
                flagGo = new Location((int) (Math.random() * 50), 50);
            } else {
                flagGo = new Location((int) (Math.random() * 50), 48);
            }
        }
        if (this.hasFlag()) {
            if (this.getGrid().get(this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(flagGo))) == null) {
                return this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(flagGo));
            } else {
                int dir = this.getLocation().getDirectionToward(flagGo);
                int negPos = -1;
                for (int i = 0; i < 8; i++) {
                    dir += 45 * i * negPos;
                    negPos *= -1;
                    Location adjLoc = this.getLocation().getAdjacentLocation(dir);
                    boolean enemyAdjacent = false;
                    for (int j = 0; j < eLocs.size(); j++) {
                        if(eLocs.get(j).equals(adjLoc)){
                            enemyAdjacent = true;
                        }
                    }
                    if (this.getGrid().isValid(adjLoc)) {
                        if (this.getGrid().get(adjLoc) == null && !oldLoc.equals(adjLoc) && !oldOldLoc.equals(adjLoc) && !this.getMyTeam().nearFlag(adjLoc) && !enemyAdjacent) {
                            if(count>1){
                                oldOldLoc = oldLoc;
                            }
                            oldLoc = this.getLocation();
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
                    for (int i = 0; i < 8; i++) {
                        dir += 45 * i * negPos;
                        negPos *= -1;
                        Location adjLoc = this.getLocation().getAdjacentLocation(dir);
                        boolean enemyAdjacent = false;
                        for (int j = 0; j < eLocs.size(); j++) {
                            if(eLocs.get(j).equals(adjLoc)){
                                enemyAdjacent = true;
                            }
                        }
                        if (this.getGrid().isValid(adjLoc)) {
                            if (this.getGrid().get(adjLoc) == null && !oldLoc.equals(adjLoc) && !oldOldLoc.equals(adjLoc) && !this.getMyTeam().nearFlag(adjLoc) && !enemyAdjacent) {

                                if(count>1){
                                    oldOldLoc = oldLoc;
                                }
                                oldLoc = this.getLocation();
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
