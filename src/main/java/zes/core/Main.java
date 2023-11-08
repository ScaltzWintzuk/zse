package zes.core;

import zes.core.engine.windows.Window;

public class Main {
	private Window window;
	
	public static void main(String[] args) {
		new Main().init();;
	}
	
	public void init() {
		window = new Window("Quest of Thyrah", 1920, 1080);
	}
	
	public void loop() {
		
	}
}
