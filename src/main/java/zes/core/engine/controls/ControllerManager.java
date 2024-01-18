package zes.core.engine.controls;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import zes.core.engine.files.FileManager;
import zes.core.engine.shapes.Circle;
import zes.core.engine.shapes.Rectangle;
import zes.core.engine.utils.ZKeyboardConstants;

public class ControllerManager {
	public static void checkControls(Rectangle rect1, Rectangle rect2) throws InterruptedException {
		if (!isColliding(rect1, rect2)) {
			// Checks movement controls
			checkMovements(rect1, rect2);
			
			// Checks if 1 or 2 are pressed, 1 saves the world, 2 loads the world, this will be removed later 
			checkKeyStuff();
		}
		else {
			System.err.println("Both Rectangles are Intersecting");
		}
	}
	
	/**
	 * Remove this later
	 */
	@Deprecated public static void checkKeyStuff() {
		/*
		if (KeyInput.isKeyPressed(ZKeyboardConstants.ONE)) {
			FileManager.save();
		}
		*/
		
		if (KeyInput.isKeyPressed(ZKeyboardConstants.TWO)) {
			//FileManager.load();
		}
	}
	
	/**
	 * Will change this, but for now this checks the movements for the rectangle based on WASD
	 * @param rect
	 * @throws InterruptedException 
	 */
	@Deprecated public static void checkMovements(Rectangle rect1, Rectangle rect2) throws InterruptedException {
		if (KeyInput.isKeyPressed(ZKeyboardConstants.UP_MOVE)) {
			rect1.incYTest();
		}
			
		if (KeyInput.isKeyPressed(ZKeyboardConstants.DOWN_MOVE)) {
			rect1.decYTest();
		}
			
		if (KeyInput.isKeyPressed(ZKeyboardConstants.LEFT_MOVE)) {
			rect1.decXTest();
		}
			
		if (KeyInput.isKeyPressed(ZKeyboardConstants.RIGHT_MOVE)) {
			rect1.incXTest();
		}
			
		if (KeyInput.isKeyPressed(ZKeyboardConstants.UP_ARROW)) {
			rect2.incYTest();
		}
			
		if (KeyInput.isKeyPressed(ZKeyboardConstants.DOWN_ARROW)) {
			rect2.decYTest();
		}
			
		if (KeyInput.isKeyPressed(ZKeyboardConstants.LEFT_ARROW)) {
			rect2.decXTest();
		}
			
		if (KeyInput.isKeyPressed(ZKeyboardConstants.RIGHT_ARROW)) { 
			rect2.incXTest();
		}
	}
	
	public static boolean isColliding(Rectangle rect, Rectangle rect2) {
		//assuming that each shape xPos and Ypos are in the center of the shape.
		//aka: xPos +/- width/2 should give the xpos of the vertical sides. and yPos +/- height/2 should give the Ypos of the horizontal walls
		//assuming the posY is at the top of the screen and pos X is on the right of the screen

		//check if the x axis of both shapes are coliding.
		if (rect.getXPos() + (rect.getWidth()/2) >= rect2.getXPos() - rect2.getWidth()/2 || rect2.getXPos() + (rect2.getWidth()/2) >= rect.getXPos() - rect.getWidth()/2) {
			//check if the y axis of both shapes are colliding.
			if ((rect.getYPos() + rect.getHeight()/2 <= rect2.getYPos() + rect2.getHeight()/2) || (rect2.getYPos() + rect2.getHeight()/2 <= rect.getYPos() + rect.getHeight()/2)){
				return true;
			}
			else{
				return false;
			}

		}
		else{
			return false;
		}
	}
	
	public static boolean isCollidingT(Rectangle rect, Rectangle rect2) {
		if ((rect.getXPos() + rect.getWidth() >= rect2.getXPos() && rect.getXPos() < rect2.getXPos() + rect2.getWidth() && ((rect.getYPos() >= rect2.getYPos() && rect.getYPos() < rect2.getYPos() + rect2.getHeight())  || (rect.getYPos() + rect.getHeight() <= rect2.getYPos() + rect2.getHeight() && rect.getYPos() + rect.getHeight() > rect2.getYPos()))) || (rect2.getXPos() + rect2.getWidth() >= rect.getXPos() && rect2.getXPos() < rect.getXPos() + rect.getWidth() && ((rect2.getYPos() >= rect.getYPos() && rect2.getYPos() < rect.getYPos() + rect.getHeight())  || (rect2.getYPos() + rect2.getHeight() <= rect.getYPos() + rect.getHeight() && rect2.getYPos() + rect2.getHeight() > rect.getYPos())))) {
			
			System.out.println("x1:" + rect.getXPos() +" y1:" + rect.getYPos()+ " x2:"+ rect2.getXPos()+ " y2:"  + rect2.getYPos());
			// Yellow is left purple is right
			if(rect.getXPos() + rect.getWidth() >= rect2.getXPos() && rect.getXPos() + rect.getWidth() < rect2.getXPos() + rect2.getWidth()) {
				rect.setXPos(rect2.getXPos() - rect.getWidth() - 0.01f);
			}
			
			// Purple on left yellow on right
			else {
				rect2.setXPos(rect.getXPos() - rect2.getWidth() - 0.01f);
				System.out.println("ELSE");
			}
			
			return true;
		}
		else if ((rect.getYPos() + rect.getHeight() >= rect2.getYPos() && rect.getYPos() < rect2.getYPos() + rect2.getHeight() && ((rect.getXPos() >= rect2.getXPos() && rect.getXPos() < rect2.getXPos() + rect2.getWidth())  || (rect.getXPos() + rect.getWidth() <= rect2.getXPos() + rect2.getWidth() && rect.getXPos() + rect.getWidth() > rect2.getXPos()))) || (rect2.getYPos() + rect2.getHeight() >= rect.getYPos() && rect2.getYPos() < rect.getYPos() + rect.getHeight() && ((rect2.getXPos() >= rect.getXPos() && rect2.getXPos() < rect.getXPos() + rect.getWidth())  || (rect2.getXPos() + rect2.getWidth() <= rect.getXPos() + rect.getWidth() && rect2.getXPos() + rect2.getWidth() > rect.getXPos())))) {
			System.out.println("Y COLLIDE");
		}
		// DO NOT FORGET THESE IN THE FIRST IF STATEMENT SCOPE
		/*
			if(rect.getXPos() <= rect2.getXPos() + rect2.getWidth()) {
				rect.setXPos(-0.03f + rect.getXPos());
				System.out.println("IF");
			
			}
			else {
				rect2.setXPos(0.03f + rect2.getXPos());
				System.out.println("ELSE");
			}
		 */
		/*else if(rect2.getXPos() + rect2.getWidth() >= rect.getXPos() && rect2.getXPos() < rect.getXPos() + rect.getWidth() && ((rect2.getYPos() >= rect.getYPos() && rect2.getYPos() < rect.getYPos() + rect.getHeight())  || (rect2.getYPos() + rect2.getHeight() <= rect.getYPos() + rect.getHeight() && rect2.getYPos() + rect2.getHeight() > rect.getYPos()))) {
			rect2.setXPos(0.03f + rect2.getXPos());
			return true;
		}*/
		return false;
	}
	
	public boolean checkCollisions() {
		return false;
	}
	
	
}
