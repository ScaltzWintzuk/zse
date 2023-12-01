package zes.core.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	
	/**
	 * Adds an item based on the maxSize of the inventory, if it does not exceed the maxSize, then it adds the item to the ArrayList
	 * @param item
	 */
	public boolean addItem(Item item) {
		if (items.size() < maxSize) {
			items.add(item);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an item and returns true or false if it was successful
	 * @param itemToRemove
	 * @return
	 */
	public boolean removeItem(Item itemToRemove) {
		for (Item i : items) {
			if (i.equals(itemToRemove)) {
				items.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void sortItemsAlphabetical() {
		items.sort(Comparator.naturalOrder());
	}
	
	
}
