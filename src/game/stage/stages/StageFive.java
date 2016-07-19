package game.stage.stages;

import game.entities.mobs.Enemy;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.ProjectileEmitter;
import game.entities.projectiles.RotationProjectileEmitter;
import game.stage.Stage;
import utils.Vector2;

public class StageFive extends Stage {

	public StageFive() {
		title = "Stage 5: Testing Graze";
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (getBullets().size() == 0) {
//			addBullet(new Bullet(Stage.Width/2, 10, new Vector2(0, 1F), new Enemy(0, 0, null)));
		}
		addProjectileEmitter(new RotationProjectileEmitter(Stage.Width/2, 10));
	}
}
