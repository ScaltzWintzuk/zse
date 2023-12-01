package zes.core.engine.controls;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import zes.core.engine.files.FileManager;
import zes.core.engine.shapes.Circle;
import zes.core.engine.shapes.Rectangle;
import zes.core.engine.utils.ZKeyboardConstants;

public class ControllerManager {
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
	@Deprecated public static void checkMovements(Rectangle rect, Rectangle rect2) throws InterruptedException {
		if (!isColliding(rect, rect2)) {
			if (KeyInput.isKeyPressed(ZKeyboardConstants.UP_MOVE)) {
				//rect.setYPos(rect.getYPos() + 1);
				rect.incYTest();
				
			}
			
			if (KeyInput.isKeyPressed(ZKeyboardConstants.DOWN_MOVE)) {
				//rect.setYPos(rect.getYPos() - 1);
				rect.decYTest();
			}
			
			if (KeyInput.isKeyPressed(ZKeyboardConstants.LEFT_MOVE)) {
				//rect.setXPos(rect.getXPos() - 1);
				rect.decXTest();
			}
			
			if (KeyInput.isKeyPressed(ZKeyboardConstants.RIGHT_MOVE)) {
				//rect.setXPos(rect.getXPos() + 1);
				rect.incXTest();
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
		else {
			
		}
	}
	
	public static boolean isColliding(Rectangle rect, Rectangle rect2) {
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
