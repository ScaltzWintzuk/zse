package zes.core.engine.windows;

import java.util.ArrayList;

import zes.core.engine.shapes.Shape;
import zes.core.engine.textures.GameTexture;

public class Screen {
	private ArrayList<GameTexture> textures;
	
	// This is temporary, remove this later
	private ArrayList<Shape> shapes;
	
	public Screen() {
		textures = new ArrayList<GameTexture>();
		shapes = new ArrayList<Shape>();
	}
	
	/**
	 * Draws the entire contents of the current screen being displayed on the window. This will be called every time a frame is being rendered
	 */
	public void draw() {
		// Draws every shape in the Shapes array, soon this will be changed to look at a GameTexture object
		for (Shape s : shapes) {
			s.draw();
		}
	}
}
