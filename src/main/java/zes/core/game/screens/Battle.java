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
		
		initTurnOrder();
	}
	
	/**
	 * Using attack speed, sort the entities turn order
	 */
	public void initTurnOrder() {
		for (int i = 0; i < entities.length; i++) {
			for (int j = 0; j < entities.length - 1 - i; j++) {
				
			}
		}
	}
	
	/**
	 * Swaps two entities with eachother
	 * @param e1
	 * @param e2
	 */
	public void swap(Entity e1, Entity e2) {
		Entity temp = e1;
		e1 = e2;
		e2 = temp;
	}
	
}
