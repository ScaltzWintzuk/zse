package zes.core.game.gameobjects;

import zes.core.engine.utils.Constants;

public class Team {
	private Entity[] entities;
	
	public Team() {
		this(4);
	}
	
	public Team(int maxSize) {
		entities = new Entity[maxSize];
	}
	
	/**
	 * Given an index, returns the entity at that array location
	 * @param index
	 * @return
	 */
	public Entity getEntity(int index) {
		if (index >= entities.length) { return null; } 
		
		return entities[index];
	}
	
	/**
	 * Grabs the entire team of Entity(ies)
	 * @return
	 */
	public Entity[] getTeam() {
		return entities;
	}
	
	/**
	 * Returns the size of the team
	 * @return
	 */
	public int getSize() { 
		return entities.length;
	}
	
	/**
	 * Updates the entity given an index returning true or false if it was successful or not
	 * @param entity
	 * @param index
	 * @return
	 */
	public boolean updateEntity(Entity entity, int index) {
		if (index >= entities.length) { return false; }
		
		entities[index] = entity;
		
		return true;
	}
	
	/**
	 * Says the debug info for the entire team with their fields
	 */
	public void debugTeamInfo() {
		System.out.printf("%sTeam Info: \n%s", Constants.DEBUG_INFO_LINES, Constants.DEBUG_INFO_LINES);
		
		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null) {
				System.out.printf("Slot #%d: Name: %s -- Id: %d\n", i, entities[i].getName(), entities[i].getId());
			}
			else {
				System.out.printf("Slot #%d: Empty Position\n", i);
			}
		}
		
	}
}
