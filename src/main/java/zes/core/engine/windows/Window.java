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

import zes.core.engine.controls.KeyInput;
import zes.core.engine.controls.MouseInput;

public class Window {
	private GLFWWindowSizeCallback sizeCallback;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	
	private long window;
	
	private String title;
	private int width, height;
	
	private boolean isResized;
	private boolean isFullScreen;
	
	private Vector3f background = new Vector3f(0, 0, 0);
	private int[] windowPosX = new int[1];
	private int[] windowPosY = new int[1];
	
	private int frames;
	private long time;
	
	public Window() {
		this("Game", 1920, 1080);
	}
	
	public Window(int widthIn, int heightIn) {
		this("Game", widthIn, heightIn);
	}
	
	public Window(String titleIn, int widthIn, int heightIn) {
		title = titleIn;
		width = widthIn;
		height = heightIn;
		
		init();
		loop();
		close();
	}
	
	public void init() {
		keyInput = new KeyInput();
		mouseInput = new MouseInput();
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
	}
	
	public void loop() {
		GL.createCapabilities();
		
		GL11.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		// Renders the window until the window is closed or ESCAPE is hit.
		while (!GLFW.glfwWindowShouldClose(window)) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			
			GLFW.glfwSwapBuffers(window);
			
			GLFW.glfwPollEvents();
		}
	}
	
	public void initCallbacks() {
		sizeCallback = new GLFWWindowSizeCallback() {
			@Override public void invoke(long window, int w, int h) {
				width = w;
				height = h;
				
				isResized = true;
			}
		};
		
		// Keyboard callbacks
		GLFW.glfwSetKeyCallback(window, keyInput.getKeyCallback());
		
		// Mouse callbacks
		GLFW.glfwSetCursorPosCallback(window, mouseInput.getCursorPositionCallback());
		GLFW.glfwSetMouseButtonCallback(window, mouseInput.getMouseButtonCallback());
		GLFW.glfwSetScrollCallback(window, mouseInput.getScrollCallback());
		
		// Window resize callbacks
		GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
		
	}
	
	public void close() {
		Callbacks.glfwFreeCallbacks(window);
		GLFW.glfwDestroyWindow(window);
		
		GLFW.glfwTerminate();
		GLFW.glfwSetErrorCallback(null).free();
	}
	
	public void update() {
		if (isResized) {
			GL11.glViewport(0, 0, width, height);
			isResized = false;
		}
		
		GL11.glClearColor(background.x, background.y, background.z, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		// Gets rid of the stuff from the previous frame
		GLFW.glfwPollEvents();
		
		frames++;
		
		if (System.currentTimeMillis() > time + 1000) { 
			GLFW.glfwSetWindowTitle(window, (title + " FPS: " + frames));
			time = System.currentTimeMillis();
			frames = 0;
		}
	}
	
	public void setBackgroundColor(float r, float g, float b) {
		background.set(r, b, b);
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
