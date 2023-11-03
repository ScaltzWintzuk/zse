package zes.core.game;

public class Item {
	private String name;
	private String description;
	
	public Item(String nameIn, String descriptionIn) {
		name = nameIn;
		description = descriptionIn;
	}
	
	// Getters
	public String getName() { return name; }
	public String getDescription() { return description; }
	
	// Setters
}
