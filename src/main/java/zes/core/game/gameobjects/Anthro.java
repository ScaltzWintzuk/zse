package zes.core.game.gameobjects;

import zes.core.engine.textures.GameTexture;

public class Anthro extends Entity {
	private GameTexture[] head;
	private GameTexture[] legs;
	private GameTexture[] arms;
	
	private int headFrame, legsFrame, armsFrame;

	public Anthro(String nameIn) {
		super(nameIn);
		
		head = new GameTexture[1];
		legs = new GameTexture[1];
		arms = new GameTexture[1];
		
		headFrame = 0;
		legsFrame = 0;
		armsFrame = 0;
	}
	
	// Head
	
	// Arms and Legs
	public void incWalkAnimation() {
		
	}
	
	public void decWalkAnimation() {
		
	}
	
	public void setWalkAnimation() {
		
	}

}
