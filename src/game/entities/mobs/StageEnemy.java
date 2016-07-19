package game.entities.mobs;

import game.Game;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.ProjectileEmitter;
import game.physics.AABB;
import game.stage.Stage;
import game.stage.stages.StageOne;
import utils.Timing;
import utils.Vector2;

public class StageEnemy extends Enemy {

	private boolean moveDown = true;
	private boolean fired = false;
	private long changeDirStartTime = Timing.getCurrentTimeMillis();
	private long fireDirStartTime = Timing.getCurrentTimeMillis();

	public StageEnemy(float x, float y, Stage stage) {
		super(x, y, stage);
		setWidth(10);
		setHeight(20);
		mobType = MobType.ENEMY;
		direction.Y = 0.2F;
	}

	public StageEnemy(float x, float y, float initialXDirection, Stage stage) {
		this(x, y, stage);
		direction.X = initialXDirection;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if (Timing.getCurrentTimeMillis()-changeDirStartTime >= 2500) {
			direction.X *= -1;  
			changeDirStartTime = Timing.getCurrentTimeMillis();
		}
		
		move(direction, deltaTime);
		if (Timing.getCurrentTimeMillis()-fireDirStartTime >= 4000) {
			fire();
			fireDirStartTime = Timing.getCurrentTimeMillis();
		}
	}
	
	public void fire() {
		if (attack == null) return;
		attack.fire();
	}

	@Override
	public void checkWithinStageBounds() {
		if (X < 0-StageOne.XSpawnBuffer) Game.stageManager.getCurrentStage().removeEntity(this);
		if (Y < 0-StageOne.YSpawnBuffer) Game.stageManager.getCurrentStage().removeEntity(this);
		if (X > Stage.Width+StageOne.XSpawnBuffer) Game.stageManager.getCurrentStage().removeEntity(this);
		if (Y > Stage.Height+StageOne.YSpawnBuffer) Game.stageManager.getCurrentStage().removeEntity(this);
	}
}
