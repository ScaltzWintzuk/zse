package zes.core.engine.controls;

import java.util.Arrays;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class MouseInput {
	// Callbacks
	private GLFWCursorPosCallback cursor;
	private GLFWMouseButtonCallback mouse;
	private GLFWScrollCallback scroll;
	
	// Values to keep track of
	private static double xPos, yPos;
	private static double xScroll, yScroll;
	
	// Buttons that are being pressed
	private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	
	/**
	 * Upon instantiation, MouseInput pays attention to the cursor's position, the mouse clicks, and the mouse scroll with invokers.
	 */
	public MouseInput() {
		// Updates the xPos and yPos fields in the MouseInput class every time the cursor moves.
		cursor = new GLFWCursorPosCallback() {
			@Override public void invoke(long window, double x, double y) {
				xPos = x;
				yPos = y;
			}
		};
		
		// Updates the buttons array when an action happens to the mouse buttons
		mouse = new GLFWMouseButtonCallback() {
			@Override public void invoke(long window, int button, int action, int mods) {
				buttons[button] = (action == GLFW.GLFW_RELEASE);
				
				if (isButtonPressed(0)) {
					printStats();
				}
			}
		};
		
		// Every time the scroll wheel is used, add/subtract it directly to the xScroll and yScroll values. The change in these values is how we know the scroll amount.
		scroll = new GLFWScrollCallback() {
			@Override public void invoke(long window, double offsetX, double offsetY) {
				xScroll += offsetX;
				yScroll += offsetY;
			}
		};
	}
	
	/**
	 * Prints the position of the mouse cursor
	 */
	public void printStats() {
		System.out.printf("[%.2f, %.2f]\n", xPos, yPos);
	}
	
	/**
	 * If any mouse buttons are being pressed, return false. Otherwise, return true.
	 * @return
	 */
	public static boolean isAnyButtonsPressed() {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i]) { return true; }
		}
		return false;
	}
	
	/**
	 * Checks to see if a specific mouse button is being pressed within the buttons array field.
	 * @param button
	 * @return
	 */
	public static boolean isButtonPressed(int button) {
		return buttons[button];
	}
	
	/**
	 * Frees up the memory for the cursor position, mouse button, and scrolling callbacks.
	 */
	public void destroy() {
		cursor.free();
		mouse.free();
		scroll.free();
	}
	
	// Getters
	public double getX() { return xPos; }
	public double getY() { return yPos; }
	
	public GLFWCursorPosCallback getCursorPositionCallback() { return cursor; }
	public GLFWMouseButtonCallback getMouseButtonCallback() { return mouse; }
	public GLFWScrollCallback getScrollCallback() { return scroll; }
}
