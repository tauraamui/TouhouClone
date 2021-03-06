package game.stage;

import game.Game;
import game.entities.Entity;
import game.entities.mobs.Mob;
import game.entities.mobs.Player;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.ProjectileEmitter;
import game.stage.features.Physics;
import graphics.ui.StageInfoPanel;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import userinterface.Window;
import utils.Random2;

public class Stage {

	protected String title = "Stage";
	protected Physics physics = new Physics(this);
	protected boolean locked = true;
	protected StageBackground stageBackground;
	protected String stageBackgroundPath;
	private long startStageTransitionTime;
	protected Image titleScreenBackground;
	protected String titleScreenBackgroundPath;
	protected Random2 randomGenerator = new Random2();
	protected ArrayList<Entity> entities =  new ArrayList<Entity>();
	protected ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	protected ArrayList<Mob> mobs = new ArrayList<Mob>();
	protected ArrayList<ProjectileEmitter> projectileEmitters = new ArrayList<ProjectileEmitter>();
	protected boolean transitioning = false;
	public int grazePoints = 0;
	protected boolean stageFinished = false;
	protected StageInfoPanel stageInfoPanel = new StageInfoPanel(this);
	public static int Width = Window.Width-200;
	public static int Height = Window.Height;
	public long totalTickTime = 0;
	public Player player;
	
	public Stage() {
		reset();
	}
	
	public Stage(String title) {
		this.title = title;
		reset();
	}
	
	public void addEntity(Entity entity) {
		if (entity == null) return;
		entities.add(entity);
		if (entity instanceof Bullet) bullets.add((Bullet)entity);
		if (entity instanceof Mob) mobs.add((Mob)entity);
		if (entity instanceof ProjectileEmitter) projectileEmitters.add((ProjectileEmitter)entity);
	}
	
	public void removeEntity(Entity entity) {
		if (entity == null) return;
		entities.remove(entity);
		if (entity instanceof Bullet) bullets.remove((Bullet)entity);
		if (entity instanceof Mob) mobs.remove((Mob)entity);
		if (entity instanceof ProjectileEmitter) projectileEmitters.remove((ProjectileEmitter)entity);
	}

	public void addBullet(Bullet bullet) {
		addEntity(bullet);
	}
	
	public void addMob(Mob mob) {
		addEntity(mob);
	}
	
	public void addProjectileEmitter(ProjectileEmitter projectileEmitter) {
		addEntity(projectileEmitter);
	}

	protected boolean stageTransitioningTimeOver() {
		return (System.currentTimeMillis()-startStageTransitionTime >= 3780);
	}
	
	public void update(float deltaTime) {
		if (Game.isNotPaused()) {
			long before = System.nanoTime();
			
			player.update(deltaTime);
			
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).update(deltaTime);
			}
			
			physics.update();
			
			stageInfoPanel.update();
			totalTickTime = System.nanoTime()-before;
		}
	}

	public void updateTransition(float deltaTime) {}
	
	public void render(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.KHAKI);
		graphicsContext.fillRect(0, 0, Window.Width, Window.Height);
		player.render(graphicsContext);
//		for (int i = 0; i < entities.size(); i++) {
//			entities.get(i).render(canvas);
//		}
		for (int i = 0; i < mobs.size(); i++)
			mobs.get(i).render(graphicsContext);

		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).render(graphicsContext);

		for (int i = 0; i < projectileEmitters.size(); i++)
			projectileEmitters.get(i).render(graphicsContext);

		stageInfoPanel.render(graphicsContext);
		if (Game.isPaused()) {
			graphicsContext.setFont(Font.getDefault());
			graphicsContext.setFill(Color.BLUE);
			double textWidth = graphicsContext.getFont().getSize();
			double textHeight = graphicsContext.getFont().getSize();
			graphicsContext.strokeText("PAUSED", (Window.Width/2)-(textWidth/2), (Window.Height/2)-(textHeight/2));
		}
	}

	public void renderTransition(GraphicsContext graphicsContext) {
		/*
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

		graphicsContext.strokeText(stageTitle, Window.Width/2-graphicsContext.getFont().getSize()/2, (Window.Height/2)-10);
		*/
	}

	public void loadRes() {}
	
	public ArrayList<Entity> getEntities() {return entities;}
	public ArrayList<Bullet> getBullets() {return bullets;}
	public ArrayList<Mob> getMobs() {return mobs;}
	
	public Player getPlayer() {return player;}
	
	public int getWidth() {return Width;}
	public int getHeight(){return Height;}
	public Physics getPhysics() {return physics;}
	
	public void reset() {
		entities.clear();
		bullets.clear();
		mobs.clear();
		projectileEmitters.clear();
		stageFinished = false;
		grazePoints = 0;
		randomGenerator.setSeed(1338);
		player = new Player(Stage.Width/2, Stage.Height-100, this);
	}
	
	public Random2 getRandomGenerator() {
		return randomGenerator;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Image getTitleScreenBackground() {
		return titleScreenBackground;
	}
	
	public int getStageProgress() {
		return 0;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public boolean isUnlocked() {
		return !locked;
	}

	public boolean isTransitioning() {
		return transitioning;
	}

	public void setTransitioning(boolean transitioning) {
		this.transitioning = transitioning;
	}

	public long getStartStageTransitionTime() {
		return startStageTransitionTime;
	}

	public void setStartStageTransitionTime(long startStageTransitionTime) {
		this.startStageTransitionTime = startStageTransitionTime;
	}

	public Stage setLocked(boolean locked) {
		this.locked = locked;
		return this;

	}
	
	public Stage setTitle(String title) {
		this.title = title;
		return this;
	}
}
