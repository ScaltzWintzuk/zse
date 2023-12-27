package zes.core.engine.multiplayertestlol;

import java.util.ArrayList;

import zes.core.engine.windows.Screen;

/**
 * This class works as a way to edit/update/call the server at a hosted address.
 */
public class Server {
	private ArrayList<Screen> allScreens;
	
	public Server() {
		allScreens = new ArrayList<Screen>();
	}
	
	public Screen getScreenAtIndex(int index) {
		if (index >= allScreens.size()) { return null; }
		return allScreens.get(index);
	}
}
