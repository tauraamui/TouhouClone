package game.stage;

import game.Game;
import game.entities.Entity;
import game.entities.mobs.Player;
import game.stage.stages.StageFive;
import game.stage.stages.StageFour;
import game.stage.stages.StageOne;
import game.stage.stages.StageThree;
import game.stage.stages.StageTwo;
import graphics.ui.menu.StageMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

public class StageManager {

	private ArrayList<Stage> stages = new ArrayList<Stage>();
	private Stage currentStage = null;
	private int currentStageIndex = -1;
	private boolean stageTransitioning = false;
	private long startStageTransitionTime;
	public boolean stageMenuOpen = true;
	private StageMenu stageMenu;
	
	public StageManager() {
		stages.add(new StageOne().setLocked(false));
		stages.add(new StageTwo());
		stages.add(new StageThree());
		stages.add(new StageFour());
		stages.add(new StageFive());
		//for testing duhhhh
//		for (int i = 0; i < 23; i++) {
//			stages.add(new Stage("Stage "+(4+i)+": Meow"));
//		}
		//THIS MUST BE LAST MEEP MEEP
		stageMenu = new StageMenu(this);
	}
	
	public void nextStage() {
		if (currentStageIndex + 1 >= stages.size()) return;
		resetCurrentStage();
		currentStageIndex++;
		currentStage = stages.get(currentStageIndex);
		currentStage.setLocked(false);
		currentStage.loadRes();
		stageTransitioning = true;
		startStageTransitionTime = System.currentTimeMillis();
	}
	
	public void previousStage() {
		if (currentStageIndex - 1 < 0) return;
		resetCurrentStage();
		currentStageIndex--;
		currentStage = stages.get(currentStageIndex);
		currentStage.setLocked(false);
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
		
		if (stageMenuOpen) {
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
	
	public void render(Graphics canvas) {
		if (stageMenuOpen) {
			stageMenu.render(canvas);
			return;
		}
		if (currentStage == null) return;
		if (stageTransitioning) {
			if (!stageTransitioningTimeOver()) {
				canvas.fillRect(0, 0, Window.Width, Window.Height);
				if (!(currentStage.getTitleScreenBackground() == null)) {
					canvas.drawImage(currentStage.getTitleScreenBackground(), 0, 0, Window.Width, Window.Height, Game.Renderer);
				} else {
					canvas.setColor(Color.WHITE);
					canvas.fillRect(0, 0, Window.Width, Window.Height);
				}
				canvas.setColor(Color.BLACK);
				canvas.setFont(new Font("Calabri", Font.CENTER_BASELINE, 20));
				String stageTitle = currentStage.getTitle();
				FontMetrics fontMetrics = canvas.getFontMetrics();
				canvas.drawString(stageTitle, Window.Width/2-fontMetrics.stringWidth(stageTitle)/2, (Window.Height/2)-10);
			}
		} else {
			currentStage.render(canvas);
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
		if (stageMenuOpen) Game.quit();
		resetCurrentStage();
		stageTransitioning = false;
		stageMenuOpen = true;
	}
	
	private boolean stageTransitioningTimeOver() {
		return (System.currentTimeMillis()-startStageTransitionTime >= 3780);
	}
}
