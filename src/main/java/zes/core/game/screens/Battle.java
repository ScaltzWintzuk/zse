package zes.core.game.screens;

import zes.core.game.gameobjects.Entity;
import zes.core.game.gameobjects.EntityObject;
import zes.core.game.gameobjects.Team;

public class Battle {
	private Team playerTeam;
	private Team enemyTeam;
	
	private EntityObject[] entities;
	
	public Battle(Team playerTeamIn, Team enemyTeamIn) {
		playerTeam = playerTeamIn;
		enemyTeam = enemyTeamIn;
		
		entities = new EntityObject[playerTeam.getSize() + enemyTeam.getSize()];
		
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
		
		//initTurnOrder();
	}
	
	/**
	 * Using attack speed, sort the entities turn order
	 */
	public void initTurnOrder() {
		for (int i = 0; i < entities.length; i++) {
			for (int j = 0; j < entities.length - i; j++) {
				//if (entities[j].getSpeed() > entities[j + 1].getSpeed()) {
					swap(entities[j], entities[j + 1]);
				//}
			}
		}
	}
	
	/**
	 * Checks if the enemy team is all dead by seeing if their total health falls equal to or lower than 0
	 * @return
	 */
	public boolean checkWin() {
		double totalHealth = 0;
		for (int i = 0; i < enemyTeam.getSize(); i++) {
			totalHealth += enemyTeam.getEntity(i).getHealth();
		}
		return (totalHealth <= 0);
	}
	
	/**
	 * Checks if the player team is all dead by seeing if their total health falls equal to or lower than 0
	 * @return
	 */
	public boolean checkLoss() {
		double totalHealth = 0;
		for (int i = 0; i < playerTeam.getSize(); i++) {
			totalHealth += playerTeam.getEntity(i).getHealth();
		}
		return (totalHealth <= 0);
	}
	
	/**
	 * Swaps two entities with each other
	 * @param e1
	 * @param e2
	 */
	public void swap(Entity e1, Entity e2) {
		Entity temp = e1;
		e1 = e2;
		e2 = temp;
	}
	
}
