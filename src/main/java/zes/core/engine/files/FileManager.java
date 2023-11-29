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
	private static final File DEFAULT_WORLD = new File("defaultworld.zwrld");
	
	public FileManager() {
		this(DEFAULT_WORLD);
	}
	
	public FileManager(File world) {
		currentFile = world;
	}
	
	public static void updateCurrentFile(String name) {
		currentFile = new File(name);
	}
	
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
	
	public static void load() {
		try {
			reader = new Scanner(currentFile);
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
