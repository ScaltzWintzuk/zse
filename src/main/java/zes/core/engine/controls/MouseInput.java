package zes.core.engine.controls;

import java.util.Arrays;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class MouseInput {
	private GLFWCursorPosCallback cursor;
	private GLFWMouseButtonCallback mouse;
	private GLFWScrollCallback scroll;
	
	private static double xPos, yPos;
	private static double xScroll, yScroll;
	
	private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	
	// Place invokers here to pay attention to mouse movement, position, and button presses
	public MouseInput() {
		cursor = new GLFWCursorPosCallback() {
			@Override public void invoke(long window, double x, double y) {
				xPos = x;
				yPos = y;
			}
		};
		
		mouse = new GLFWMouseButtonCallback() {
			@Override public void invoke(long window, int button, int action, int mods) {
				buttons[button] = (action != GLFW.GLFW_RELEASE);
				
				if (isButtonPressed(0)) {
					printStats();
				}
			}
		};
		
		scroll = new GLFWScrollCallback() {
			@Override public void invoke(long window, double offsetX, double offsetY) {
				xScroll += offsetX;
				yScroll += offsetY;
			}
		};
	}
	
	public void printStats() {
		System.out.printf("[%.2f, %.2f]\n", xPos, yPos);
	}
	
	public static boolean isAnyButtonsPressed() {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i]) { return true; }
		}
		return false;
	}
	
	public static boolean isButtonPressed(int button) {
		return buttons[button];
	}
	
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
