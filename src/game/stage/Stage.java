package game.stage;

import game.Game;
import game.entities.Entity;
import game.entities.mobs.Mob;
import game.entities.mobs.Player;
import game.entities.projectiles.Bullet;
import game.entities.projectiles.ProjectileEmitter;
import game.stage.features.Physics;
import graphics.ui.StageInfoPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import utils.Random2;

public class Stage {

	protected String title = "Stage";
	protected Physics physics = new Physics(this);
	protected boolean locked = true;
	protected StageBackground stageBackground;
	protected String stageBackgroundPath;
	protected BufferedImage titleScreenBackground;
	protected String titleScreenBackgroundPath;
	protected Random2 randomGenerator = new Random2();
	protected ArrayList<Entity> entities =  new ArrayList<Entity>();
	protected ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	protected ArrayList<Mob> mobs = new ArrayList<Mob>();
	protected ArrayList<ProjectileEmitter> projectileEmitters = new ArrayList<ProjectileEmitter>();
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
	
	public void render(Canvas canvas) {
		player.render(canvas);
//		for (int i = 0; i < entities.size(); i++) {
//			entities.get(i).render(canvas);
//		}
		for (int i = 0; i < mobs.size(); i++) 
			mobs.get(i).render(canvas);
		
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i).render(canvas);
		
		for (int i = 0; i < projectileEmitters.size(); i++)
			projectileEmitters.get(i).render(canvas);

		stageInfoPanel.render(canvas);
		if (Game.isPaused()) {
			canvas.setFont(new Font("Calibri", Font.BOLD, 40));
			canvas.setColor(Color.LIGHT_GRAY);
			int textWidth = canvas.getFontMetrics().stringWidth("PAUSED");
			int textHeight = canvas.getFontMetrics().getHeight();
			canvas.drawString("PAUSED", (Window.Width/2)-(textWidth/2), (Window.Height/2)-(textHeight/2));
		}
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
	
	public BufferedImage getTitleScreenBackground() {
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

	public Stage setLocked(boolean locked) {
		this.locked = locked;
		return this;
	}
	
	public Stage setTitle(String title) {
		this.title = title;
		return this;
	}
}
