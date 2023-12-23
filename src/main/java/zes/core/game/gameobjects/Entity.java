package zes.core.game.gameobjects;

import zes.core.engine.shapes.Rectangle;
import zes.core.engine.textures.GameTexture;
import zes.core.engine.utils.ZColors;

public class Entity extends Object implements Comparable<Entity> {
	// GUI related things to the Entity
	private GameTexture[] entityTextures;
	private int currentFrame;
	
	// Variables associated to an Entity
	private String name;
	private int id;
	
	private float xPos, yPos;
	
	// TEMPROARY STUFF
	private Rectangle hitbox;
	
	public Entity(String nameIn) {
		name = nameIn;
		
		entityTextures = new GameTexture[1]; // 1 Image, this is not animated...
		
		// Center of screen if it uses GL11's library
		xPos = 0;
		yPos = 0;
		
		currentFrame = 0;
		hitbox = new Rectangle(ZColors.BLUE, xPos, yPos, 0.33f, 0.33f); // REMOVE THIS LATER
	}
	
	public void draw() {
		
	}
	
	@Override public int compareTo(Entity entityIn) {
		return this.name.compareTo(entityIn.name);
	}
	
	// Getters
	public String getName() { return name; }
	public int getId() { return id; }
	
	// Setters
	public void setName(String nameIn) { name = nameIn; }
	
	// Animations?
	public void incAnimationFrame() {
		if (currentFrame >= entityTextures.length - 1) { 
			currentFrame = 0;
		}
		else {
			currentFrame++;
		}
	}
	
	public void decAnimationFrame() {
		if (currentFrame > 0) {
			currentFrame--;
		}
		else {
			currentFrame = 0;
		}
	}
	
	public boolean setAnimationFrame(int frameIn) {
		if (frameIn >= entityTextures.length - 1) {
			currentFrame = frameIn;
			return true;
		}
		return false;
	}
}
