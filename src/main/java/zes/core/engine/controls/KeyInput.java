package zes.core.engine.controls;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class KeyInput {
	private GLFWKeyCallback keyboard;
	
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	
	// Create Invokers to pay attention to key presses
	public KeyInput() {
		keyboard = new GLFWKeyCallback() {
			@Override public void invoke(long window, int key, int scanCode, int action, int mods) {
				keys[key] = (action != GLFW.GLFW_PRESS);
				
				System.out.printf("Key Pressed = [Key:%d - Action: %d - Mods: %d]\n", key, action, mods);
			}
		};
	}
	
	public boolean isAnyKeyPressed() {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i]) { return true; }
		}
		
		return false;
	}
	
	public static boolean isKeyPressed(int key) {
		return keys[key];
	}
	
	public void destroy() {
		keyboard.free();
	}
	
	// Getters
	public GLFWKeyCallback getKeyCallback() { return keyboard; }
}
