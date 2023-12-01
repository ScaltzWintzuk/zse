package zes.core.game.gameobjects;

public class Item extends Entity {
	private String description;
	
	private int buyPrice, sellPrice;
	
	public Item(String descriptionIn, int buyPriceIn, int sellPriceIn) {
		description = descriptionIn;
		buyPrice = buyPriceIn;
		sellPrice = sellPriceIn;
	}
	
	// Getters
	public String getDescription() { return description; }
	public int getBuyprice() { return buyPrice; }
	public int getSellPrice() { return sellPrice; }
	
	// Setters
	public void setDescription(String descriptionIn) { description = descriptionIn; }
	public void setBuyPrice(int buyPriceIn) { buyPrice = buyPriceIn; }
	public void setSellPrice(int sellPriceIn) { sellPrice = sellPriceIn; }
}
