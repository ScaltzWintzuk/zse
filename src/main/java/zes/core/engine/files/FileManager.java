package zes.core.engine.files;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {
	private static Scanner reader;
	private static FileWriter writer;
	
	private static final File DEFAULT_WORLD = new File("defaultworld.zwrld");
	
	public FileManager() {
		this(DEFAULT_WORLD);
	}
	
	public FileManager(File world) {
		
	}
	
	public static void save() {
		
	}
	
	public static void load() {
		
	}
	
}
