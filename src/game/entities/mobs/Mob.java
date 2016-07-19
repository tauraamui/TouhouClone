package game.entities.mobs;

import game.entities.Entity;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.Projectile;
import game.physics.AABB;
import game.stage.Stage;

import java.awt.image.BufferedImage;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Vector2;

public class Mob extends Entity {
	
	protected Stage stage;
	protected BufferedImage Sprite;
	protected boolean dead = false;
	protected Vector2 centerPoint;
	protected Vector2 direction = new Vector2(0,0);
	protected Vector2 velocityGoal = new Vector2(0,0);
	protected MobType mobType = MobType.GENERIC;
	public HealthManager healthManager;
	
	public static enum MobType {
		GENERIC,
		ENEMY,
		PLAYER;
	}

	public Mob(float x, float y, Stage stage) {
		super(x, y);
		this.stage = stage;
		healthManager = new HealthManager(this);
	}
	
	public Vector2 getCenterPoint() {
		return new Vector2(X+(getWidth()/2), Y+(getHeight()/2));
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		checkWithinStageBounds();
	}
	
	@Override
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.RED);
		Vector2 centerPoint = getCenterPoint();
		graphicsContext.fillRect((int)X, (int)Y, getWidth(), getHeight());
	}
	
	public void hit(Projectile projectile, Entity firingEntity) {}
	
	protected float approach(float goal, float current, float dt) {
		float difference = goal - current;
		if (difference > dt)
			return current + dt;
		if (difference < -dt)
			return current - dt;
		
		return goal;
	}
	
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	
	public Vector2 getDirection() {
		return direction;
	}
	
	public MobType getMobType() {
		return mobType;
	}
}