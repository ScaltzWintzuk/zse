package zes.core.engine.controls;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class KeyInput {
	private GLFWKeyCallback keyboard;
	
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	
	// Create Invokers to pay attention to key/mouse presses
	public KeyInput() {
		keyboard = new GLFWKeyCallback() {
			@Override public void invoke(long window, int key, int scanCode, int action, int mods) {
				keys[key] = (action != GLFW.GLFW_PRESS);
				System.out.printf("Hi %d - %d\n", key, action);
			}
		};
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
