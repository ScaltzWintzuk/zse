package zes.core.engine.audio;

import java.util.LinkedList;
import java.util.Queue;

public class SongsManager {
	private Queue<String> songs;
	
	public SongsManager() {
		songs = new LinkedList<String>();
	}
	
	public void add(String filePath) {
		songs.add(filePath);
	}
	
	public String remove() {
		return songs.poll();
	}
	
	public boolean isEmpty() {
		return songs.isEmpty();
	}
	
	public void sortAlphabetically() {
		Object[] tempSongs = songs.toArray();
		
		for (int i = 0; i < songs.size(); i++) {
			for (int j = 0; j < songs.size()- i; j++) {
				
			}
		}
	}
}
