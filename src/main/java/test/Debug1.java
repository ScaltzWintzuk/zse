package test;

import zes.core.engine.shapes.Circle;
import zes.core.engine.shapes.Rectangle;
import zes.core.engine.utils.ZColors;
import zes.core.engine.windows.Screen;

public class Debug1 {
	// This class works as a debugger to display statistics
	public static Screen debugScreen;
	
	public static Screen mainMenuScreen;
	
	/**
	 * Tests a ScreenStack to see if it works
	 * @return
	 */
	public static void debugScreens() {
		debugScreen = new Screen();
		
		debugScreen.addShape(new Circle(0.33f));
		debugScreen.addShape(new Rectangle(ZColors.PURPLE, 0.5f, 0.5f, 0.5f, 0.5f));
		debugScreen.addShape(new Rectangle(ZColors.GREEN, -0.66f, -0.66f, 0.33f, 0.33f));
	}

	/**
	 * Testing main menu screen and buttons using the Rectangle class
	 * @return
	 */
	@Deprecated public static boolean menuTest() {
		mainMenuScreen = new Screen();
		
		debugScreen.addShape(new Rectangle(ZColors.WHITE, -0.25f, -0.25f, 0.5f, 0.125f));
		
		return false;
	}
}
