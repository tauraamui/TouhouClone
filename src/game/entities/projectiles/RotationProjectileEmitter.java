package game.entities.projectiles;

import game.Game;
import game.entities.Entity;
import utils.Random2;
import utils.Vector2;

public class RotationProjectileEmitter extends ProjectileEmitter {
	
	private float mathRandomDivider = 0.7f;
	private Random2 stageRandomGenerator;
	private boolean emitted = false;
	private Entity firingEntity = null;
	private int bulletsPerWave = 36;
	private int interval = bulletsPerWave/3;
	protected double minRotation = 0.004;
	protected double maxRotation = 0.008;
	protected double rotationDiffAmount = 0.0001;
	protected double rotation = minRotation;
	protected boolean rotationAmountInc = true;
	
	public RotationProjectileEmitter(float x, float y) {
		super(x, y);
		stageRandomGenerator = Game.stageManager.getCurrentStage().getRandomGenerator();
		setWidth(0);
		setHeight(0);
	}
	
	public void setFiringEntity(Entity entity) {
		firingEntity = entity;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (!emitted) {
			for (int i = 0; i < bulletsPerWave; i++) {
				Vector2 direction = new Vector2((float)Math.sin(i * interval), (float)Math.cos(i * interval));
//				Vector2 direction = new Vector2((float)(2 * stageRandomGenerator.rangef(0.0F, 0.99999F) - 1), (float)(2 * stageRandomGenerator.rangef(0.0F, 0.99999F) - 1));
				direction.normalize();
				float speed = 1;
				direction.scale(speed);
				if (rotationAmountInc) {
					if (rotation < maxRotation) {
						rotation += rotationDiffAmount;
					} else {
						rotationAmountInc = false;
					}
				} else {
					if (rotation > minRotation) {
						rotation -= rotationDiffAmount;
					} else {
						rotationAmountInc = true;
					}
				}
				Bullet bullet = new RotatingBullet((int)X, (int)Y, direction, firingEntity, rotation);
				bullet.setFiringEntity(firingEntity);
				Game.stageManager.getCurrentStage().addBullet(bullet);
			}
			Game.stageManager.removeEntity(this);
			emitted = true;
		}
	}
}
