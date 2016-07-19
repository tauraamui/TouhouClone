package game.entities.projectiles;

import game.Game;
import game.entities.Entity;
import game.stage.Stage;
import utils.Vector2;

public class RotatingBullet extends Bullet {
	
	protected Vector2 spawnLocation = new Vector2();
	protected double minRotation = 0.004;
	protected double maxRotation = 0.005;
	protected double rotationDiffAmount = 0.0001;
	protected double rotation = minRotation;
	protected boolean rotationAmountInc = true;
	protected double angle;

	public RotatingBullet(float x, float y, Vector2 direction, Entity firingEntity, double angle) {
		super(x, y, direction, firingEntity);
		this.angle = angle;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		direction.rotate(angle);
//		spawnLocation.X += direction.X * deltaTime;
//		spawnLocation.Y += direction.Y * deltaTime;
	}
}
