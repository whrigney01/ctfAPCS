package teams.penguinMoment;

import ctf.Team;
import info.gridworld.grid.Location;

import java.awt.*;

public class PenguinMomentTeam extends Team {

	public PenguinMomentTeam() {
		// set up Team through superconstructor
		super(Color.RED);

		// add your Players: add to left side - can not be too near the Flag
//		super.addPlayer(new DefensePlayer(new Location(23, 30)));
//		super.addPlayer(new DefensePlayer(new Location(25, 30)));
//		super.addPlayer(new DefensePlayer(new Location(27, 30)));
//		super.addPlayer(new DefensePlayer(new Location(21, 30)));
//		super.addPlayer(new OffensePlayer(new Location(10, 30)));
//		super.addPlayer(new OffensePlayer(new Location(11, 30)));
//		super.addPlayer(new OffensePlayer(new Location(45, 30)));
		super.addPlayer(new OffensePlayer(new Location(46, 30)));
	}

}