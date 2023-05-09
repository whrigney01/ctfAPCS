package teams.penguinMoment;

import ctf.Team;
import info.gridworld.grid.Location;

import java.awt.*;

public class PenguinMomentTeam extends Team {

	public PenguinMomentTeam() {
		// set up Team through superconstructor
		super(Color.RED);

		// add your Players: add to left side - can not be too near the Flag
		super.addPlayer(new DefensePlayer(new Location(5 + (int)(Math.random()*3 - 1), 30)));
		super.addPlayer(new OffensePlayer(new Location(10 + (int)(Math.random()*3 - 1), 30)));
		super.addPlayer(new DefensePlayer(new Location(15 + (int)(Math.random()*3 - 1), 30)));
		super.addPlayer(new OffensePlayer(new Location(20 + (int)(Math.random()*3 - 1), 30)));
		super.addPlayer(new DefensePlayer(new Location(30 + (int)(Math.random()*3 - 1), 30)));
		super.addPlayer(new OffensePlayer(new Location(35 + (int)(Math.random()*3 - 1), 30)));
		super.addPlayer(new DefensePlayer(new Location(40 + (int)(Math.random()*3 - 1), 30)));
		super.addPlayer(new OffensePlayer(new Location(45 + (int)(Math.random()*3 - 1), 30)));
	}

}