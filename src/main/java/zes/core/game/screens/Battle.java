package zes.core.game.screens;

import zes.core.game.gameobjects.Entity;
import zes.core.game.gameobjects.Team;

public class Battle {
	private Team playerTeam;
	private Team enemyTeam;
	
	private Entity[] entities;
	
	public Battle(Team playerTeamIn, Team enemyTeamIn) {
		playerTeam = playerTeamIn;
		enemyTeam = enemyTeamIn;
		
		entities = new Entity[playerTeam.getSize() + enemyTeam.getSize()];
		
		init();
	}
	
	/**
	 * Initializes the turn order of every entity within the Battle
	 */
	public void init() {
		int counter = 0;
		
		for (int i = 0; i < playerTeam.getSize(); i++) {
			if (playerTeam.getEntity(i) != null) {
				entities[counter] = playerTeam.getEntity(i);
				counter++;
			}
		}
		
		for (int i = 0; i < enemyTeam.getSize(); i++) {
			if (enemyTeam.getEntity(i) != null) {
				entities[counter] = enemyTeam.getEntity(i);
				counter++;
			}
		}
	}
}
