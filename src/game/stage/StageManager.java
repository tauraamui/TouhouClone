package game.stage;

import game.Game;
import game.entities.Entity;
import game.entities.mobs.Player;
import game.stage.stages.*;
import graphics.ui.menu.StageMenu;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import userinterface.Window;

import java.util.ArrayList;

public class StageManager {

	private ArrayList<Stage> stages = new ArrayList<Stage>();
	private Stage currentStage = null;
	private int currentStageIndex = -1;
	private boolean stageTransitioning = false;
	private long startStageTransitionTime;
	private StageMenu stageMenu;
	
	public StageManager() {
		stages.add(new StageOne().setLocked(false));
		stages.add(new StageTwo());
		stages.add(new StageThree());
		stages.add(new StageFour());
		stages.add(new StageFive());
		//for testing duhhhh
		//THIS MUST BE LAST MEEP MEEP
		stageMenu = new StageMenu(this);
		stageMenu.setOpen(true);
	}

	public void nextStage() {
		if (currentStageIndex + 1 >= stages.size()) return;
		resetCurrentStage();
		currentStageIndex++;
		startCurrentStage();
	}
	
	public void previousStage() {
		if (currentStageIndex - 1 < 0) return;
		resetCurrentStage();
		currentStageIndex--;
		startCurrentStage();
	}

	public void startCurrentStage() {
		resetCurrentStage();
		currentStage = stages.get(currentStageIndex);
		currentStage.setLocked(false);
		currentStage.loadRes();
		stageMenu.setOpen(false);
		stageTransitioning = true;
		startStageTransitionTime = System.currentTimeMillis();
	}
	
	public void setStage(int stageIndex) {
		if (stageIndex >= stages.size() || stageIndex < 0) {
			try {
				throw new Exception("Stage index out of bounds");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			resetCurrentStage();
			currentStageIndex = stageIndex;
			currentStage = stages.get(currentStageIndex);
			stageTransitioning = true;
			startStageTransitionTime = System.currentTimeMillis();
		}
	}
	
	public Stage getCurrentStage() {
		return currentStage;
	}
	
	public int getHighestUnlockedStage() {
		int total = 0;
		for (int i = 0; i < stages.size(); i++) {
			if (stages.get(i).isUnlocked()) {
				total = i;
			}
		}
		return total;
	}
	
	public Player getPlayer() {
		Stage currentStage = getCurrentStage();
		if (currentStage == null) return null;
		return currentStage.getPlayer();
	}
	
	public ArrayList<Stage> getStages() {
		return stages;
	}

	private void resetCurrentStage() {
		if (currentStage == null) return;
		currentStage.reset();
	}
	
	public void update(float deltaTime) {
		
		if (stageMenu.isOpen()) {
			stageMenu.update();
			return;
		}
		if (currentStage == null) return;
		//whilst transitioning twixt stages, don't update current stage
		if (stageTransitioning) {
			if (stageTransitioningTimeOver()) {
				stageTransitioning = false;
			}
		} else {
			currentStage.update(deltaTime);
		}
	}
	
	public void render(GraphicsContext graphicsContext) {
		if (stageMenu.isOpen()) {
			stageMenu.render(graphicsContext);
			return;
		}
		if (currentStage == null) return;
		if (stageTransitioning) {
			if (!stageTransitioningTimeOver()) {
				graphicsContext.fillRect(0, 0, Window.Width, Window.Height);
				if (!(currentStage.getTitleScreenBackground() == null)) {
					graphicsContext.drawImage(currentStage.getTitleScreenBackground(), 0, 0, Window.Width, Window.Height);
				} else {
					graphicsContext.setFill(Color.WHITE);
					graphicsContext.fillRect(0, 0, Window.Width, Window.Height);
				}
				graphicsContext.setFill(Color.BLACK);
				graphicsContext.setFont(Font.getDefault());
				String stageTitle = currentStage.getTitle();

				graphicsContext.strokeText(stageTitle, Window.Width/2-graphicsContext.getFont().getSize()/2, (Window.Height/2)-10);
			}
		} else {
			currentStage.render(graphicsContext);
		}
	}
	
	public void addEntity(Entity entity) {
		if (currentStage == null) return;
		currentStage.addEntity(entity);
	}
	
	public void removeEntity(Entity entity) {
		if (currentStage == null) return;
		currentStage.removeEntity(entity);
	}
	
	public boolean stageTransitioning() {
		return stageTransitioning;
	}
	
	public void openStageMenu() {
		if (stageMenu.isOpen()) Game.quit();
		resetCurrentStage();
		stageTransitioning = false;
		stageMenu.setOpen(true);
	}
	
	private boolean stageTransitioningTimeOver() {
		return (System.currentTimeMillis()-startStageTransitionTime >= 3780);
	}
}
