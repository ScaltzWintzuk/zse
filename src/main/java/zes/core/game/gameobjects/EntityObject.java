package zes.core.game.gameobjects;

public class EntityObject extends Entity {
	private double health, maxHealth;
	private double mana, maxMana;
	private double damage;
	private double defense;

	public EntityObject(String nameIn) {
		super(nameIn);
	}

	// Getters
	public boolean isDead() { return (health <= 0); }
	public boolean willDie(double damageDealt) { return (health - damageDealt <= 0); }
	public double getHealth() { return health; }
	public double getMaxHealth() { return maxHealth; }
	
	public boolean canUseMana(double value) { return (mana - value <= 0); }
	public double getMana() { return mana; }
	public double getMaxMana() { return maxMana; }
	
	public double getDamage() { return damage; }
	
	public double getDefense() { return defense; }
	
	// Setters
	public void setHealth(double healthIn) { health = healthIn; }
	public void setMaxHealth(double maxHealthIn) { maxHealth = maxHealthIn; }
	
	public void setMana(double manaIn) { mana = manaIn; }
	public void setMaxMana(double maxManaIn) { maxMana = maxManaIn; }
	
	public void setDamage(double damageIn) { damage = damageIn; }
	public void setDefense(double defenseIn) { defense = defenseIn; }
	
}
