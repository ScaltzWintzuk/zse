package zes.core.engine.multiplayertestlol;

import java.util.ArrayList;

import zes.core.engine.windows.Screen;

/**
 * This class works as a way to edit/update/call the server at a hosted address.
 */
public class Server {
	private String HOST_IP = "127.0.0.1";
	private String PORT = "11111";
	
	private String IP = HOST_IP + ":" + PORT;
	
	private ArrayList<Session> allSessions;
	
	public Server() {
		allSessions  = new ArrayList<Session>();
	}
	
	/**
	 * Given an ID (Index), this returns the screen within the server that way all clients are synchronized
	 * @param index
	 * @return
	 */
	public Session getSession(int index) {
		// Invalid indexes will return a null screen later, but for now it is just a null state
		if (index < 0) { return null; }
		if (index >= allSessions.size()) { return null; }
		
		return allSessions.get(index);
	}
	
	/**
	 * Returns the total amount of sessions running on the server
	 * @return
	 */
	public int getTotalSessions() { return allSessions.size(); }
	
	/**
	 * Adds a Session to the ArrayList within the server. A session is one active environment with clients synchronized together to the same session. If there are 5 different multiplayer games going, then there are 5 sessions
	 * @param screen
	 */
	public void addSession(Session session) {
		if (session == null) { return; }
		
		allSessions.add(session);
	}
	
	/**
	 * Given a Session object, this method will return the index within the Server's Session ArrayList
	 * @param session
	 * @return
	 */
	public int getSessionsIndex(Session session) {
		for (int i = 0; i < allSessions.size(); i++) {
			if (allSessions.get(i).equals(session)) { return i; }
		}
		
		// Invalid index returned if it was not found
		return -1;
	}
	
	/**
	 * Removes a screen session given the Screen Object itself
	 * @param session
	 */
	public void removeSession(Session session) {
		if (session == null) { return; }
		
		allSessions.remove(session);
	}
	
	/**
	 * Removes a screen session given the index in the ArrayList
	 * @param index
	 */
	public void removeSession(int index) {
		if (index < 0) { return; }
		if (index >= allSessions.size()) { return; }
		
		allSessions.remove(index);
	}
	
}
