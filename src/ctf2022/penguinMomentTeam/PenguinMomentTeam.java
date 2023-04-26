package ctf2022.penguinMomentTeam;

import ctf2022.Team;
import info.gridworld.grid.Location;

import java.awt.*;

public class PenguinMomentTeam extends Team {

        public PenguinMomentTeam(String name, Color color) {
            super(name, color);
        }

        public void generateTeam() {
            addPlayer(new OffensePlayer(new Location(5 + (int)(Math.random()*3 - 1), 30)));
            addPlayer(new OffensePlayer(new Location(10 + (int)(Math.random()*3 - 1), 30)));
            addPlayer(new OffensePlayer(new Location(15 + (int)(Math.random()*3 - 1), 30)));
            addPlayer(new OffensePlayer(new Location(20 + (int)(Math.random()*3 - 1), 30)));
            addPlayer(new DefensePlayer(new Location(30 + (int)(Math.random()*3 - 1), 30)));
            addPlayer(new DefensePlayer(new Location(35 + (int)(Math.random()*3 - 1), 30)));
            addPlayer(new DefensePlayer(new Location(40 + (int)(Math.random()*3 - 1), 30)));
            addPlayer(new DefensePlayer(new Location(45 + (int)(Math.random()*3 - 1), 30)));
        }
}
