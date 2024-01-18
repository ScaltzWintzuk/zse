package zes.core.engine.multiplayertestlol;

import java.util.ArrayList;

import zes.core.engine.windows.Screen;
import zes.core.game.gameobjects.Player;

public class Session {
	public String name;
	public int id;
	
	private ArrayList<Player> players;
	private ArrayList<Screen> screens;
	
	public Session() {
		players = new ArrayList<Player>();
		screens = new ArrayList<Screen>();
	}
	
	public void debugDisplayPlayers() {
		
	}
}
