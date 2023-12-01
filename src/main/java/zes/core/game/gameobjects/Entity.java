package zes.core.game.gameobjects;

import zes.core.engine.textures.GameTexture;

public class Entity implements Comparable<Entity> {
	// GUI related things to the Entity
	private GameTexture[] entityTextures;
	
	// Variables associated to an Entity
	private String name;
	private int id;
	
	public Entity(String nameIn) {
		name = nameIn;
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
}
