package game.stage.stages;

import game.Game;
import game.entities.Entity;
import game.entities.mobs.StageEnemy;
import game.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import userinterface.Window;
import utils.Timing;
import utils.Vector2;
import utils.resources.ResourceHandler;

public class StageOne extends Stage {

	public Vector2 EnemySpawnPoint = new Vector2(Stage.Width/2, -30);
	public static float XSpawnBuffer = 40;
	public static float YSpawnBuffer = 40;
	protected long lastSpawned = 0;
	protected long enemiesToDestoryStageOne = 60;
	protected float destroyedEnemies = 0;
	protected float enemiesToDestory = 30;

	protected double stageTitleX = Window.Width;
	protected double stageTitleY = Window.Height/2-10;

	public StageOne() {
		title = "Stage 1: Perfect Cherry Blossom";
		titleScreenBackgroundPath = "/Resources/Backgrounds/stageOneLowRes.jpg";
		titleScreenBackground = ResourceHandler.loadStageTitleScreenBackground(titleScreenBackgroundPath);
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		if (stageFinished) Game.stageManager.nextStage();
		if (Timing.getCurrentTimeMillis()-lastSpawned >= 4000) {
		//if (System.currentTimeMillis()-lastSpawned >= 4000) {
			addMob(new StageEnemy(EnemySpawnPoint.X, EnemySpawnPoint.Y, 0.3F, this));
			addMob(new StageEnemy(Stage.Width/2-50, EnemySpawnPoint.Y, -0.3F, this));
			addMob(new StageEnemy(Stage.Width/2+70, EnemySpawnPoint.Y, 0.3F, this));
			lastSpawned = Timing.getCurrentTimeMillis();
		}
		if (destroyedEnemies >= enemiesToDestory) stageFinished = true;
	}

	@Override
	public void updateTransition(float deltaTime) {
		if (stageTitleX > Window.Width/2-15) stageTitleX -= 8;
		System.out.println(stageTitleX);
	}

	@Override
	public void renderTransition(GraphicsContext graphicsContext) {
		graphicsContext.fillRect(0, 0, Window.Width, Window.Height);
		if (!(getTitleScreenBackground() == null)) {
			graphicsContext.drawImage(getTitleScreenBackground(), 0, 0, Window.Width, Window.Height);
		} else {
			graphicsContext.setFill(Color.WHITE);
			graphicsContext.fillRect(0, 0, Window.Width, Window.Height);
		}
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.setFont(Font.getDefault());
		String stageTitle = getTitle();

		graphicsContext.strokeText(stageTitle, stageTitleX, stageTitleY);
		//graphicsContext.strokeText(stageTitle, Window.Width/2-graphicsContext.getFont().getSize()/2, (Window.Height/2)-10);
	}

	@Override
	public void removeEntity(Entity entity) {
		super.removeEntity(entity);
		if (entity instanceof StageEnemy) destroyedEnemies++;
	}
	
	@Override
	public int getStageProgress() {
		return (int)((destroyedEnemies/enemiesToDestory)*100);
	}

	@Override
	public void reset() {
		super.reset();
		enemiesToDestory = enemiesToDestoryStageOne;
	}
}
