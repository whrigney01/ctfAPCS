package teams.BadPenguinMoment;

import ctf.Team;
import info.gridworld.grid.Location;

import java.awt.*;

public class BadPenguinMomentTeam extends Team{
    public BadPenguinMomentTeam() {
        // set up Team through superconstructor
        super(Color.BLUE);

        // add your Players: add to left side - can not be too near the Flag
        super.addPlayer(new BadDefensePlayer(new Location(23, 30)));
        super.addPlayer(new BadDefensePlayer(new Location(25, 30)));
        super.addPlayer(new BadDefensePlayer(new Location(27, 30)));
        super.addPlayer(new BadDefensePlayer(new Location(21, 30)));
        super.addPlayer(new BadOffensePlayer(new Location(10, 30)));
        super.addPlayer(new BadOffensePlayer(new Location(11, 30)));
        super.addPlayer(new BadOffensePlayer(new Location(45, 30)));
        super.addPlayer(new BadOffensePlayer(new Location(46, 30)));
    }
}
