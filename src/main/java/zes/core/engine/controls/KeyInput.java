package zes.core.engine.controls;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class KeyInput {
	private GLFWKeyCallback keyboard; // key input callback
	
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST]; // boolean array to keep track of every key
	
	/**
	 * Upon creation, KeyInput will create an invoker to pay attention to all keystrokes and elongated keystrokes.
	 */
	public KeyInput() {
		keyboard = new GLFWKeyCallback() {
			@Override public void invoke(long window, int key, int scanCode, int action, int mods) {
				keys[key] = (action != GLFW.GLFW_PRESS);
				
				System.out.printf("Key Pressed = [Key:%d - Action: %d - Mods: %d]\n", key, action, mods);
			}
		};
	}
	
	/**
	 * Returns true or false if any keys are being pressed at the moment
	 * @return
	 */
	public boolean isAnyKeyPressed() {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) { return true; }
		}
		
		return false;
	}
	
	/**
	 * Checks to see if a specific GLFW input key is being pressed
	 * @param key
	 * @return
	 */
	public static boolean isKeyPressed(int key) {
		return keys[key];
	}
	
	/**
	 * Frees up the memory for the keyboard callback -- This extinguishes the keyboard's invoker
	 */
	public void destroy() {
		keyboard.free();
	}
	
	// Getters
	public GLFWKeyCallback getKeyCallback() { return keyboard; }
}
