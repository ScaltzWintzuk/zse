package zes.core.game.gameobjects;

public class Item extends Entity {
	private String description;
	
	private int buyPrice, sellPrice;
	
	public Item(String nameIn, String descriptionIn, int buyPriceIn, int sellPriceIn) {
		super(nameIn);
		description = descriptionIn;
		buyPrice = buyPriceIn;
		sellPrice = sellPriceIn;
	}
	
	@Override public String toString() {
		return String.format("Type: %d, Name: %s, Description: %s, Buy: %.2f, Sell: %.2f\n", this.getClass().getName(), getName(), description, buyPrice, sellPrice);
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
