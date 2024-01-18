/**
 * @author - Andrew White
 * @description - This is a 2D/3D Game Engine to be able to drag and drop assets into a asset management system while also being able to program collisions and game logic through Java.
 * @version - 0.0.1-SNAPSHOT
 */

package zes.core;

import org.lwjgl.glfw.GLFW;

import zes.core.engine.windows.Window;

public class Main {
	private Window window;
	
	public static void main(String[] args) {
		new Main().init();;
	}
	
	public void init() {
		window = new Window("Quest of Thyrah", 1920, 1080);
	}
	
	public void loop() {
		
	}
}
