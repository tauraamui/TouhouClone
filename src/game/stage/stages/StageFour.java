package game.stage.stages;

import game.Game;
import game.entities.mobs.Enemy;
import game.entities.projectiles.ProjectileEmitter;
import game.stage.Stage;
import utils.Timing;
import utils.Vector2;
import utils.resources.ResourceHandler;

public class StageFour extends Stage {

	private boolean spawned = false;
	private Enemy enemy = new Enemy(0, 0, this);
	private int numberSpawned = 0;
	private long lastSpawnedTime = 0;

	public StageFour() {
		title = "Stage 4: Return of the 'Gap Hag'";
		titleScreenBackgroundPath = "/Resources/Backgrounds/stageFourLowRes.jpg";
		titleScreenBackground = ResourceHandler.loadStageTitleScreenBackground(titleScreenBackgroundPath);
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		if (Timing.getCurrentTimeMillis()-lastSpawnedTime >= 1000) {
			//			if (System.currentTimeMillis()-lastSpawnedTime >= 1000) {
			if (numberSpawned < 40) {
				Vector2 randomPos = new Vector2(randomGenerator.rangef(0, Window.Width), randomGenerator.rangef(0, Window.Height));
				ProjectileEmitter projectileEmitter = new ProjectileEmitter(randomPos.X, randomPos.Y);
				projectileEmitter.setFiringEntity(enemy);
				addProjectileEmitter(projectileEmitter);
				numberSpawned++;
				lastSpawnedTime = Timing.getCurrentTimeMillis();
			} else {
				if (bullets.size() == 0)
					Game.stageManager.nextStage();
			}
		}
	}

	@Override
	public void reset() {
		super.reset();
		spawned = false;
	}
}
