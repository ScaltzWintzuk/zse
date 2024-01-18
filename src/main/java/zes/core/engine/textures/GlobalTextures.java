package zes.core.engine.textures;


public class GlobalTextures {
	public static final GameTexture WOLF = new GameTexture("Wolf", "resources/textures/wolf.PNG");
	public static final GameTexture DOG = new GameTexture("Dog", "resources/textures/dog.PNG");
	
	/**
	 * Registers all the Textures onto to TextureSystem to be synchronized with the GlobalTexture class
	 * @param system
	 */
	public static void registerTextures(TextureSystem system) {
		system.registerTexture(WOLF);
		system.registerTexture(DOG);
	}
}
