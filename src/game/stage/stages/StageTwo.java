package game.stage.stages;

import utils.resources.ResourceHandler;
import game.Game;
import game.entities.mobs.Player;
import game.entities.mobs.StageEnemy;
import game.stage.Stage;

public class StageTwo extends Stage {
	
	protected float minHealthPercentage = 10;

	public StageTwo() {
		title = "Stage 2: End of Time";
		titleScreenBackgroundPath = "/Resources/Backgrounds/stageTwoLowRes.jpg";
		titleScreenBackground = ResourceHandler.loadStageTitleScreenBackground(titleScreenBackgroundPath);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (player.healthManager.getHealthPercentage() > minHealthPercentage) player.healthManager.decreaseHealth(1);
		if (player.healthManager.getHealthPercentage() <= minHealthPercentage) Game.stageManager.nextStage();
	}
	
	@Override
	public int getStageProgress() {
		float healthPercentage = player.healthManager.getHealthPercentage();
		
		return 0;
	};

	@Override
	public void reset() {
		super.reset();
		addMob(new StageEnemy(0, 0, 0.3F, this));
		addMob(new StageEnemy(Stage.Width/2-50, 0, -0.3F, this));
		addMob(new StageEnemy(Stage.Width/2+70, 0, 0.3F, this));
	}
}
