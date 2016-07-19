package game.entities.mobs;

public class HealthManager {

	private Mob mob;
	private final float MIN_HEALTH = 0;
	private final float MAX_HEALTH = 600;
	private float health = MAX_HEALTH;
	
	public HealthManager(Mob mob) {
		this.mob = mob;
	}
	
	public void increaseHealth(float increaseAmount) {
		health += increaseAmount;
		if (health > MAX_HEALTH) health = MAX_HEALTH;
	}
	
	public void decreaseHealth(float decreaseAmount) {
		health -= decreaseAmount;
		if (health < MIN_HEALTH) health = MIN_HEALTH;
	}
	
	public void resetHealth() {
		health = MAX_HEALTH;
	}
	
	public float getHealth() {
		return health;
	}
	
	public float getMinHealth() {
		return MIN_HEALTH;
	}
	
	public float getMaxHealth() {
		return MAX_HEALTH;
	}
	
	public float getHealthPercentage() {
		return (health/MAX_HEALTH)*100;
	}
}
