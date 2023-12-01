package zes.core.game;

import java.util.ArrayList;

import zes.core.game.gameobjects.Item;

public class Inventory {
	private ArrayList<Item> items;
	private int maxSize;
	
	public Inventory() {
		this(36);
	}
	
	public Inventory(int maxSizeIn) {
		items = new ArrayList<Item>();
		maxSize = maxSizeIn;
	}
	
	public void addItem(Item item) {
		if (items.size() < maxSize) {
			items.add(item);
		}
	}
}
