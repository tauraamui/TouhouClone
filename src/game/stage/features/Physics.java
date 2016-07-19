package game.stage.features;

import game.Game;
import game.entities.mobs.Enemy;
import game.entities.mobs.Mob;
import game.entities.mobs.Player;
import game.entities.projectiles.Bullet;
import game.stage.Stage;

import java.util.ArrayList;

public class Physics {

	protected Stage stage;
	protected ArrayList<Bullet> bulletsWithinPlayerBounds = new ArrayList<Bullet>();
	protected GrazeTracker grazeTracker = new GrazeTracker();
	
	public Physics(Stage stage) {
		this.stage = stage;
	}
	
	public void update() {
		updateBulletMobCollisions();
		updateBulletPlayerCollisions();
		grazeTracker.update();
	}
	
	public void updateBulletMobCollisions() {
		ArrayList<Bullet> bullets = stage.getBullets();
		ArrayList<Mob> mobs = stage.getMobs();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			for (int j = 0; j < mobs.size(); j++) {
				Mob mob = mobs.get(j);
				if (bullet.getFiringEntity() != mob) {
					if (mob.getBoundingBox().intersects(bullet.getPoint())) {
						mob.hit(bullet, bullet.getFiringEntity());
					}
				}
			}
		}
	}
	
	public void updateBulletPlayerCollisions() {
		ArrayList<Bullet> bullets = stage.getBullets();
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if (bullet.getFiringEntity() != null)  {
				Player player = Game.stageManager.getPlayer();
				if (player != null) {
					if (player.getBoundingBox().intersects(bullet.getPoint())) {
						if (bullet.getFiringEntity() instanceof Enemy)
							grazeTracker.addBullet(bullet);
						player.hit(bullet, bullet.getFiringEntity());
					}
				}
			}
		}
	}
}
