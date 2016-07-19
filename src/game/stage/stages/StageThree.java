package game.stage.stages;

import utils.Timing;
import utils.resources.ResourceHandler;
import game.Game;
import game.entities.mobs.Player;
import game.stage.Stage;

public class StageThree extends Stage {

	private long startTime;
	private boolean setStartTime = false;

	public StageThree() {
		title = "Stage 3: MEEP MEEP";
		titleScreenBackgroundPath = "/Resources/Backgrounds/stageThreeLowRes.jpg";
		titleScreenBackground = ResourceHandler.loadStageTitleScreenBackground(titleScreenBackgroundPath);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (!setStartTime) {
			startTime = Timing.getCurrentTimeMillis();
			setStartTime = true;
		}
		if (Timing.getCurrentTimeMillis()-startTime >= 6000) Game.stageManager.nextStage();
	}

	@Override
	public void reset() {
		super.reset();
		setStartTime = false;
	}
}
