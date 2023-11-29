package zes.core.engine.windows;

import java.nio.IntBuffer;

import org.joml.Vector3f;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import zes.core.engine.controls.ControllerManager;
import zes.core.engine.controls.KeyInput;
import zes.core.engine.controls.MouseInput;
import zes.core.engine.files.FileManager;
import zes.core.engine.graphics.Shader;
import zes.core.engine.shapes.Circle;
import zes.core.engine.shapes.Rectangle;
import zes.core.engine.utils.Constants;
import zes.core.engine.utils.ZColors;
import zes.core.engine.utils.ZKeyboardConstants;
import zes.core.engine.utils.ZStack;

public class Window {
	// All Callbacks
	private GLFWWindowSizeCallback sizeCallback;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	private Shader shader;
	
	// The Window Memory Address itself
	private long window;
	
	// Window Attributes
	private String title;
	private int width, height;
	
	// Window Booleans
	private boolean isResized;
	private boolean isFullScreen;
	
	private Vector3f background = new Vector3f(0, 0, 0);
	private int[] windowPosX = new int[1];
	private int[] windowPosY = new int[1];
	
	private ZStack<Screen> screens; // Keeps track of the current screen and previous screens
	
	// Delta Time Stuff
	private int frames;
	private long time;
	
	public Window() {
		this("Game", 1920, 1080);
	}
	
	public Window(String titleIn) {
		
		//this(titleIn, );
	}
	
	public Window(int widthIn, int heightIn) {
		this("Game", widthIn, heightIn);
	}
	
	public Window(String titleIn, int widthIn, int heightIn) {
		title = titleIn;
		width = widthIn;
		height = heightIn;
		
		//screen = new Screen();
		screens = new ZStack<Screen>();
		screens.push(new Screen());
		
		//shader = new Shader(Constants.VERTEX_FILE_PATH, Constants.FRAGMENT_FILE_PATH);
		
		//rectangle = new Rectangle(ZColors.YELLOW, -0.66f, -0.66f, 0.33f, 0.33f);
		//rect2 = new Rectangle(ZColors.PURPLE, 0.5f, 0.5f, 0.5f, 0.5f);
		//circle = new Circle();
		
		// Main Game Mechanics in the current window
		init();
		loop();
		
		// When loop is closed out, close the window
		close();
	}
	
	public void init() {
		keyInput = new KeyInput();
		mouseInput = new MouseInput();
		
		// File Manager stuff
		try {
			FileManager.updateCurrentFile("test.zwrld");
			//FileManager.load();
		}
		catch (Exception e) {
			
		}
		
		// TEMPORARY SCREEN STUFF
		getCurrentScreen().addShape(new Rectangle(ZColors.YELLOW, -0.66f, -0.66f, 0.33f, 0.33f));
		
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
		
		window = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
		
		if (window == MemoryUtil.NULL) {
			throw new RuntimeException("Failed to create the GLFW Window");
		}
		
		// Invoker using a lambda operator
		GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
				GLFW.glfwSetWindowShouldClose(window, true);
			}
		});
		
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			GLFW.glfwGetWindowSize(window, pWidth, pHeight);
			
		    GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		    
		    GLFW.glfwSetWindowPos(window, vidMode.width() - pWidth.get(0) / 2, vidMode.height() - pHeight.get(0) / 2);
		}
		
		GLFW.glfwMakeContextCurrent(window);
		
		GLFW.glfwSwapInterval(1);
		
		initCallbacks();
		
		GLFW.glfwShowWindow(window);
		
		GLFWVidMode mode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, mode.width() / 3, mode.height() / 3);
		//GLFW.glfwSetWindowMonitor(window, NULL, (max_width/2)-(width/2), (max_hieght/2) - (height/2), width, height, GLFW_DONT_CARE)
	}
	
	public void loop() {
		GL.createCapabilities();
		
		setBackgroundColor(0, 0, 0);
		
		// Renders the window until the window is closed or ESCAPE is hit.
		while (!GLFW.glfwWindowShouldClose(window) && !KeyInput.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			try {
				// Rendering stuff
				//getCurrentScreen().draw();
				update();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			GLFW.glfwSwapBuffers(window);
			GLFW.glfwPollEvents();
			
		}
		
		destroy();
	}
	
	
	public void initCallbacks() {
		// updates w and h, important for rendering assets correctly with different window sizes
		sizeCallback = new GLFWWindowSizeCallback() {
			@Override public void invoke(long window, int w, int h) {
				width = w;
				height = h;
				
				isResized = true;
			}
		};
		
		// Keyboard Callbacks
		GLFW.glfwSetKeyCallback(window, keyInput.getKeyCallback());
		
		// Mouse Callbacks
		GLFW.glfwSetCursorPosCallback(window, mouseInput.getCursorPositionCallback());
		GLFW.glfwSetMouseButtonCallback(window, mouseInput.getMouseButtonCallback());
		GLFW.glfwSetScrollCallback(window, mouseInput.getScrollCallback());
		
		// Window resize callbacks
		GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
	}
	
	/**
	 * Frees all of the callbacks and destroys the window
	 */
	public void close() {
		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);
		
		GLFW.glfwTerminate();
		try {
			GLFW.glfwSetErrorCallback(null).free();
		}
		catch (Exception e) {
			
		}
		
		// Save here
		FileManager.save(getCurrentScreen());
		
		System.out.println("Closing...");
	}
	
	/**
	 * Updates the buffer and 
	 */
	public void update() {
		if (isResized) {
			GL11.glViewport(0, 0, width, height);
			isResized = false;
		}
		
		GL11.glClearColor(background.x, background.y, background.z, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		try {
			getCurrentScreen().draw();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// Gets rid of the stuff from the previous frame
		GLFW.glfwPollEvents();
		
		frames++;
		
		if (System.currentTimeMillis() > time + 1000) { 
			GLFW.glfwSetWindowTitle(window, (title + " FPS: " + frames));
			time = System.currentTimeMillis();
			frames = 0;
		}
	}
	
	public Screen getCurrentScreen() { return screens.peek(); }
	
	public Window getWindow() { return this; }
	
	public void setBackgroundColor(float r, float g, float b) {
		GL11.glClearColor(r, g, b, 0.0f);
		//background.set(r, b, b);
	}
	
	public void destroy() {
		keyInput.destroy();
		mouseInput.destroy();
		
		GLFW.glfwWindowShouldClose(window);
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
	
	public void setFullScreen(boolean isFullScreenIn) {
		isFullScreen = isFullScreenIn;
		isResized = true;
		
		if (isFullScreen) {
			GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
			GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
		}
		else {
			GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, 0);
		}
	}

}
