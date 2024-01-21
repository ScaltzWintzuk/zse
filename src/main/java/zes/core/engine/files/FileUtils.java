package zes.core.engine.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileUtils {
	public static String loadAsStringShader(String path) throws FileNotFoundException {
		/*
		StringBuilder result = new StringBuilder();
		
		try (Scanner reader = new Scanner(path)) {
			String line = "";
			//while ((line = reader.nextLine()) != null) {
			while (reader.hasNextLine()) {
				result.append(line);
				//append("\n");
			}
		}
		
		return result.toString();
		*/
		
		return path;
	}
	
	public static String loadAsStringWorldFile(String path) throws FileNotFoundException {
		return "";
	}
	
	@Deprecated public static String debugBuildString(String path) throws FileNotFoundException {
		return path;
	}
}
