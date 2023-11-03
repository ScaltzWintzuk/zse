package zes.core.engine.controls;

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
	}
	
	public static boolean isButtonPressed(int button) {
		return buttons[button];
	}
	
	// Getters
	public double getX() { return xPos; }
	public double getY() { return yPos; }
	
	public GLFWCursorPosCallback getCursorPositionCallback() { return cursor; }
	public GLFWMouseButtonCallback getMouseButtonCallback() { return mouse; }
	public GLFWScrollCallback getScrollCallback() { return scroll; }
}
