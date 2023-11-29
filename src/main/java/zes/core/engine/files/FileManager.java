package zes.core.engine.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import zes.core.engine.shapes.Circle;
import zes.core.engine.shapes.Rectangle;
import zes.core.engine.windows.Screen;

public class FileManager {
	private static Scanner reader;
	private static PrintWriter writer;
	
	private static File currentFile;
	private static final String FILE_EXTENSION = ".zwrld";
	private static final File DEFAULT_WORLD = new File("defaultworld" + FILE_EXTENSION);
	
	public FileManager() {
		this(DEFAULT_WORLD);
	}
	
	public FileManager(File world) {
		currentFile = world;
	}
	
	/**
	 * Updates the current file that the FileManager is looking at into a new location
	 * @param name
	 */
	public static void updateCurrentFile(String name) {
		currentFile = new File("resources/" + name + FILE_EXTENSION);
	}
	
	/**
	 * Saves the game screen to the currentFile, this is defaulted to look at defaultworld.zrld
	 * @param screen
	 */
	public static void save(Screen screen) {
		try {
			writer = new PrintWriter(currentFile);
			
			for (int i = 0; i < screen.getShapes().size(); i++) {
				if (screen.getShape(i) instanceof Rectangle) {
					Rectangle r = (Rectangle) screen.getShape(i);
					writer.printf("%s %.2f %.2f %.2f %.2f\n", "rectangle", r.getXPos(), r.getYPos(), r.getWidth(), r.getHeight());
				}
				
				if (screen.getShape(i) instanceof Circle) {
					Circle c = (Circle) screen.getShape(i);
					writer.printf("%s %.2f\n", "circle", c.getRadius());
				}
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load(Screen screen) {
		/*
		try {
			reader = new Scanner(currentFile);
			
			while (reader.hasNextLine()) {
				switch (reader.next().trim().toLowerCase()) {
				case "rectangle": screen.addShape(new Rectangle(reader.nextFloat(), reader.nextFloat(), reader.nextFloat(), reader.nextFloat())); break;
				//case "circle": screen.addShape(); break;
				default: break;
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
	
}
